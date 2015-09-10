/**
 * ambar-core-api [25/08/2011 21:47:29]
 */
package org.ambar.core.commons.filters;

import java.util.ArrayList;
import java.util.List;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;

/**
 * <p>
 * Clase utilizada en las expresiones de múltiples operandos
 * utilizando los operadores <pre>in</pre> o <pre>not in</pre>.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.PropertyCompareFilter
 */
public class MultipleFilter extends PropertyCompareFilter {
	private static final long serialVersionUID = 5963048510246219354L;
	private final MultipleOperator multipleOperator;
	private final List<FilterValue> values;

	/**
	 * Constructor propio del tipo de filtro que recibe un array de valores.
	 * @param pProperty			Operando izquierdo
	 * @param pMultipleOperator	Operador
	 * @param pValues			Operandos de la derecha
	 */
	public MultipleFilter(final String pProperty, final MultipleOperator pMultipleOperator,
			final Object... pValues) {
		this.setPropertyName(pProperty);
		this.multipleOperator = pMultipleOperator;
		this.values = new ArrayList<FilterValue>();
		for (final Object value : pValues) {
			this.values.add(new FilterValue(value));
		}
	}

	/**
	 * Constructor propio del tipo de filtro que recibe una lista de valores.
	 * @param pProperty			Operando izquierdo
	 * @param pMultipleOperator	Operador
	 * @param pValues			Operandos de la derecha
	 */
	public MultipleFilter(final String pProperty, final MultipleOperator pMultipleOperator,
			final List<?> pValues) {
		this.setPropertyName(pProperty);
		this.multipleOperator = pMultipleOperator;
		this.values = new ArrayList<FilterValue>();
		for (final Object value : pValues) {
			this.values.add(new FilterValue(value));
		}
	}

    /**
     * @return Retorna el valor del atributo multipleOperator.
     */
    public MultipleOperator getMultipleOperator() {
        return this.multipleOperator;
    }

    /**
     * @return Retorna el valor del atributo values.
     */
    public List<FilterValue> getValues() {
        return this.values;
    }

    /* (non-JSDoc)
      * @see org.ambar.core.commons.filters.PropertyCompareFilter#toString()
      */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("MultipleFilter [");
		if (this.getPropertyName() != null) {
			builder.append("getPropertyName()=");
			builder.append(this.getPropertyName());
			builder.append(", ");
		}
		if (this.multipleOperator != null) {
			builder.append("multipleOperator()=");
			builder.append(this.multipleOperator);
		}
		if (this.values != null) {
			builder.append("values=");
			builder.append(this.values);
		}
		builder.append("]");
		return builder.toString();
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.filters.helpers.FilterElement#build(MultipleFilter, Class<?>)
	 */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
