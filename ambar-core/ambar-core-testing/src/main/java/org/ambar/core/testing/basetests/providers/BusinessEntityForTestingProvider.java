/**
 * ambar-core-impl [25/08/2011 09:16:40]
 */
package org.ambar.core.testing.basetests.providers;

import java.util.Map;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.testing.commons.ValidationPair;
import org.ambar.core.testing.commons.ValidationTouple;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Funcionalidad que deben implementar los <i>providers</i> de
 * entidades de negocio utilizados en los tests de DAOs y BOs</p>.
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad de negocio
 * @param <E> Entidad de negocio
 */
public abstract class BusinessEntityForTestingProvider<T, E extends Persistible<T>> {
	@Autowired
	private ServiceLocator beanServiceLocator;

	/**
	 * Método que instancia y retorna la entidad de negocio sobre la cual
	 * hacer el test.
	 *
	 * @return {@link Persistible} Entidad de negocio configurada por
	 *         default
	 */
	public abstract E createDefaultBussinessEntity();

	/**
	 * Método que recibe una instancia de una entidad de negocio y la
	 * retorna modificada.
	 *
	 * @param pBussinessEntity Entidad de negocio a modificar
	 * @return {@link Persistible} Entidad de negocio modificada
	 */
	public abstract E modifyBussinessEntity(E pBussinessEntity);

	/**
	 * Método que retorna un array de entidades de negocio para testear la
	 * funcionalidad de filtros dinámicos.
	 *
	 * @return {@link Persistible}[] Lista de entidades de negocio para
	 *         testear filtros dinámicos
	 */
	public abstract E[] createBussinessEntitiesForFilter();

	/**
	 * Método que instancia y retorna los criterios de filtro para testear
	 * la funcionalidad de filtros dinámicos. Si retorna <I>null</I> no realiza el test de filtros.
	 *
	 * @return {@link QueryPredicate} Objeto que permite construir la
	 * sentencia de una consulta a la base con JPQL.
	 *
	 */
	public abstract QueryPredicate createQueryPredicate();

	/**
	 * Método que retorna la cantidad de registros a obtener cuando se
	 * invoque a {@link org.ambar.core.dao.DataDAO#getFilteredList} con el filtro provisto por
	 * este <i>provider</i>. Este método no tiene importancia si se retorna <I>null</I>
	 * en el metodo <I>createQueryPredicate()</I>
	 *
	 * @return {@link Integer} Cantidad de registros que debe retornar el
	 *         test de {@link org.ambar.core.dao.DataDAO#getFilteredList}
	 */
	public abstract int filteredListResult();


	/**
	 * Método que retorna un mapa por metodos array de transacciones y un par
	 * ordenado del tipo [entidad de negocio, error de validacion (Boolean)],
	 * para testear las validaciones y reglas de negocio.
	 *
	 * @return  {@link Map} < {@link String}, {@link Pair}>
	 * 		Como indice el nombre de la transacción y como value la
	 * Lista de entidades de negocio y si se esperan errores en la validación.
	 */
	public abstract Map<String, ValidationPair<E>[]> createBussinessEntitiesForValidation();

	/**
	 * Método que retorna una tupla de elementos
	 * de la forma {Validator, Profile, Entity, ErrorExpected}
	 * para testear las validaciones y reglas de negocio.
	 *
	 * @return {@link ValidationBOStruct}[] de la forma {Validator, Profile, Entity, ErrorExpected}
	 */
	public abstract ValidationTouple[] createBussinessEntitiesForValidationByValidator();


	/**
	 * @param pBeanServiceLocator Fija el valor de pBeanServiceLocator al
	 *        campo beanServiceLocator
	 */
	public void setBeanServiceLocator(ServiceLocator pBeanServiceLocator) {
		beanServiceLocator = pBeanServiceLocator;
	}

	/**
	 * @return Devuelve el atributo beanServiceLocator.
	 */
	protected ServiceLocator getApplicationContext() {
		return beanServiceLocator;
	}
}
