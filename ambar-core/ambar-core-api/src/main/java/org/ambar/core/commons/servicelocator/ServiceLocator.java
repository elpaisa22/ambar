/**
 * ambar-core-api [20/08/2011 00:13:55]
 */
package org.ambar.core.commons.servicelocator;

/**
 * <p>Interfaz a implementar que ofrece la funcionalidad de
 * localizar servicios en el contexto de ejecución.</p>
 *
 * @author Sebastian
 *
 */
public interface ServiceLocator {

	/**
	 * Método que retorna un servicio por el "id" que tiene
	 * en el contexto IoC.
	 * @param pServiceName	Nombre o "id" del servicio
	 * @return {@link Object} Instancia del servicio
	 */
	Object getService(String pServiceName);
}
