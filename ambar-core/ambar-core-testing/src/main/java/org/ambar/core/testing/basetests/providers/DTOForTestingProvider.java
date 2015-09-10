/**
 * ambar-core-testing [17/09/2011 02:04:53]
 */
package org.ambar.core.testing.basetests.providers;

import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.dto.DTO;

/**
 * <p>
 * Funcionalidad que deben implementar los <i>providers</i> de
 * DTOs utilizados en los tests de Services.
 * </p>
 *
 * @author Sebastian
 *
 * @param <K> Tipo de dato de la clave primaria del DTO
 * @param <D> Tipo de dato del DTO
 *
 * @see org.ambar.core.dto.DTO
 */
public abstract class DTOForTestingProvider<K, D extends DTO<K>> {


	/**
	 * Método que instancia y retorna un DTO con sus propiedades con
	 * valores para test de funcionalidad CRUD.
	 * @return {@link DTO} Instancia del DTO
	 */
	public abstract D createDefaultDTO();


	/**
	 * Método que instancia y retorna los criterios de filtro para testear
	 * la funcionalidad de filtros dinámicos. Si retorna <I>null</I> no realiza el test de filtros.
	 *
	 * @return {@link FilteringContext} Objeto que encapsula los criterios de
	 *         filtrado, paginación y ordenamiento
	 *
	 */
	public abstract FilteringContext createFilterContext();


	/**
	 * Método que retorna un array de entidades de negocio para testear la
	 * funcionalidad de filtros dinámicos.
	 *
	 * @return {@link DTO}[] Lista de entidades de negocio para
	 *         testear filtros dinámicos
	 */
	public abstract D[] createDTOsForFilter();


	/**
	 * Método que retorna la cantidad de registros a obtener cuando se
	 * invoque a {@link org.ambar.core.services.DataServices#getFilteredList} con el filtro provisto por
	 * este <i>provider</i>.
	 *
	 * @return {@link Integer} Cantidad de registros que debe retornar el
	 *         test de {@link org.ambar.core.dao.DataServices#getFilteredList}
	 */
	public abstract int filteredListResult();



}
