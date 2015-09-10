/**
 * ambar-core-api [20/08/2011 00:04:05]
 */
package org.ambar.core.dto;

import java.io.Serializable;

/**
 * <p>
 * Interface que debe ser implementada por todos los DTOs.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T> Tipo de dato de la clave primaria de la entidad
 */
public interface DTO<T> extends Serializable {

	/**
	 * Devuelve el valor del Id.
	 * @return T
	 */
	T getId();

	/**
	 * @param pId Fija el valor de pId al campo Id.
	 */
	void setId(T pId);
}

