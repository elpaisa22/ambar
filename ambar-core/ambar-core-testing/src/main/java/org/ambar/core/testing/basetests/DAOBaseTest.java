/**
 * ambar-core-impl [25/08/2011 09:05:20]
 */
package org.ambar.core.testing.basetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.be.Versionable;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.dao.CrudDAO;
import org.ambar.core.testing.basetests.providers.BusinessEntityForTestingProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Clase base para testear persistencia de la funcionalidad <i>CRUD</i>
 * para una determinada entidad de negocio.</p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad de negocio
 * @param <E> Entidad de negocio
 */
public abstract class DAOBaseTest <T, E extends Persistible<T>> extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOG = LoggerFactory.getLogger(DAOBaseTest.class);

	private BusinessEntityForTestingProvider<T, E> provider;
	private CrudDAO<T, E> dao;

	/**
	 * @return Retorna el valor del atributo provider.
	 */
	protected BusinessEntityForTestingProvider<T, E> getProvider() {
		return provider;
	}

	/**
	 * @param pProvider Establece el valor del atributo provider.
	 */
	protected void setProvider(BusinessEntityForTestingProvider<T, E> pProvider) {
		provider = pProvider;
	}

	/**
	 * @return Retorna el valor del atributo dao.
	 */
	protected CrudDAO<T, E> getDao() {
		return dao;
	}

	/**
	 * @param pDao Establece el valor del atributo dao.
	 */
	protected void setDao(CrudDAO<T, E> pDao) {
		dao = pDao;
	}

	/**
	 * Método que será utilizado para persistir objetos asociados a
	 * la entidad de negocio cuyo DAO se está testeando y de esta manera
	 * garantizar su consistencia.
	 * @param	pBussinessEntity	Entidad de negocio
	 */
	protected abstract void prepareBussinessEntity(E pBussinessEntity);

	/**
	 * Método que puede ser utilizado para mostrar en el log el contenido
	 * de una entidad de negocio persistida.
	 * @param pBussinessEntity Entidad de negocio a mostrar
	 */
	protected abstract void showGettedBussinessEntity(E pBussinessEntity);

	/**
	 * Método que permite realizar comprobaciones sobre la entidad de negocio
	 * insertada. La implementación default (FALSE) retorna que no hubo errores.
	 * @param	pBussinessEntity	Entidad de negocio
	 * @return	{@link Boolean}		Resultado de la comprobación
	 */
	protected abstract boolean insertHasErrors(E pBussinessEntity);

	/**
	 * Método que permite realizar comprobaciones sobre la entidad de negocio
	 * actualizada. La implementación default (FALSE) retorna que no hubo errores.
	 * @param	pBussinessEntity	Entidad de negocio
	 * @return	{@link Boolean}		Resultado de la comprobación
	 */
	protected abstract boolean updateHasErrors(E pBussinessEntity);

	/**
	 * Método que determina el tipo de dato del parámetro genérico
	 * correspondiente a la clave primaria de la entidad de negocio.
	 *
	 * @return {@link Class}
	 */
	@SuppressWarnings("rawtypes")
	private Class getBussinessEntityPrimaryKeyClass() {
		Type entityIdRawType =
				((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		return (Class) entityIdRawType;
	}

	/**
	 * Método que determina el tipo de dato del parámetro genérico
	 * correspondiente a la entidad de negocio.
	 *
	 * @return {@link Class}
	 */
	@SuppressWarnings("rawtypes")
	private Class getBussinessEntityClass() {
		Type entityRawType =
				((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

		return (Class) entityRawType;
	}

	/**
	 * Test base de las acciones CRUD para una entidad.
	 */
	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void testCRUDActions() {
		LOG.info("Entidad: " + getBussinessEntityClass().getName());
		LOG.info("Clave primaria Tipo: " + getBussinessEntityPrimaryKeyClass().getName());

		LOG.debug("Insert test");
		E bussinessEntity = provider.createDefaultBussinessEntity();
		prepareBussinessEntity(bussinessEntity);
		dao.insert(bussinessEntity);
		assertFalse("BE insertada con errores", insertHasErrors(bussinessEntity));

		E insertedBussinessEntity = dao.getById(bussinessEntity.getId());
		showGettedBussinessEntity(insertedBussinessEntity);
		assertNotNull("BE no insertada correctamente", insertedBussinessEntity);

		LOG.debug("Update test");
		E bussinessEntityForUpdate = provider.modifyBussinessEntity(insertedBussinessEntity);
		dao.update(bussinessEntityForUpdate);
		E updatedBussinessEntity = dao.getById(bussinessEntity.getId());
		assertFalse("BE actualizada con errores", updateHasErrors(updatedBussinessEntity));

		LOG.debug("Delete test");
		E bussinessEntityForDelete = dao.getById(bussinessEntityForUpdate.getId());
		if (!(bussinessEntityForDelete instanceof Versionable)) {
			Assert.fail("La entidad no implementa Versionable,"
					+ " fue creada incorrectamente o no corresponde que sea testeada.");
			return;
		}
	}

	/**
	 * Test base de la funcionalidad para obtener lista de entidades por filtro.
	 */
	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void testFilteredList() {

		QueryPredicate queryPredicate = provider.createQueryPredicate();
		if (queryPredicate != null) {
			LOG.info("Entidad: " + getBussinessEntityClass().getName());
			LOG.info("Clave primaria Tipo: " + getBussinessEntityPrimaryKeyClass().getName());

			E[] bussinessEntities = provider.createBussinessEntitiesForFilter();

			for (E bussinessEntity : bussinessEntities) {
				prepareBussinessEntity(bussinessEntity);
				LOG.debug("Insertar entidad: " + bussinessEntity.toString());
				dao.insert(bussinessEntity);
			}
			LOG.debug("Condiciones de filtrado: " + queryPredicate.toString());
			FilteredList<E> entities = dao.getFilteredList(queryPredicate);

			int rowsExpected;
			rowsExpected = provider.filteredListResult();
			assertEquals("Incorrecta cantidad de registros obtenidos",
					rowsExpected, entities.getFilteredList().size());
		}
	}
}
