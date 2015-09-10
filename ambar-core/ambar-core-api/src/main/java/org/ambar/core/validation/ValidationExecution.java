/**
 * ambar-core-api [20/08/2011 23:18:26]
 */
package org.ambar.core.validation;

import java.util.Map;
import java.util.Set;

import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.validation.configuration.TransactionConfiguration;
import org.ambar.core.validation.configuration.ValidationStrategy;
import org.ambar.core.validation.results.ValidationResults;


/**
 * <p>
 * Interfaz a ser implementada por servicios que se encarguen
 * de ejecutar las validaciones, clases que implementen {@link Validator}.
 * </p>
 * @author Sebastian
 *
 */
public interface ValidationExecution {
	/**
	 * Colección de validators organizados por "transacción".
	 * @return {@link Map}
	 */
	Map<String, Set<String>> getValidators();

	/**
	 * Colección de "profiles" por validator.
	 * @return {@link Map}
	 */
	Map<String, Map<String, Set<String>>> getProfiles();

	/**
	 * Estrategia de ejecución ante una validación incumplida.
	 * @return {@link ValidationStrategy}
	 */
	ValidationStrategy getValidationStrategy();

	/**
	 * Estrategia de ejecución ante una validación incumplida.
	 * @param pValidationStrategy Estrategia a seguir
	 */
	void setValidationStrategy(ValidationStrategy pValidationStrategy);

	/**
	 * Configuración del comportamiento de la transacción ante
	 * validaciones incumplidas.
	 * @return {@link TransactionConfiguration}
	 */
	TransactionConfiguration getTransactionConfiguration();

	/**
	 * Configuración del comportamiento de la transacción ante
	 * validaciones incumplidas.
	 * @param pTransactionConfiguration Valor a establecer
	 */
	void setTransactionConfiguration(TransactionConfiguration pTransactionConfiguration);

	/**
	 * Servicio que obtiene instancias de validadores del IoC container en uso.
	 * @param pServiceLocator Valor a establecer
	 */
	void setServiceLocator(ServiceLocator pServiceLocator);

	/**
	 * Método que ejecuta las validaciones de una
	 * entidad configuradas para una transacción en particular.
	 * @param pTransactionToValidate	Transacción
	 * @param pEntity					Entidad a validar
	 * @param pRequestContext			Contexto de la petición
	 * @return {@link ValidationResults}
	 */
	ValidationResults validate(
			String pTransactionToValidate,
			Object pEntity,
			RequestContext pRequestContext);
}
