/**
 * ambar-core-api [25/08/2011 21:52:01]
 */
package org.ambar.core.commons.filters;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;


/**
 * <p>
 * Clase utilizada en las expresiones de un único operando.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.PropertyCompareFilter
 */
public class UnaryFilter extends PropertyCompareFilter {
	private static final long serialVersionUID = -3024678307179010393L;
	private final UnaryOperator unaryOperator;

	/**
	 * Constructor propio del tipo de filtro.
	 * @param pProperty			Operando
	 * @param pUnaryOperator	Operador
	 */
	public UnaryFilter(final String pProperty, final UnaryOperator pUnaryOperator) {
		this.unaryOperator = pUnaryOperator;
		this.setPropertyName(pProperty);
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.filters.PropertyCompareFilter#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("UnaryFilter [");
		if (this.getPropertyName() != null) {
			builder.append("getPropertyName()=");
			builder.append(this.getPropertyName());
			builder.append(", ");
		}
		if (this.unaryOperator != null) {
			builder.append("unaryOperator");
			builder.append(this.unaryOperator);
			builder.append(", ");
		}
		builder.append("]");
		return builder.toString();
	}

    /**
     * @return Retorna el valor del atributo unaryOperator.
     */
    public UnaryOperator getUnaryOperator() {
        return this.unaryOperator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
