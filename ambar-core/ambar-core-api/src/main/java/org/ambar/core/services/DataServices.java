/**
 * ambar-core-api [20/08/2011 00:20:05]
 */
package org.ambar.core.services;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultListDTO;
import org.ambar.core.dto.results.ResultObjectDTO;

/**
 * <p>Interfaz que deben implementar todos los servicios
 * de la capa que expone la funcionalidad del backend. Se remite a
 * operaciones de acceso a datos de lectura exclusivamente.</p>
 *
 * @author Sebastian
 *
 * @param <K> Tipo de dato de la clave primaria del DTO sobre el que opera el servicio
 * @param <D> Tipo de dato del DTO sobre el que opera el servicio
 * @param <T> Tipo de dato de la clave primaria de la BE sobre el que opera el servicio
 * @param <E> Tipo de dato de la BE sobre el que opera el servicio
 */
public interface DataServices<K, D extends DTO<K>, T, E extends Persistible<T>> {
	/**
	 * Busca entidad por clave primaria, la convierte a DTO y la retorna.
	 * @param pId Clave primaria del DTO de la entidad
	 * @param pRequestContext Contexto de la petición
	 * @return {@link ResultObjectDTO} Encapsula mensajes de la operación y el DTO de la entidad
	 */
	ResultObjectDTO<D> getById(K pId, RequestContext pRequestContext);

	/**
	 * Busca todas las entidades existentes de acuerdo al filtro, las convierte a DTOs y las retorna.
	 * @param pFilteringContext Condiciones de filtrado
	 * @param pRequestContext Contexto de la petición
	 * @return {@link ResultListDTO} Encapsula mensajes de la operación y la lista de DTOs
	 */
	ResultListDTO<D> getFilteredList(FilteringContext pFilteringContext, RequestContext pRequestContext);
}
