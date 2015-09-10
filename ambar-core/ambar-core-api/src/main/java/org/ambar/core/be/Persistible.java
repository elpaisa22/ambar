/**
 * ambar-core-api [10/08/2011 22:59:23]
 */
package org.ambar.core.be;

/**
 * <p>
 * Interfaz que deben implementar todas las entidades de negocio y que determina si una clase
 * puede o no ser persistida. La firma de los métodos de los servicios de persistencia
 * esperan objetos que implementen esta interfaz.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T> Tipo de dato de la clave primaria de la entidad
 */
public interface Persistible<T> {

	/**
	 * Getter de la clave primaria de la entidad.
	 * @return T
	 */
	T getId();

	/**
	 * Setter de la clave primaria de la entidad.
	 * @param pId Valor de la propiedad
	 */
	void setId(T pId);
}
