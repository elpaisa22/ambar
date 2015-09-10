/**
 * ambar-core-impl [22/08/2011 14:17:53]
 */
package org.ambar.core.validation.validators;

import java.util.Locale;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.be.Persistible;
import org.ambar.core.be.Trackingable;
import org.ambar.core.commons.messages.MessageService;
import org.ambar.core.dao.DataDAO;
import org.ambar.core.exceptions.EntityNotTrackingableException;
import org.ambar.core.validation.Validator;
import org.ambar.core.validation.results.ValidationResults;

/**
 * <p>
 * Este validador verifica en primer lugar que la entidad implemente {@link Trackingable}, de no ser así
 * lanza una excepción, ya que no es posible controlar si o no un nuevo objeto.
 * A continuación busca un objeto en la base con el mismo ID, si lo encuentra el resultado es falso
 * y el mensaje se determina a partir del estado el objeto encontrado. Si no lo encuentra el resultado
 * es verdadero. Se debe configurar el DataDAO correspondiente.
 * </p>
 *
 * @author Sebastian
 *
 */
public class UniqueIdValidation implements Validator {
	private Object dao;
	private MessageService messageService;

	/**
	 * Constructor por defecto.
	 */
	public UniqueIdValidation() {
	}

	/**
	 * @param <T> Parámentro genérico para la clave primaria de la entidad de negocio del DataDAO
	 * @param <E> Parámentro genérico para la entidad de negocio del DataDAO
	 * @param pDao Fija el valor de pDao al campo dao
	 */
	public <T, E extends Persistible<T>> void setDao(DataDAO<T, E> pDao) {
		dao = pDao;
	}

	/**
	 * @param pMessageService Fija el valor de pMessageService al campo messageService
	 */
	public void setMessageService(MessageService pMessageService) {
		messageService = pMessageService;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.validation.Validator#validate(
	 * 		java.lang.Object, java.lang.String[], java.util.Locale)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ValidationResults validate(Object pEntity, String[ ] pProfiles, Locale pLocale) {
		ValidationResults resulValidations = new ValidationResults();

		Object persistedEntity = ((DataDAO) dao).getById(((Persistible) pEntity).getId());
		if (persistedEntity != null) {
			if (persistedEntity instanceof Trackingable) {
				Trackingable auditoryDataPersistedEntity = (Trackingable) persistedEntity;
				if ((auditoryDataPersistedEntity.getTrackInfo().getEstado() != null)
						&& (auditoryDataPersistedEntity.getTrackInfo().getEstado()
								== EstadoTracking.Baja)) {
					resulValidations.addErrorMessage(messageService.getMessage(
							"org.ambar.core.be.entidad.codigoDuplicado.baja",
							null,
							pLocale));
				} else {
					resulValidations.addErrorMessage(messageService.getMessage(
							"org.ambar.core.be.entidad.codigoDuplicado.activo",
							null,
							pLocale));
				}
			} else {
				throw new EntityNotTrackingableException(messageService.getMessage(
						"org.ambar.core.be.entidad.notrackingable",
						null,
						pLocale));
			}
		}
		return resulValidations;
	}
}
