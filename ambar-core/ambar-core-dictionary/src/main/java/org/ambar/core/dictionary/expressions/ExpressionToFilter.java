/**
 * ambar-core-dictionary [08/03/2012 22:18:09]
 */
package org.ambar.core.dictionary.expressions;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ambar.core.commons.filters.BinaryFilter;
import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.GroupFilter;
import org.ambar.core.commons.filters.GroupOperator;
import org.ambar.core.commons.filters.NotGroupFilter;
import org.ambar.core.commons.filters.criteria.Criteria;
import org.ambar.core.commons.mapping.exceptions.MappingOperationException;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Helper que colabora en la resolución de expresiones que deben
 * ser convertidas en objetos {@link Filter}.
 * </p>
 *
 * @author Sebastian
 *
 */
public final class ExpressionToFilter {
    private static final Logger LOG = LoggerFactory.getLogger(ExpressionToFilter.class);

    private static final ExpressionToFilter INSTANCE = new ExpressionToFilter();

    private final List<String> relationalOperators;
    private final List<String> conditionalOperators;
    private final Pattern pattern;
    private final Pattern patternValueOperators;

    /**
     * Constructor por default.
     */
    private ExpressionToFilter() {
        super();

    	final int siete = 7;
    	final int cuatro = 4;

        this.relationalOperators = new ArrayList<String>(siete);
        this.relationalOperators.add("=");
        this.relationalOperators.add("<");
        this.relationalOperators.add(">");
        this.relationalOperators.add("<=");
        this.relationalOperators.add(">=");
        this.relationalOperators.add("<>");
        this.relationalOperators.add("LIKE");

        this.conditionalOperators = new ArrayList<String>(cuatro);
        this.conditionalOperators.add("AND");
        this.conditionalOperators.add("OR");
        this.conditionalOperators.add("(");
        this.conditionalOperators.add(")");

        StringBuilder expressionPattern = new StringBuilder();
        expressionPattern.append("[a-zA-Z0-9._]+|:[a-zA-Z0-9]+|\'[a-zA-Z0-9]+\'|(=)|(<)|(>)|(<=)|(>=)|(<>)|");
        expressionPattern.append("([Oo][Rr])|([Aa][Nn][Dd])|([Nn][Oo][Tt])|[(]|[)]");
        this.pattern = Pattern.compile(expressionPattern.toString());

        this.patternValueOperators = Pattern.compile(":[A-Za-z0-9]+");
    }

    /**
     * Método que retorna la instancia única del helper.
     * @return {@link ExpressionToFilter}
     */
    public static synchronized ExpressionToFilter getInstance() {
        return INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Este objeto no se puede clonar");
    }

    /**
     * Método que permite establecer si un "valor" es operador relacional.
     * @param pValue Valor a comparar
     * @return {@link Boolean}
     */
    private boolean isRelationalOperator(final String pValue) {
        return this.relationalOperators.contains(pValue);
    }

    /**
     * Método que permite establecer si un "valor" es operador condicional o paréntesis
     * ya que ambos se utlizan para "separar términos" de la expresión.
     * @param pValue Valor a comparar
     * @return {@link Boolean}
     */
    private boolean isConditionalOperator(final String pValue) {
        return this.conditionalOperators.contains(pValue);
    }

    /**
     * Método que obtiene el valor de una propiedad.
     * @param pValue Nombre de la propiedad
     * @param pEntity Entidad
     * @return {@link Object}
     */
    private Object getValueFieldFromObject(final String pValue, final Object pEntity) {
        Object value = null;
        try {
            value = BeanUtilsBean.getInstance().getPropertyUtils().getNestedProperty(pEntity, pValue);
        } catch (IllegalAccessException e) {
            String msg = "Error en el acceso por reflection";
            LOG.error(msg);
            throw new MappingOperationException(msg, e);
        } catch (InvocationTargetException e) {
            String msg = "Error en el acceso a una propiedad";
            LOG.error(msg);
            throw new MappingOperationException(msg, e);
        } catch (NoSuchMethodException e) {
            String msg = "Error por acceso a una propiedad inexistente";
            LOG.error(msg);
            throw new MappingOperationException(msg, e);
        }
        return value;
    }

    /**
     * Método que procesa la lista enlazada para generar un {@link Filter}.
     * @param pRootExpression Nodo raíz de la lista enlazada que contiene la expresión
     * @return {@link Filter}
     */
    private Filter generateFilter(final ExpressionNode pRootExpression) {
        Filter filter = null;
        // caso particular de lista enlazada con un únco nodo con una única "expresión"
        if ((pRootExpression.getLinkedNode() == null)
                && ((pRootExpression.getOperator() == null) || (pRootExpression.getOperator().trim().isEmpty()))
                && (pRootExpression.getFilters().size() == 1)) {
            filter = pRootExpression.getFilters().get(0);
        } else {
            ExpressionNode node = pRootExpression;

            GroupFilter lastFilter = null;
            while (node != null) {
                if (filter == null) {
                    if (node.isNegation()) {
                        filter = new NotGroupFilter(GroupOperator.fromValue(node.getOperator()),
                                node.getFilters().toArray(new Filter[0]));
                    } else {
                        filter = new GroupFilter(GroupOperator.fromValue(node.getOperator()),
                                node.getFilters().toArray(new Filter[0]));
                    }
                    lastFilter = (GroupFilter) filter;
                } else {
                    GroupFilter groupFilter = null;
                    if (node.isNegation()) {
                        groupFilter = new NotGroupFilter(GroupOperator.fromValue(node.getOperator()),
                                node.getFilters().toArray(new Filter[0]));
                    } else {
                        groupFilter = new GroupFilter(GroupOperator.fromValue(node.getOperator()),
                                node.getFilters().toArray(new Filter[0]));
                    }
                    lastFilter.getInnerFilters().add(groupFilter);
                    lastFilter = groupFilter;
                }
                node = node.getLinkedNode();
            }
        }

        return filter;
    }

    /**
     * Método que se encarga de la conversión.
     * @param pExpression           Expresión a analizar
     * @param pSourceValueEntity    Objeto con los valores para el filtro
     * @return {@link Filter}
     */
    public Filter convertExpression(final String pExpression,
                                    final Object pSourceValueEntity) {

        Deque<String> operands = new ArrayDeque<String>();
        Deque<String> operators = new ArrayDeque<String>();
        Deque<String> parenthesis = new ArrayDeque<String>();
        boolean negation = false;

        Matcher matcher = this.pattern.matcher(pExpression);

        ExpressionNode root = new ExpressionNode();
        ExpressionNode lastNode = root;

        while (matcher.find()) {
            String token = matcher.group();

            if (isConditionalOperator(token)) {
                LOG.debug("es operador lógico o paréntesis: {}", token);
                if (token.equals("(")) {
                    parenthesis.push(token);
                    ExpressionNode newNode = new ExpressionNode();
                    lastNode.setLinkedNode(newNode);
                    lastNode = newNode;
                    if (negation) {
                        negation = false;
                        lastNode.setNegation(true);
                    }
                } else if (token.equals(")")) {
                    parenthesis.pop();
                } else {
                    if ((lastNode.getOperator() != null) && (!token.toUpperCase().equals(lastNode.getOperator()))) {
                        ExpressionNode newNode = new ExpressionNode();
                        lastNode.setLinkedNode(newNode);
                        lastNode = newNode;
                        if (negation) {
                            negation = false;
                            lastNode.setNegation(true);
                        }
                    }
                    if (lastNode.getOperator() == null) {
                        lastNode.setOperator(token.toUpperCase());
                    }
                }
            } else if (isRelationalOperator(token.toUpperCase())) {
                LOG.debug("es operador relacional: {}", token);
                operators.push(token.toUpperCase());
            } else if (token.toUpperCase().equals("NOT")) {
                LOG.debug("es negación: {}", token);
                negation = true;
            } else {
                LOG.debug("es un operando: {}", token);
                if (operands.size() > 0) {
                    Object value = token;
                    if (token.startsWith(":")) {
                        value = this.getValueFieldFromObject(token.substring(1), pSourceValueEntity);
                    } else if (token.startsWith("'")) {
                        value = token.substring(1, token.length() - 2);
                    }

                    BinaryFilter filter = Criteria.createBinary().property(operands.pop())
                            .fromStringOperator(operators.pop(), value);
                    lastNode.addFilter(filter);
                    LOG.debug("Filtro generado: {}", filter);
                } else {
                    operands.push(token);
                }
            }
        }

        return this.generateFilter(root);
    }

    /**
     * Método que reemplaza parámetros por valores en una expresión.
     * @param pExpression           Expresión con parámetros
     * @param pSourceValueEntity    Entidad con valores a reemplazar
     * @return {@link String}       Expresión con parámetros reemplazados
     */
    public String processParameterInExpression(final String pExpression, final Object pSourceValueEntity) {
        String result = pExpression;
        Matcher matcher = this.patternValueOperators.matcher(pExpression);

        while (matcher.find()) {
            String token = matcher.group();
            Object value = this.getValueFieldFromObject(token.substring(1), pSourceValueEntity);
            if (value != null) {
                result = result.replaceFirst(token, value.toString());
            } else {
                result = result.replaceFirst(token, "NULL");
            }
        }
        return result;
    }
}

