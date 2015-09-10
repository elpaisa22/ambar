/**
 * ambar-core-api [19/08/2011 20:06:57]
 */
package org.ambar.core.dao;

import java.util.List;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.commons.filters.QueryPredicate;

/**
 * <p>Interfaz que implementan los daos que no poseen operaciones CRUD.</p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad
 * @param <E> Entidad de negocio que implementa {@link Persistible}
 */
public interface DataDAO<T, E extends Persistible<T>> {

	/**
	 * <p>
	 * Busca todas las entidades existentes de acuerdo al filtro.
	 * </p>
	 *
	 * @param pQueryPredicate Clase que contiene la información con la claúsula
     *          <i>WHERE</i>, la claúsula <i>ORDER BY</i> y la información de paginado
	 * @return List<E>
	 */
	FilteredList<E> getFilteredList(QueryPredicate pQueryPredicate);

	/**
	 * <p>
	 * Busca entidad por clave primaria.
	 * </p>
	 *
	 * @param	pId					Clave primaria de la entidad
	 * @return	{@link Persistible}	Entidad de negocio obtenida
	 */
	E getById(T pId);

	/**
	 *
	 * Obtiene todos los registros.
	 * @return List<DomainObject>
	 */
	List<E> getAll();

}

