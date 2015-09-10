/**
 * ambar-core-testing [17/09/2011 01:46:25]
 */
package org.ambar.core.testing.basetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import java.util.Map;

import org.ambar.core.be.Persistible;
import org.ambar.core.bo.impl.CrudBusinessObjectImpl;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.testing.basetests.providers.BusinessEntityForTestingProvider;
import org.ambar.core.testing.commons.ValidationPair;
import org.ambar.core.testing.commons.ValidationTouple;
import org.ambar.core.testing.exceptions.InvalidBusinessObjectClassForTestException;
import org.ambar.core.validation.Validator;
import org.ambar.core.validation.impl.ValidationExecutionDefaultImpl;
import org.ambar.core.validation.results.ValidationResults;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * <p>
 * Clase base para testear validaciones para un determinado objeto de
 * negocio.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad de negocio
 * @param <E> Entidad de negocio
 */
public abstract class BusinessObjectBaseTest<T, E extends Persistible<T>>
extends AbstractTransactionalJUnit4SpringContextTests {

private static final Logger LOG = LoggerFactory.getLogger(BusinessObjectBaseTest.class);

@Autowired
private ServiceLocator serviceLocator;

private Class<? extends CrudBusinessObjectImpl<T, E>> boClass;
private BusinessEntityForTestingProvider<T, E> provider;

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
 * Test base para validaciones y reglas de negocio.
 */
@Test
public void testValidations() {
	LOG.debug("Test de Validaciones");

	CrudBusinessObjectImpl<T, E> bo = null;
	try {
		bo = this.boClass.newInstance();
	} catch (InstantiationException e) {
		LOG.error(e.getMessage(), e);
		throw new InvalidBusinessObjectClassForTestException(e.getMessage(), e);
	} catch (IllegalAccessException e) {
		LOG.error(e.getMessage(), e);
		throw new InvalidBusinessObjectClassForTestException(e.getMessage(), e);
	}

	// Crea un ejecutor de validaciones
	ValidationExecutionDefaultImpl validationExcecution =
			new ValidationExecutionDefaultImpl(bo, serviceLocator);

	// Obtiene las entidades a validar
	Map<String, ValidationPair<E>[]> entities = provider.createBussinessEntitiesForValidation();
	if (entities != null) {

		// Recorro la coleccion de transacciones utilizadas para validar
		for (Map.Entry<String, ValidationPair<E>[]> entry : entities.entrySet()) {

			// Obtiene el nombre de la transaccion
			String transaction = entry.getKey();
			// Obtiene el arreglo de entidades a validar
			ValidationPair<E>[] entitiesToValidate = entry.getValue();

			// Valida cada una de las entidades asociadas a la
			// transacción
			for (int i = 0; i < entitiesToValidate.length; i++) {

				// Obtiene la entidad
				E entityToValidate = entitiesToValidate[i].getEntity();
				// Obtiene el valor de si espera errores en la validación
				Boolean errorsExpected = entitiesToValidate[i].getErrorExpected();

				LOG.debug("Entidad: " + entityToValidate);
				LOG.debug("Transaction: " + transaction);
				ValidationResults result =
						validationExcecution.validate(transaction,
								                      entityToValidate,
								                      requestContext);

				assertEquals("El resultado esperado de la validacion del Business Object "
							+ "difiere del resultado obtenido",
							errorsExpected,
							result.hasErrors());

				assertFalse("El Business Object contiene errores en la validacion",
							validationHasErrors(transaction,
									            entityToValidate,
									            result.getErrorMessages()));

				LOG.debug("Errors: " + result.hasErrors());
				LOG.debug("Expected: " + errorsExpected);
			}
		}
	}
}


/**
 * Test base para validaciones y reglas de negocio por validadores.
 */
@Test
public void testValidationsByValidators() {
	LOG.debug("Test de Validaciones por Validadores");
	// Obtiene las entidades a validar
	ValidationTouple[ ] tuples = provider.createBussinessEntitiesForValidationByValidator();
	if (tuples != null) {

		// Recorre los validadores validando en cada caso la entidad
		for (int i = 0; i < tuples.length; i++) {
			// Obtiene el nombre del validador
			String validatorName = tuples[i].getValidator();
			// Obtiene el validador
			Validator validator = (Validator) serviceLocator.getService(validatorName);
			// Obtiene la entidad a validar
			Object entityToValidate = tuples[i].getEntity();
			// Obtiene si espera errores en la validación
			Boolean errorsExpected = tuples[i].getErrorExpected();
			// Obtiene la lista de profiles
			String[ ] profiles = tuples[i].getProfiles();

			LOG.debug("Entidad: " + entityToValidate);
			LOG.debug("Validator: " + validatorName);
			LOG.debug("Profiles");
			for (int j = 0; j < profiles.length; j++) {
				LOG.debug("Profile: # " + j + " -- " + profiles[j]);
			}

			ValidationResults result =
					validator.validate(entityToValidate,
									   profiles,
									   getRequestContext().getLocaleData());

			assertEquals("El resultado esperado de la validacion del Business Object "
						+ "difiere del resultado obtenido",
						errorsExpected,
						result.hasErrors());

			assertFalse("El Business Object contiene errores en la validacion",
						validationWithValidatorHasErrors(validatorName,
										                 profiles,
	                                                     entityToValidate,
	                                                     result.getErrorMessages()));

			LOG.debug("Errors: " + result.hasErrors());
			LOG.debug("Expected: " + errorsExpected);
		}
	}
}

/**
 * Método que permite realizar comprobaciones sobre Business Object
 * validado. La implementación default retorna que no hubo errores.
 *
 * @param pValidatorName Validador
 * @param pProfiles Perfiles con los que se validó
 * @param pEntityToValidate EntidadBO validada
 * @param pErrorMessages Errores generados durante la validacion
 * @return {@link Boolean} Resultado de la comprobación
 */
protected abstract boolean validationWithValidatorHasErrors(String pValidatorName,
		String[ ] pProfiles,
		Object pEntityToValidate,
		List<String> pErrorMessages);

/**
 * Método que permite realizar comprobaciones sobre Business Object
 * validado. La implementación default retorna que no hubo errores.
 *
 * @param pTransaction Transaccion validada
 * @param pEntityToValidate EntidadBO validada
 * @param pErrorMessages Errores generados durante la validacion
 * @return {@link Boolean} Resultado de la comprobación
 */
protected abstract boolean validationHasErrors(String pTransaction,
											   E pEntityToValidate,
	                                           List<String> pErrorMessages);

/**
 * @return Retorna el RequestContext.
 */
protected RequestContext getRequestContext() {
	return this.requestContext;
}

/**
 * @return Retorna el valor del atributo serviceLocator.
 */
protected ServiceLocator getServiceLocator() {
	return this.serviceLocator;
}

/**
 * @param pServiceLocator Establece el valor del atributo
 *        serviceLocator.
 */
public void setServiceLocator(ServiceLocator pServiceLocator) {
	this.serviceLocator = pServiceLocator;
}


/**
 * @return Retorna el valor del atributo boClass.
 */
protected Class<? extends CrudBusinessObjectImpl<T, E>> getBoClass() {
	return this.boClass;
}

/**
 * @param pBoClass Establece el valor del atributo boClass.
 */
protected void setBoClass(Class<? extends CrudBusinessObjectImpl<T, E>> pBoClass) {
	this.boClass = pBoClass;
}


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
	this.provider = pProvider;
}
}
