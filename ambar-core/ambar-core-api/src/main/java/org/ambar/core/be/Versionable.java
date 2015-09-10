/**
 * ambar-core-api [10/08/2011 23:01:56]
 */
package org.ambar.core.be;

/**
 * <p>
 * Interfaz que deben implementar todas las entidades de negocio donde se
 * desee controlar actualizaciones concurrentes.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface Versionable {

	/**
	 * Getter de la propiedad Versión.
	 * @return int
	 */
	int getVersion();

	/**
	 * Setter de la propiedad Versión.
	 * @param pVersion Valor de la propiedad
	 */
	void setVersion(int pVersion);
}
