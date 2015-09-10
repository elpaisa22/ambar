/**
 * ambar-core-api [11/10/2011 01:49:34]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>
 * Expresión ternaria vacía.
 * </p>
 *
 * @author Sebastian
 *
 */
public class EmptyTernanyFilter implements EmptyFilter {

	/**
	 * Método que retorna una expresión ternaria vacía.
	 * @param pProperty Operando de la expresión
	 * @return {@link PropertyTernaryFilter}
	 */
	public PropertyTernaryFilter property(final String pProperty) {
		return new PropertyTernaryFilter(pProperty);
	}
}
