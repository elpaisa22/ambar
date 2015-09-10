/**
 * ambar-core-api [20/08/2011 00:08:13]
 */
package org.ambar.core.dto;

/**
 * <p>
 * Interface que debe ser implementada por todos los DTOs cuya entidad
 * necesite control de actualizaciones concurrentes.
 * </p>
 *
 * @author Sebastian
 */
public interface VersionableDTO {

	/**
	 * Retorna el número de Versión.
	 * @return int
	 */
	int getVersion();

	/**
	 * @param pVersion Establece el valor de pVersion al campo versión.
	 */
	void setVersion(int pVersion);
}
