/**
 * ambar-core-impl [12/10/2011 08:29:07]
 */
package org.ambar.core.commons.mapping.helpers;

/**
 * <p>
 * Helper que resuelve el mapeo de objetos asociados.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface MappingHelper {

	/**
	 * Método que realiza el mapeo de los objetos asociados.
	 * @param pObjectA Objeto destino
	 * @param pObjectB Objeto origen
	 */
	void mapAssociatedObjects(Object pObjectA, Object pObjectB);

}
