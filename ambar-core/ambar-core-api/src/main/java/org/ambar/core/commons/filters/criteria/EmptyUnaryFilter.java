/**
 * ambar-core-api [11/10/2011 01:50:32]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>
 * Expresión unaria vacía.
 * </p>
 *
 * @author Sebastian
 *
 *
 */
public class EmptyUnaryFilter implements EmptyFilter {

	/**
	 * Método que retorna una expresión unaria vacía.
	 * @param pProperty Operando de la expresión
	 * @return {@link PropertyUnaryFilter}
	 */
	public PropertyUnaryFilter property(final String pProperty) {
		return new PropertyUnaryFilter(pProperty);
	}
}
