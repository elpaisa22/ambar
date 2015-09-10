/**
 * ambar-core-dictionary [08/03/2012 22:15:52]
 */
package org.ambar.core.dictionary.expressions;

import java.util.ArrayList;
import java.util.List;

import org.ambar.core.commons.filters.Filter;


/**
 * <p>
 * Nodo de la expresión.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ExpressionNode {
    private String operator;
    private List<Filter> filters;
    private ExpressionNode linkedNode;
    private boolean negation;

    /**
     * Constructor por default.
     */
    public ExpressionNode() {
        super();
        this.filters = new ArrayList<Filter>();
    }

    /**
     * @param pFilter Agrega un filtro.
     */
    public void addFilter(Filter pFilter) {
        this.filters.add(pFilter);
    }

    /**
     * @return Retorna el valor del atributo filters.
     */
    public List<Filter> getFilters() {
        return this.filters;
    }

    /**
     * @return Retorna el valor del atributo operator.
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * @param pOperator Establece el valor del atributo operator.
     */
    public void setOperator(final String pOperator) {
        this.operator = pOperator;
    }

    /**
     * @return Retorna el valor del atributo linkedNode.
     */
    public ExpressionNode getLinkedNode() {
        return this.linkedNode;
    }

    /**
     * @param pLinkedNode Establece el valor del atributo linkedNode.
     */
    public void setLinkedNode(final ExpressionNode pLinkedNode) {
        this.linkedNode = pLinkedNode;
    }

    /**
     * @return Retorna el valor del atributo negation.
     */
    public boolean isNegation() {
        return this.negation;
    }

    /**
     * @param pNegation Establece el valor del atributo negation.
     */
    public void setNegation(final boolean pNegation) {
        this.negation = pNegation;
    }
}

