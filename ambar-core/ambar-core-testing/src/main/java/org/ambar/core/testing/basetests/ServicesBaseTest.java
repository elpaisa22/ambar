/**
 * ambar-core-testing [17/09/2011 02:09:05]
 */
package org.ambar.core.testing.basetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultListDTO;
import org.ambar.core.dto.results.ResultObjectDTO;
import org.ambar.core.dto.results.ResultVoidDTO;
import org.ambar.core.mappers.GenericMapper;
import org.ambar.core.services.CrudServices;
import org.ambar.core.testing.basetests.providers.DTOForTestingProvider;
import org.ambar.core.testing.exceptions.InvalidDTOException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Clase base para testear la capa de servicios para una determinada
 * entidad.
 * </p>
 *
 * @author Sebastian
 * @param <K> Tipo de dato de la clave primaria del DTO
 * @param <D> Tipo de dato del DTO
 * @param <T> Tipo de dato de la clave primaria de la entidad de negocio
 * @param <E> Tipo de dato de la entidad de negocio
 */
public abstract class ServicesBaseTest<K, D extends DTO<K>, T, E extends Persistible<T>>
extends AbstractTransactionalJUnit4SpringContextTests {

private static final Logger LOG = LoggerFactory.getLogger(ServicesBaseTest.class);

private CrudServices<K, D, T, E> service;

private GenericMapper<E, D> mapper;

private DTOForTestingProvider<K, D> provider;
private RequestContext requestContext;


/**
 * Inicialización de objetos y parámetros necesarios para el
 * test.
 */
@Before
public void setUp() {
	this.requestContext = new RequestContext();
	this.requestContext.setUser("AMBAR_TEST");
	this.requestContext.setLocale("es_AR");
}


/**
 * Test del método {@link org.ambar.core.services.CrudServices#insert}, se pretende testear
 * completamente los mapeos <i>dozer</i>, validaciones y persistencia.
 */
@Test
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void testInsertAndGetById() {
	D dto = provider.createDefaultDTO();

	//se asegura que el DTO a insertar tenga todos sus campos seteados
	assertDTOisFull(dto, null, "antes de insert()");

	LOG.info("Insertando DTO");
	LOG.info("DTO: " + dto);

	LOG.info("Preparando DTO");
	//Prepara el DTO para insertar
	this.prepareDTO(dto);

	LOG.info("Insertando DTO");
	//la entidad se inserta
	ResultVoidDTO<D> resultInsert = service.insert(dto, requestContext);

	//Log de errores en el insert
	if (resultInsert.hasErrors()) {
		LOG.info("Errores en el insert.");
	} else {
		LOG.info("El DTO se inserto correctamente.");
	}
	assertFalse("Errores en el insert: " + resultInsert.getMessages(), resultInsert.hasErrors());

	LOG.info("GetById del DTO");
	LOG.info("Id del DTO: " + dto.getId());
	//Se obtiene el DTO recientemente guardado
	ResultObjectDTO<D> resultGet = service.getById(dto.getId(), requestContext);

	assertFalse("Errores en la comprobación del insert del DTO", insertHasErrors(resultGet.getResult()));

	//Log de errores en el insert
	if (resultGet.hasErrors()) {
		LOG.info("Errores en el GetById.");
	} else {
		LOG.info("El DTO se obtuvo correctamente.");
	}

	assertFalse("Errores en el getById: " + resultGet.getMessages(), resultGet.hasErrors());
	assertFalse("Errores en la comprobación del insert del DTO", getByIdHasErrors(dto));
}

/**
 * Método que permite realizar comprobaciones sobre el DTO
 * insertado. La implementación default retorna que no hubo errores.
 * @param	pDTO	DTO
 * @return	{@link Boolean}		Resultado de la comprobación
 */
protected abstract boolean insertHasErrors(D pDTO);


/**
 * Método que permite realizar comprobaciones sobre el DTO
 * que se obtiene luego del getById. La implementación default retorna que no hubo errores.
 * @param	pDTO	DTO
 * @return	{@link Boolean}		Resultado de la comprobación
 */
protected abstract boolean getByIdHasErrors(D pDTO);


/**
 * Método que será utilizado para persistir objetos asociados al
 * DTO cuyo Service se está testeando y de esta manera
 * garantizar su consistencia.
 * @param	pDTO	DTO
 */
protected abstract void prepareDTO(D pDTO);


/**
 * Comprueba que el DTO devuelto por createDefaultDTO tenga todos los campos seteados.
 *
 * @param pDTO DTO
 * @param pMasterDTO Master del DTO, null si no tiene master
 * @param pMethodName Nombre del metodo
 */
protected void assertDTOisFull(Object pDTO, Object pMasterDTO, String pMethodName) {

	if (!(pDTO instanceof DTO<?>)) {
		throw new InvalidDTOException("DTO: " + "<" + pDTO.getClass().getSimpleName()
									  +  ">" + " no es del tipo DTO");
	}

	String dtoName = pDTO.getClass().getSimpleName();

	for (Field field : pDTO.getClass().getDeclaredFields()) {
		field.setAccessible(true);

		try {

			Object fieldValue = field.get(pDTO);

			if 	(fieldValue == null
				&& !isIdField(field)
				&& !isDescripcionEnum(field, pDTO)
				&& !isMasterIdField(field, pMasterDTO)) {
					throw new InvalidDTOException("Campo "
						+ "<" + field.getName() + ">" + " sin valor seteado para"
						+ " <" + dtoName + ">" + " , " + pMethodName);
			}


		} catch (IllegalArgumentException e) {
			throw new InvalidDTOException(e);
		} catch (IllegalAccessException e) {
			throw new InvalidDTOException(e);
		} catch (SecurityException e) {
			throw new InvalidDTOException(e);
		}

	}

}


/**
 * @param pField {@link Field}
 * @return true si el campo es un identificador del tipo Long
 */
private boolean isIdField(Field pField) {
	return (pField.getName().startsWith("id") && pField.getType().equals(Long.class));
}

/**
 * @param pField {@link Field}
 * @param pMasterDTO {@link String}
 * @return true si el campo es un identificador del tipo Integer
 */
private boolean isMasterIdField(Field pField, Object pMasterDTO) {

	if (pMasterDTO == null) {
		return false;
	}

	String dtoName = pMasterDTO.getClass().getSimpleName();

	String fieldName = "id" + dtoName.substring(0, 1).toUpperCase() + dtoName.substring(1);

	int indexDTO = fieldName.indexOf("DTO", 0);

	fieldName = fieldName.substring(0, indexDTO);

	return (pField.getName().equals(fieldName) && pField.getType().equals(Integer.class));
}


/**
 * @param pField {@link Field}
 * @param pDto DTO
 * @return true si corresponde a la descripcion de un enum
 *
 * @throws IllegalAccessException IllegalAccessException
 */
private boolean isDescripcionEnum(Field pField, Object pDto)
	throws IllegalAccessException {

	String desc = "descripcion";

	if (pField.getName().length() > desc.length() &&  pField.getName().startsWith(desc)) {

		String enumName = pField.getName().substring(desc.length());

		Field fieldEnum = null;

		try {
			fieldEnum = pDto.getClass().getDeclaredField("id" + enumName);
			fieldEnum.setAccessible(true);
		} catch (NoSuchFieldException e) {
			return false; //no existe el campo codigo para el enum
		}

		Object enumCodigoValue = fieldEnum.get(pDto);

		if (enumCodigoValue == null) {
			throw new InvalidDTOException("Campo del enum"
					+ "<" + fieldEnum.getName() + ">" + " no tiene valor seteado para "
					+ "<" + pDto.getClass().getName() + ">");
		}

		//codigo enum vino seteado, pero descripcion no, esta OK
		return true;

	}
	//el campo no forma parte del codigo/descripcion de un enum
	return false;
}


/**
 * Test del método {@link org.ambar.core.mappers.EntityMapper#map}, se pretende testear
 * completamente los mapeos <i>dozer</i>, validaciones y persistencia.
 */
@Test
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void testMapper() {
	D dto = provider.createDefaultDTO();

	//se asegura que el DTO a insertar tenga todos sus campos seteados
	assertDTOisFull(dto, null, "antes de testear el mapping()");

	LOG.info("Testing Mapper DTO");
	LOG.info("DTO: " + getDTOClass().getName());
	LOG.info("Clave primaria Tipo: " + getDTOPrimaryKeyClass().getName());
	LOG.info("DTO: " + dto);
	//Mapea el DTO a una BE
	E businessEntity = mapper.mapFromB(dto);
	LOG.info("BusinessEntity: " + businessEntity);
	assertTrue("El DTO no se ha mapeado correctamente", entityAndDTOAreEquals(dto, businessEntity));

}

/**
 * Método que compara un DTO y una BE y verifica si son "iguales".
 * Por defecto debe retornar True.
 *
 * @param pDTO DTO
 * @param pEntity Entidad
 * @return Boolean True si el DTO y la BE son iguales
 */
public abstract Boolean entityAndDTOAreEquals(D pDTO, E pEntity);


/**
 * Test del método {@link org.ambar.core.services.DataServices#getFilteredList}, se pretende testear
 * el filtro de entidades para el servicio.
 */
@Test
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void testFilteredList() {
	FilteringContext filteringContext = provider.createFilterContext();
	if (filteringContext != null) {
		LOG.info("DTO: " + getDTOClass().getName());
		LOG.info("Clave primaria Tipo: " + getDTOPrimaryKeyClass().getName());

		D[] dtos = provider.createDTOsForFilter();

		for (D dto : dtos) {
			prepareDTO(dto);
			LOG.debug("Insertar DTO: " + dto.toString());
			ResultVoidDTO<D> resultRelatedEntities = service.insert(dto, this.requestContext);
			if (resultRelatedEntities.hasErrors()) {
				LOG.error(resultRelatedEntities.getMessages().toString());
			}
			assertEquals("Existen errores en el INSERT de los DTO a Filtrar.",
						  false,
						  resultRelatedEntities.hasErrors());
		}
		LOG.debug("Condiciones de filtrado: " + filteringContext.getFilter().toString());
		LOG.debug("Condiciones de paging: " + filteringContext.getPager().toString());

		ResultListDTO<D> resultDTO =
				service.getFilteredList(filteringContext, this.requestContext);

		int rowsExpected;
		rowsExpected = provider.filteredListResult();
		assertEquals("Incorrecta cantidad de registros obtenidos en el método 'GetFilteredList'",
				rowsExpected, resultDTO.getCompleteListSize());
	}
}


/**
 * Método que determina el tipo de dato del parámetro genérico
 * correspondiente al DTO.
 *
 * @return {@link Class}
 */
@SuppressWarnings("rawtypes")
private Class getDTOClass() {
	Type entityRawType =
			((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];

	return (Class) entityRawType;
}

/**
 * Método que determina el tipo de dato del parámetro genérico
 * correspondiente a la clave primaria del DTO.
 *
 * @return {@link Class}
 */
@SuppressWarnings("rawtypes")
private Class getDTOPrimaryKeyClass() {
	Type entityIdRawType =
			((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	return (Class) entityIdRawType;
}

/**
 * @return Retorna el valor del atributo provider.
 */
protected DTOForTestingProvider<K, D> getProvider() {
	return provider;
}

/**
 * @param pProvider Establece el valor del atributo provider.
 */
protected void setProvider(DTOForTestingProvider<K, D> pProvider) {
	this.provider = pProvider;
}


/**
 * @return Retorna el valor del atributo requestContext.
 */
protected RequestContext getRequestContext() {
	return requestContext;
}


/**
 * @param pRequestContext Establece el valor del atributo requestContext.
 */
protected void setRequestContext(RequestContext pRequestContext) {
	this.requestContext = pRequestContext;
}

/**
 * Método que devuelve una instancia del servicio a testear.
 * @return	{@link CrudServices} Instancia del servicio a testear
 */
protected CrudServices<K, D, T, E> getService() {
	return this.service;
}


/**
 * @param pService Establece el valor del atributo serviceImplementation.
 */
protected void setService(CrudServices<K, D, T, E> pService) {
	this.service = pService;
}


/**
 * @return Retorna el valor del atributo mapper.
 */
public GenericMapper<E, D> getMapper() {
	return mapper;
}


/**
 * @param pMapper Establece el valor del atributo mapper.
 */
public void setMapper(GenericMapper<E, D> pMapper) {
	this.mapper = pMapper;
}

}
