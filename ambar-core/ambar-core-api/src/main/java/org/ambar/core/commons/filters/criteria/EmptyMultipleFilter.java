/**
 * ambar-core-api [11/10/2011 01:47:39]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>
 * Expresión de múltiples operandos vacía.
 * </p>
 *
 * @author Sebastian
 *
 *
 */
public class EmptyMultipleFilter implements EmptyFilter {

	/**
	 * Método que retorna una expresión de múltiples operandos vacía.
	 * @param pProperty Operando de la expresión
	 * @return {@link PropertyMultipleFilter}
	 */
	public PropertyMultipleFilter property(final String pProperty) {
		return new PropertyMultipleFilter(pProperty);
	}
}
