/**
 * ambar-core-api [19/08/2011 20:11:04]
 */
package org.ambar.core.dao;

import org.ambar.core.be.Persistible;

/**
 * <p>Insertar descripción funcional.</p>
 * @author Sebastian
 *
 * @see org.ambar.core.be.Persistible
 *
 * @param <T> Clave primaria de la entidad
 * @param <E> Entidad de negocio que implementa <i>Persistible</i>
 */
public interface CrudDAO <T, E extends Persistible<T>> extends DataDAO<T, E> {

	/**
	 * Actualiza un registro pasandoselo por parámetro.
	 * @param pEntity DomainObject
	 */
	void update(E pEntity);

	/**
	 * Inserta un registro nuevo pasandoselo por parámetro.
	 * @param pEntity DomainObject
	 */
	void insert(E pEntity);

	/**
	 * Elimina un registro pasandoselo por parámetro.
	 * @param pEntity DomainObject
	 */
	void remove(E pEntity);
}
