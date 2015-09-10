/**
 * ambar-core-api [11/10/2011 01:44:18]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>Expresión binaria vacía.</p>
 *
 * @author Sebastian
 *
 */
public class EmptyBinaryFilter {

	/**
	 * Método que retorna una expresión binaria vacía.
	 * @param pProperty Operando de la expresión
	 * @return {@link PropertyBinaryFilter}
	 */
	public PropertyBinaryFilter property(final String pProperty) {
		return new PropertyBinaryFilter(pProperty);
	}
}
