/**
 * ambar-core-api [25/08/2011 21:12:07]
 */
package org.ambar.core.commons.filters;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;


/**
 * <p>
 * Clase utilizada en las expresiones de dos operandos.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.PropertyCompareFilter
 */
public class BinaryFilter extends PropertyCompareFilter {

	private static final long serialVersionUID = 5094080191197526292L;
	private final FilterValue value;
	private final BinaryOperator binaryOperator;

	/**
	 * Constructor propio del tipo de filtro.
	 * @param pProperty			Operando izquierdo
	 * @param pBinaryOperator	Operador
	 * @param pValue			Operando derecho
	 */
	public BinaryFilter(final String pProperty, final BinaryOperator pBinaryOperator, final Object pValue) {
		this.setPropertyName(pProperty);
		this.binaryOperator = pBinaryOperator;
		this.value = new FilterValue(pValue);
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.filters.PropertyCompareFilter#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("BinaryFilter [");

		builder.append("propertyName=");
		builder.append(this.getPropertyName());
		builder.append(", ");
		builder.append("value=");
		builder.append(this.getValue());

		builder.append("]");
		return builder.toString();
	}

    /**
     * @return Retorna el valor del atributo value.
     */
    public FilterValue getValue() {
        return this.value;
    }

    /**
     * @return Retorna el valor del atributo binaryOperator.
     */
    public BinaryOperator getBinaryOperator() {
        return this.binaryOperator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
