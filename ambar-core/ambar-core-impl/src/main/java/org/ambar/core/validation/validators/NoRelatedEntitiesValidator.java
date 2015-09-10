/**
 * ambar-core-impl [22/08/2011 14:21:22]
 */
package org.ambar.core.validation.validators;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Locale;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.be.Persistible;
import org.ambar.core.be.Trackingable;
import org.ambar.core.commons.messages.MessageService;
import org.ambar.core.exceptions.SystemException;
import org.ambar.core.validation.results.ValidationResults;

/**
 *  Valida que la entidad no tenga entidades asociadas o details en baja.
 *
 *  Devuelve mensaje de error si:
 *  	- hay una entidad asociada Auditable en baja
 *  	- tiene un detail Auditable que se encuentre en baja
 *      - tiene un detail no Auditable que tenga una entidad asociada Auditable en baja.
 *
 * @author Sebastian
 *
 *
 */
class NoRelatedEntitiesValidator implements org.ambar.core.validation.Validator {

	private static final String MSG_ENTIDAD_ASOCIADA_EN_BAJA =
			"org.ambar.core.validators.NoRelatedEntitiesValidator";

	private MessageService messageService;

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.Validator#validate(
	 * 		java.lang.Object, java.lang.String[], java.util.Locale)
	 */
	@Override
	public ValidationResults validate(
			Object pEntity, String[ ] pProfiles, Locale pLocale) {

		ValidationResults validationResults = new ValidationResults();

		for (Field field : pEntity.getClass().getDeclaredFields()) {
			field.setAccessible(true);

			Object entidadAsociadaValue = null;
			try {
				entidadAsociadaValue = field.get(pEntity);
			} catch (IllegalArgumentException e) {
				throw new SystemException("Argumento incorrecto", e);
			} catch (IllegalAccessException e) {
				throw new SystemException("Acceso incorrecto", e);
			}

			// se pregunta si el campo tiene una collection de details
			if (entidadAsociadaValue instanceof Collection<?>) {
				Collection<?> details = (Collection<?>) entidadAsociadaValue;
				for (Object detail : details.toArray()) {
					if (detail instanceof Trackingable) {
						validarCampo(detail, validationResults, pLocale);
					} else {
						for (Field entidadAsociadaDetailField
								: detail.getClass().getDeclaredFields()) {
							entidadAsociadaDetailField.setAccessible(true);
							Object entidadAsociadaDetailValue = null;
							try {
								entidadAsociadaDetailValue =
										entidadAsociadaDetailField.get(detail);
							} catch (IllegalArgumentException e) {
								throw new SystemException("Argumento incorrecto", e);
							} catch (IllegalAccessException e) {
								throw new SystemException("Acceso incorrecto", e);
							}
							validarCampo(
									entidadAsociadaDetailValue,
									validationResults,
									pLocale);
						}
					}
				}
			} else { // si no es una collection, se valida si el campo es Trackingable
				validarCampo(entidadAsociadaValue, validationResults, pLocale);
			}
		}

		return validationResults;
	}


	/**
	 * @param pValue valor del campo a validar
	 * @param pValidationResults {@link ValidationResults}
	 * @param pLocale Locale
	 *
	 * Si el campo es Trackingable y el Estado es en Baja se lanza un mensaje de error
	 *
	 */
	private void validarCampo(Object pValue, ValidationResults pValidationResults, Locale pLocale) {
		if (pValue instanceof Trackingable && pValue instanceof Persistible<?>) {
			Trackingable auditable = (Trackingable) pValue;
			if (auditable != null && auditable.getTrackInfo() != null
					&& auditable.getTrackInfo().getEstado() != null) {
				if (auditable.getTrackInfo().getEstado().equals(EstadoTracking.Baja)) {

					Persistible<?> persistible = (Persistible<?>) pValue;

					pValidationResults
					.addErrorMessage(messageService
							.getMessage(MSG_ENTIDAD_ASOCIADA_EN_BAJA,
									new Object[]{persistible.getId()},
									pLocale));

				}

			}
		}
	}

	/**
	 * @param pMessageService Establece el valor de messageService al campo messageService
	 */
	public void setMessageService(MessageService pMessageService) {
		this.messageService = pMessageService;
	}
}
