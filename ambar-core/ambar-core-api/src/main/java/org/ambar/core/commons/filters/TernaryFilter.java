/**
 * ambar-core-api [25/08/2011 21:49:52]
 */
package org.ambar.core.commons.filters;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;


/**
 * <p>
 * Clase utilizada en las expresiones de tres operandos.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.PropertyCompareFilter
 */
public class TernaryFilter extends PropertyCompareFilter {
	private static final long serialVersionUID = 8345329827861425987L;
	private final TernaryOperator ternaryOperator;
	private final FilterValue value1;
	private final FilterValue value2;

	/**
	 * Constructor propio del tipo de filtro.
	 * @param pProperty			Operando izquierdo
	 * @param pTernaryOperator	Operador
	 * @param pValue1			Primer operando derecho
	 * @param pValue2			Segundo operando derecho
	 */
	public TernaryFilter(final String pProperty, final TernaryOperator pTernaryOperator,
			final Object pValue1, final Object pValue2) {
		this.setPropertyName(pProperty);
		this.ternaryOperator = pTernaryOperator;
		this.value1 = new FilterValue(pValue1);
		this.value2 = new FilterValue(pValue2);
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.filters.PropertyCompareFilter#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("TernaryFilter [");
		if (this.value1 != null) {
			builder.append("value1=");
			builder.append(this.value1);
			builder.append(", ");
		}
		if (this.value2 != null) {
			builder.append("rightOperand=");
			builder.append(this.value2);
			builder.append(", ");
		}
		if (this.getPropertyName() != null) {
			builder.append("getPropertyName()=");
			builder.append(this.getPropertyName());
			builder.append(", ");
		}
		if (this.ternaryOperator != null) {
			builder.append("ternaryOperator=");
			builder.append(this.ternaryOperator);
		}
		builder.append("]");
		return builder.toString();
	}

    /**
     * @return Retorna el valor del atributo ternaryOperator.
     */
    public TernaryOperator getTernaryOperator() {
        return this.ternaryOperator;
    }

    /**
     * @return Retorna el valor del atributo value1.
     */
    public FilterValue getValue1() {
        return this.value1;
    }

    /**
     * @return Retorna el valor del atributo value2.
     */
    public FilterValue getValue2() {
        return this.value2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
