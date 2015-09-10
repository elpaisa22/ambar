/**
 * ambar-core-impl [22/08/2011 14:12:21]
 */
package org.ambar.core.validation.impl;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.ambar.core.exceptions.ProfileClassNotFoundException;
import org.ambar.core.validation.Validator;
import org.ambar.core.validation.results.ValidationResults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Clase que efectúa validaciones basada en <i>Hibernate Validator</i>.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ValidatorHibernateImpl implements Validator {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorHibernateImpl.class);

	private javax.validation.Validator validator;

	/**
	 * @return Retorna el valor del atributo validator.
	 */
	public javax.validation.Validator getValidator() {
		return this.validator;
	}

	/**
	 * @param pValidator Establece el valor del atributo validator.
	 */
	public void setValidator(javax.validation.Validator pValidator) {
		this.validator = pValidator;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.Validator#validate(
	 * 		java.lang.Object, java.lang.String[], java.util.Locale)
	 */
	@Override
	public ValidationResults validate(
			Object pEntity, String[ ] pProfiles, Locale pLocale) {
		LOG.info("Ingreso a \"validate\"");

        Locale.setDefault(pLocale);

        // Obtener los profiles adecuados a lo solicitado por Hibernate Validators
        Class<?>[] profiles = null;
        if (pProfiles != null) {
        	LOG.debug("Obtener las clases de los \"profiles\": {}", pProfiles);
        	profiles = new Class<?>[pProfiles.length];
        	for (int i = 0; i < pProfiles.length; i++) {
        		try {
					profiles[i] = Class.forName(pProfiles[i]);
				} catch (ClassNotFoundException e) {
					String error = "El profile: \"" + pProfiles[i] + "\" no es una clase válida";
					LOG.error(error, e);
					throw new ProfileClassNotFoundException(error, e);
				}
        	}
        }

        Set<ConstraintViolation<Object>> violations = null;

        // Validar
        violations = this.validator.validate(pEntity, profiles);

        // Recolectar los mensajes en el objeto a retornar
        ValidationResults results = new ValidationResults();
        for (ConstraintViolation<Object> violation : violations) {
        	results.addErrorMessage(violation.getMessage());
        }

        return results;
	}
}
