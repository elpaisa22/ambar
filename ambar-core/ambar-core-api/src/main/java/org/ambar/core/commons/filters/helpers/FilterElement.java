/**
 * ambar-core-api [11/10/2011 01:56:08]
 */
package org.ambar.core.commons.filters.helpers;

/**
 * <p>
 * Interfaz a ser implementada por las subclases de {@link org.ambar.core.commons.filters.Filter}
 * que permitirá la invocación del helper que construye la expresión de filtrado.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface FilterElement {
	/**
     * Método que permitirá la invocación al helper que construye la expresión.
     * @param pBuilder Helper
	 * @param pObjectClass 
     */
    void build(FilterExpressionBuilder pBuilder, Class<?> pObjectClass);
}
