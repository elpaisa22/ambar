/**
 * ambar-core-impl [21/08/2011 23:26:21]
 */
package org.ambar.core.validation.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.exceptions.ValidatorCastException;
import org.ambar.core.exceptions.ValidatorDuplicatedException;
import org.ambar.core.validation.ValidationExecution;
import org.ambar.core.validation.Validator;
import org.ambar.core.validation.configuration.TransactionConfiguration;
import org.ambar.core.validation.configuration.Validation;
import org.ambar.core.validation.configuration.ValidationStrategy;
import org.ambar.core.validation.configuration.Validations;
import org.ambar.core.validation.configuration.ValidatorProfiles;
import org.ambar.core.validation.results.ValidationResults;

/**
 * <p>
 * Implementación por default del servicio que ejecuta las validaciones
 * obteniendo su configuración de las anotaciones en <i>business objects</i>
 * o cualquier tipo de clase que tenga anotaciones de validación.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ValidationExecutionDefaultImpl implements ValidationExecution {
	private static final Logger LOG = LoggerFactory.getLogger(ValidationExecutionDefaultImpl.class);

	private Map<String, Set<String>> validators;
	private Map<String, Map<String, Set<String>>> profiles;
	private ValidationStrategy validationStrategy;
	private TransactionConfiguration transactionConfiguration;
	private ServiceLocator serviceLocator;
	private Object serviceToValidate;

	private List<Validation> validations;
	private List<ValidatorProfiles> validationProfiles;

	/**
	 * Constructor por default.
	 */
	public ValidationExecutionDefaultImpl() {
		super();
	}

	/**
	 * Constructor que recibe el <i>servicio</i> con validaciones configuradas
	 * y la instancia de {@link ServiceLocator} a utilizar.
	 * @param pServiceToValidate	Instancia del servicio a validar
	 * @param pServiceLocator		Instancia del service locator
	 */
	public ValidationExecutionDefaultImpl(Object pServiceToValidate,
			ServiceLocator pServiceLocator) {
		super();
		this.serviceToValidate = pServiceToValidate;
		this.serviceLocator = pServiceLocator;
		this.configValidationInfo();
	}

	/**
	 * Método que lee las configuraciones de las anotaciones del
	 * business objects y genera una configuración local.
	 */
	private void configValidationInfo() {
		LOG.debug("Ingreso a \"configValidationInfo\"");

		this.validators = new HashMap<String, Set<String>>();
		this.profiles = new HashMap<String, Map<String, Set<String>>>();

		boolean hasValidations = readAnnotations();

		if (hasValidations) {
			this.configValidators();
			this.configProfiles();
		}
	}

	/**
	 * Método que genera la estructura a partir de la cual se
	 * ejecutan los validadores.
	 */
	private void configValidators() {
		LOG.debug("Ingreso a \"configValidators\"");

		// configurar los validators por transacción
		for (Validation validation : this.validations) {

			for (String transactionName : validation.transactionNames()) {

				if (this.validators.containsKey(transactionName)) {
					Set<String> validatorBeans = this.validators.get(transactionName);
					validatorBeans.addAll(Arrays.asList(validation.validators()));
				} else {
					Set<String> validatorBeans = new HashSet<String>();
					validatorBeans.addAll(Arrays.asList(validation.validators()));
					this.validators.put(transactionName, validatorBeans);
				}
			}
		}
	}

	/**
	 * Método que genera la estructura a partir de la cual se
	 * determinan los profiles configurados para cada validador.
	 */
	private void configProfiles() {
		LOG.debug("Ingreso a \"configProfiles\"");

		for (ValidatorProfiles profile : this.validationProfiles) {

			String transactionName = profile.transactionName();
			String validatorName = profile.validator();
			List<String> profilesAnnotated = Arrays.asList(profile.profiles());

			// la transaccion ya tiene validadores y profiles
			if (this.profiles.containsKey(transactionName)) {
				Map<String, Set<String>> validatorsConfigured =
						this.profiles.get(transactionName);

				// configurar los profiles del validator
				if (!validatorsConfigured.containsKey(validatorName)) {

					Set<String> profilesConfigured = new HashSet<String>();
					profilesConfigured.addAll(profilesAnnotated);
					validatorsConfigured.put(validatorName, profilesConfigured);

				} else {
					// excepcion porque el validator está configurado mas de una vez
					// para la transaccion
					LOG.debug("El validador {} fue configurado mas de una vez.",
							validatorName);
					throw new ValidatorDuplicatedException("Validador: "
							+ validatorName + " configurado mas de una vez.");
				}
			} else {
				// crear el objeto que contendrá un validator y sus profiles
				Map<String, Set<String>> validator = new HashMap<String, Set<String>>();
				// lista temporal de profiles
				Set<String> validatorProfiles = new HashSet<String>();
				validatorProfiles.addAll(profilesAnnotated);
				validator.put(validatorName, validatorProfiles);
				// agregar a la estructura de profiles el objeto
				// con la transaccion y el validator configurados
				this.profiles.put(transactionName, validator);
			}
		}
	}

	/**
	 * Lee las anotaciones definidas en el business object e hidrata las
	 * estructuras de datos locales.
	 * @return {@link Boolean} Indica si se detectaron o no validaciones configuradas
	 */
	private boolean readAnnotations() {
		LOG.debug("Ingreso a \"readAnnotations\"");

		this.validations = new ArrayList<Validation>();
		this.validationProfiles = new ArrayList<ValidatorProfiles>();

		Validations configuredValidations =
				this.serviceToValidate.getClass().getAnnotation(Validations.class);

		if (configuredValidations != null) {
			for (Validation entityValidation : configuredValidations.validations()) {
				this.validations.add(entityValidation);
			}

			for (ValidatorProfiles profile : configuredValidations.profiles()) {
				this.validationProfiles.add(profile);
			}

			this.transactionConfiguration = configuredValidations.transactionConfiguration();
			this.validationStrategy = configuredValidations.strategy();
		}

		return (configuredValidations != null);
	}

	/**
	 * Obtener el "validator" del <i>contexto IoC</i> y retornarlo como
	 * instancia de {@link Validator}. Lanzar una excepción unchecked de
	 * tipo {@link ValidatorCastException}.
	 * @param pValidatorName Nombre o id del validator en el contexto IoC
	 * @return {@link Validator}
	 */
	private Validator findValidator(String pValidatorName) {
		LOG.debug("Ingreso a \"findValidator\"");
		LOG.debug("Parámetro pValidatorName: {}", pValidatorName);

		Object validator = this.serviceLocator.getService(pValidatorName);
		if (!(validator instanceof Validator)) {
			LOG.error("El objeto con id: {} no es un Validator.", pValidatorName);
			throw new ValidatorCastException(
					"Error al intentar obtener un validator con nombre \"" + pValidatorName + "\"");
		}

		return (Validator) validator;
	}

	/**
	 * Método que retorna un array de strings con los profiles
	 * configurados para un validator en una determinada transacción.
	 * Devuelve <code>null</code> si no encuentra ninguna configuración.
	 * @param pTransactionName	Transacción en ejecución
	 * @param pValidatorName	Validador
	 * @return					Array de {@link String}
	 */
	private String[] findProfiles(String pTransactionName, String pValidatorName) {
		LOG.debug("Ingreso a \"findProfiles\"");

		String[] foundProfiles = {};

		if (this.profiles.containsKey(pTransactionName)) {
			Map<String, Set<String>> validatorProfiles = this.profiles.get(pTransactionName);

			if (validatorProfiles.containsKey(pValidatorName)) {
				Set<String> configuredProfiles = validatorProfiles.get(pValidatorName);
				foundProfiles = (String[ ]) configuredProfiles.toArray();
			}
		}

		return foundProfiles;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#validate(
	 * 		java.lang.String,
	 * 		java.lang.Object,
	 * 		org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public ValidationResults validate(
			String pTransactionToValidate,
			Object pEntity,
			RequestContext pRequestContext) {
		LOG.info("Ingreso a \"validate\"");

		// este conjunto busca garantizar que no haya mensajes
		// repetidos en el objeto a retornar
		Set<String> errorMessages = new HashSet<String>();
		Set<String> warningMessages = new HashSet<String>();

		if (this.validators.containsKey(pTransactionToValidate)) {
			Set<String> transactionValidators = this.validators.get(pTransactionToValidate);

			for (String validatorName : transactionValidators) {
				Validator validator = this.findValidator(validatorName);

				String[] validatorProfiles = this.findProfiles(pTransactionToValidate, validatorName);

				ValidationResults validatorResults = validator.validate(
						pEntity, validatorProfiles, pRequestContext.getLocaleData());

				if (validatorResults.getErrorMessages() != null) {
					errorMessages.addAll(validatorResults.getErrorMessages());
				}
				if (validatorResults.getWarningMessages() != null) {
					warningMessages.addAll(validatorResults.getWarningMessages());
				}

				if ((!validatorResults.hasErrors())
						&& (this.validationStrategy.equals(ValidationStrategy.STOP))) {
					break;
				}
			}
		}

		// volcar la lista de mensajes "no repetidos" al objeto de retorno
		ValidationResults result = new ValidationResults();
		if (!errorMessages.isEmpty()) {
			result.addErrorMessages(new ArrayList<String>(errorMessages));
		}
		if (!warningMessages.isEmpty()) {
			result.addWarningMessages(new ArrayList<String>(warningMessages));
		}
		LOG.debug("Resultado Validaciones: {}", result);
		return result;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#getValidators()
	 */
	@Override
	public Map<String, Set<String>> getValidators() {
		return this.validators;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#getProfiles()
	 */
	@Override
	public Map<String, Map<String, Set<String>>> getProfiles() {
		return this.profiles;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#getValidationStrategy()
	 */
	@Override
	public ValidationStrategy getValidationStrategy() {
		return this.validationStrategy;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#setValidationStrategy(
	 * 		org.ambar.core.validation.configuration.ValidationStrategy)
	 */
	@Override
	public void setValidationStrategy(ValidationStrategy pValidationStrategy) {
		this.validationStrategy = pValidationStrategy;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#getTransactionConfiguration()
	 */
	@Override
	public TransactionConfiguration getTransactionConfiguration() {
		return this.transactionConfiguration;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#setTransactionConfiguration(
	 * 		org.ambar.core.validation.configuration.TransactionConfiguration)
	 */
	@Override
	public void setTransactionConfiguration(TransactionConfiguration pTransactionConfiguration) {
		this.transactionConfiguration = pTransactionConfiguration;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.ValidationExecution#setServiceLocator(
	 * 		org.ambar.core.commons.servicelocator.ServiceLocator)
	 */
	@Override
	public void setServiceLocator(ServiceLocator pServiceLocator) {
		this.serviceLocator = pServiceLocator;
	}
}
