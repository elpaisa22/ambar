/**
 * ambar-core-impl [11/10/2011 03:03:25]
 */
package org.ambar.core.commons.filters.helpers.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ambar.core.commons.filters.BinaryFilter;
import org.ambar.core.commons.filters.BinaryOperator;
import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.FilterValue;
import org.ambar.core.commons.filters.GroupFilter;
import org.ambar.core.commons.filters.MultipleFilter;
import org.ambar.core.commons.filters.NotGroupFilter;
import org.ambar.core.commons.filters.Pager;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.commons.filters.TernaryFilter;
import org.ambar.core.commons.filters.UnaryFilter;
import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;
import org.ambar.core.commons.filters.helpers.FilterExpressionBuilderEx;
import org.ambar.core.commons.filters.helpers.FilterValuesConverter;
import org.ambar.core.commons.mapping.configuration.FieldMapping;
import org.ambar.core.commons.order.Order;
import org.ambar.core.exceptions.FilterConfigurationErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Implementación de {@link FilterExpressionBuilder}.
 * </p>
 *
 * @author Sebastian
 *
 */
public class FilterExpressionBuilderImpl implements FilterExpressionBuilderEx {

    private static final Logger LOG = LoggerFactory.getLogger(FilterExpressionBuilderImpl.class);
    
    private FilterValuesConverter converter = FilterValuesConverter.getInstance();

    private Map<String, FieldDefinition> mappedPropertyNames;
    private final QueryPredicate predicate;

    /**
     * Clase privada para guardar la configuración de cada propiedad.
     */
    class FieldDefinition {
        private String field;
        private String alias;
        private boolean addWildCards;
        private boolean caseInsensitive;

        /**
         * @return String
         * */
        public String getField() {
            return field;
        }

        /**
         * @return String
         * */
        public String getAlias() {
            return alias;
        }

        /**
         * @param pAlias Alias
         * */
        public void setAlias(final String pAlias) {
            alias = pAlias;
        }

        /**
         * @param pField Field
         * */
        public void setField(final String pField) {
            field = pField;
        }

        /**
         * @return {@link Boolean}
         * */
        public boolean isAddWildCards() {
            return addWildCards;
        }

        /**
         * @param pAddWildCards WildCards
         * */
        public void setAddWildCards(final boolean pAddWildCards) {
            addWildCards = pAddWildCards;
        }

        /**
         * @return {@link Boolean} case sensitive
         * */
        public boolean isCaseInsensitive() {
            return caseInsensitive;
        }

        /**
         * @param pCaseInsensitive case sensitive
         * */
        public void setCaseInsensitive(final boolean pCaseInsensitive) {
            caseInsensitive = pCaseInsensitive;
        }
    }

    /**
     * Constructor por default.
     */
    public FilterExpressionBuilderImpl() {
        super();
        this.predicate = new QueryPredicate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public QueryPredicate getPredicate() {
        return this.predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMappings(final Set<FieldMapping> pMappings) {
        this.mappedPropertyNames = new HashMap<String, FieldDefinition>();
        if (pMappings != null) {
            for (FieldMapping field : pMappings) {
                String beField = field.getFieldA();
                String dtoField = field.getFieldB();

                FieldDefinition fieldDefinition = new FieldDefinition();
                fieldDefinition.setField(beField);
                fieldDefinition.setAlias(field.getQueryAlias());

                // armar el map con el atributo del dto como key
                // y el alias o atributo de la be como value
                this.mappedPropertyNames.put(dtoField, fieldDefinition);
            }
        }
    }

    /**
     * Método que retorna los valores configurados para una propiedad.
     * @param pFilterProperty Nombre de la propiedad establecido en el filtro
     * @return {@link FieldDefinition} Objecto que encapsula los valores de configuración
     */
    private FieldDefinition findFieldDefinition(String pFilterProperty) {
        return this.mappedPropertyNames.get(pFilterProperty);
    }

    /**
     * Método que se encarga de establecer el nombre correcto de la propiedad
     * a utilizar en la expresión.
     * @param pFilterProperty Propiedad establecida en el {@link Filter}
     * @param pFieldDefinition Configuración de mapeos para la propiedad
     * @return {@link String} Nombre correcto de la propiedad
     */
    private String calculatePropertyName(String pFilterProperty, FieldDefinition pFieldDefinition) {
        String propertyName;
        if (pFieldDefinition == null) {
            propertyName = "e." + pFilterProperty;
        } else {
            propertyName = ((pFieldDefinition.getAlias() != null)
                    ? pFieldDefinition.getAlias() : "e." + pFieldDefinition.getField());
        }
        return propertyName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final UnaryFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getPropertyName() == null) || (pFilter.getUnaryOperator() == null)) {
            String msg = "Error de configuración en \"filtro unario\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }
        FieldDefinition fieldDefinition = this.findFieldDefinition(pFilter.getPropertyName());

        String propertyName = this.calculatePropertyName(pFilter.getPropertyName(), fieldDefinition);

        this.predicate.appendToWhere(propertyName);
        this.predicate.appendToWhere(pFilter.getUnaryOperator().getOperator());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final BinaryFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getPropertyName() == null) || (pFilter.getBinaryOperator() == null)) {
            String msg = "Error de configuración en \"filtro binario\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }

        final FieldDefinition fieldDefinition = this.findFieldDefinition(pFilter.getPropertyName());

        final Object value;
        if (((fieldDefinition != null) && (fieldDefinition.isAddWildCards()))
                && ((pFilter.getBinaryOperator().equals(BinaryOperator.Like))
                || (pFilter.getBinaryOperator().equals(BinaryOperator.NotLike)))) {
            value = "%" + pFilter.getValue().getValue() + "%";
        } else {
        	value = converter.convert(pObjectClass,
        			                  pFilter.getPropertyName(),
        			                  pFilter.getValue().getValue());
        }
        // valores de la expresión
        final String propertyName = this.calculatePropertyName(pFilter.getPropertyName(), fieldDefinition);
        String paramName = ":" + this.predicate.addParam(
                pFilter.getPropertyName(),
                (((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive()))
                        ? value.toString().toLowerCase() : value));

        // generación de la expresión
        if ((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive())) {
            // no distinguir entre mayúsculas y minúsculas
            predicate.appendToWhere("LOWER(" + propertyName + ")");
        } else {
            predicate.appendToWhere(propertyName);
        }
        predicate.appendToWhere(pFilter.getBinaryOperator().getOperator());
        predicate.appendToWhere(paramName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final TernaryFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getPropertyName() == null)
                || (pFilter.getTernaryOperator() == null)
                || (pFilter.getValue1() == null)
                || (pFilter.getValue2() == null)) {
            String msg = "Error de configuración en \"filtro ternario\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }
        FieldDefinition fieldDefinition = this.findFieldDefinition(pFilter.getPropertyName());

        
        final Object value1 = converter.convert(pObjectClass,
                                                pFilter.getPropertyName(),
                                                pFilter.getValue1().getValue());
        
        final Object value2 = converter.convert(pObjectClass,
                                                pFilter.getPropertyName(),
                                                pFilter.getValue2().getValue());
        
        // valores de la expresión
        String propertyName = this.calculatePropertyName(pFilter.getPropertyName(), fieldDefinition);
        String paramName1 = ":" + this.predicate.addParam(
                pFilter.getPropertyName(),
                (((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive()))
                        ? pFilter.getValue1().getValue().toString().toLowerCase()
                        : value1));
        String paramName2 = ":" + this.predicate.addParam(
                pFilter.getPropertyName(),
                (((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive()))
                        ? pFilter.getValue2().getValue().toString().toLowerCase()
                        : value2));

        // generación de la expresión
        if ((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive())) {
            this.predicate.appendToWhere("LOWER(" + propertyName + ")");
        } else {
            this.predicate.appendToWhere(propertyName);
        }
        this.predicate.appendToWhere(pFilter.getTernaryOperator().getOperator());
        this.predicate.appendToWhere(paramName1);
        this.predicate.appendToWhere("AND");
        this.predicate.appendToWhere(paramName2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final MultipleFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getPropertyName() == null)
                || (pFilter.getMultipleOperator() == null)
                || (pFilter.getValues() == null)) {
            String msg = "Error de configuración en \"filtro de valores múltiples\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }

        FieldDefinition fieldDefinition = this.findFieldDefinition(pFilter.getPropertyName());

        // generar un list con los valores
        List<Object> values = new ArrayList<Object>();
        for (FilterValue value : pFilter.getValues()) {
            if (fieldDefinition.isCaseInsensitive()) {
                values.add(value.getValue().toString().toLowerCase());
            } else {
            	final Object convertedValue = converter.convert(pObjectClass,
                                                                pFilter.getPropertyName(),
                                                                value.getValue());
                values.add(convertedValue);
            }
        }

        String propertyName = this.calculatePropertyName(pFilter.getPropertyName(), fieldDefinition);
        String paramName = ":" + this.predicate.addParam(
                pFilter.getPropertyName(), values);

        if ((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive())) {
            this.predicate.appendToWhere("LOWER(" + propertyName + ")");
        } else {
            this.predicate.appendToWhere(propertyName);
        }
        this.predicate.appendToWhere(pFilter.getMultipleOperator().getOperator());
        this.predicate.appendToWhere("(" + paramName + ")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final GroupFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getGroupOperator() == null) || (pFilter.getInnerFilters() == null)) {
            String msg = "Error de configuración en \"filtros compuestos\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }

        this.predicate.appendToWhere("(");
        int i = 0;
        for (Filter filter : pFilter.getInnerFilters()) {
            i++;
            if (i > 1) {
                this.predicate.appendToWhere(pFilter.getGroupOperator().getOperator());
            }
            this.predicate.appendToWhere("(");
            filter.build(this, pObjectClass);
            this.predicate.appendToWhere(")");
        }

        this.predicate.appendToWhere(")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildExpression(final NotGroupFilter pFilter, final Class<?> pObjectClass) {
        if ((pFilter.getGroupOperator() == null) || (pFilter.getInnerFilters() == null)) {
            String msg = "Error de configuración en \"filtros compuestos (negación)\"";
            LOG.error(msg);
            throw new FilterConfigurationErrorException(msg);
        }

        this.predicate.appendToWhere("NOT (");
        int i = 0;
        for (Filter filter : pFilter.getInnerFilters()) {
            i++;
            if (i > 1) {
                this.predicate.appendToWhere(pFilter.getGroupOperator().getOperator());
            }
            this.predicate.appendToWhere("(");
            filter.build(this, pObjectClass);
            this.predicate.appendToWhere(")");
        }

        this.predicate.appendToWhere(")");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processOrderAndPagination(final Pager pPager) {
        if (pPager == null) {
            return;
        }
        if (pPager.getOrderList() != null) {
            for (Order order : pPager.getOrderList()) {
                if (order.getPropertyName() == null) {
                    String msg = "Error de configuración de ordenamiento (columna de órden no establecida)";
                    LOG.error(msg);
                    throw new FilterConfigurationErrorException(msg);
                }
                FieldDefinition fieldDefinition = this.findFieldDefinition(order.getPropertyName());
                String propertyName = this.calculatePropertyName(order.getPropertyName(), fieldDefinition);

                if ((fieldDefinition != null) && (fieldDefinition.isCaseInsensitive())) {
                    this.predicate.appendToOrderBy("LOWER(" + propertyName + ")");
                } else {
                    this.predicate.appendToOrderBy(propertyName);
                }
                if (order.getOrderType() != null) {
                    this.predicate.appendToOrderBy(order.getOrderType().toString());
                }
            }
        }

        this.predicate.setPageSize(pPager.getPageSize());
        this.predicate.setPageNumber(pPager.getPage());
    }
}
