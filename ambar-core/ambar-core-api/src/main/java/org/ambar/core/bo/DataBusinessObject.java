/**
 * ambar-core-api [19/08/2011 19:40:23]
 */
package org.ambar.core.bo;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.commons.filters.QueryPredicate;



/**
 * <p>Interfaz para las entidades que no poseen operaciones CRUD.</p>
 *
 * @author Sebastian
 *
 * @param <T> Parámetro genérico que representa al tipo de dato de la clave
 *        primaria de la entidad
 * @param <E> Parámetro genérico que representa a la entidad
 */
public interface DataBusinessObject <T, E extends Persistible<T>> {

	/**
	 * Método que busca una entidad por clave primaria. Retorna <code>null</code>
	 * si la entidad no fue encontrada.
	 * @param pId Clave primaria a buscar.
	 * @return {@link Persistible}
	 */
	E getById(T pId);


	/**
	 * Método que retorna las entidades existentes de acuerdo a los parámetros de filtrado,
	 * ordenamiento y paginación.
     * @param pQueryPredicate Clase que contiene la información con la claúsula
     *          <i>WHERE</i>, la claúsula <i>ORDER BY</i> y la información de paginado
	 * @return {@link FilteredList}
	 */
	FilteredList<E> getFilteredList(QueryPredicate pQueryPredicate);
}
