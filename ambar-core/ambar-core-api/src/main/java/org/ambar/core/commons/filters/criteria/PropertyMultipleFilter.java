/**
 * ambar-core-api [11/10/2011 01:53:09]
 */
package org.ambar.core.commons.filters.criteria;

import java.util.Arrays;
import java.util.List;

import org.ambar.core.commons.filters.MultipleFilter;
import org.ambar.core.commons.filters.MultipleOperator;

/**
 * <p>
 * Wrapper de {@link PropertyFilter} con métodos que retornan expresiones
 * de tipo <i>IN</i> y <i>NOT IN</i> configuradas.
 * </p>
 *
 * @author Sebastian
 *
 */
public class PropertyMultipleFilter extends PropertyFilter {
	/**
	 * Constructor por default.
	 * @param pProperty Operando izquierdo de la expresión
	 */
	public PropertyMultipleFilter(final String pProperty) {
		super(pProperty);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad in (value1, value2 ..., valueN)</pre>
	 * a partir de un array de valores.
	 * @param pValues Lista de valores de la expresión
	 * @return {@link MultipleFilter}
	 */
	public MultipleFilter in(final Object... pValues) {
		return this.in(Arrays.asList(pValues));
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad in (value1, value2 ..., valueN)</pre>
	 * a partir de una lista de valores.
	 * @param pValues Lista de valores de la expresión
	 * @return {@link MultipleFilter}
	 */
	public MultipleFilter in(final List<?> pValues) {
		return new MultipleFilter(this.getProperty(), MultipleOperator.In, pValues);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad not in (value1, value2 ..., valueN)</pre>
	 * a partir de un array de valores.
	 * @param pValues Lista de valores de la expresión
	 * @return {@link MultipleFilter}
	 */
	public MultipleFilter notIn(final Object... pValues) {
		return this.notIn(Arrays.asList(pValues));
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad not in (value1, value2 ..., valueN)</pre>
	 * a partir de una lista de valores.
	 * @param pValues Lista de valores de la expresión
	 * @return {@link MultipleFilter}
	 */
	public MultipleFilter notIn(final List<?> pValues) {
		return new MultipleFilter(this.getProperty(), MultipleOperator.NotIn, pValues);
	}
}
