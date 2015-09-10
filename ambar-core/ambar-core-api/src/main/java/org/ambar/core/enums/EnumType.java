/**
 * ambar-core-api [20/08/2011 22:19:30]
 */
package org.ambar.core.enums;

/**
 * Interfaz para ser implementada por los enums.
 *
 * @author Sebastian
 */
public interface EnumType {

	/**
	 * @return {@link String} Devuelve el valor del enum
	 */
	String getValor();

	/**
	 * @return {@link String} Devuelve la descripcion del enum
	 */
	String getDescripcion();

}
