/**
 * ambar-core-impl [19/08/2011 17:22:40]
 */
package org.ambar.core.bo.impl;

import java.util.Date;
import java.util.List;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.be.Persistible;
import org.ambar.core.be.TrackInfo;
import org.ambar.core.be.Trackingable;
import org.ambar.core.bo.CrudBusinessObject;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.messages.MessageService;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.dao.CrudDAO;
import org.ambar.core.exceptions.BusinessException;
import org.ambar.core.exceptions.EntityNotTrackingableException;
import org.ambar.core.exceptions.ServiceCastException;
import org.ambar.core.validation.ValidationExecution;
import org.ambar.core.validation.impl.ValidationExecutionDefaultImpl;
import org.ambar.core.validation.results.ValidationResults;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Implementación por default de {@link CrudBusinessObject}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <E> Parámetro genérico que representa a la entidad
 * @param <T> Parámetro genérico que representa el tipo del ID de la entidad
 */
@Transactional(rollbackFor = Throwable.class)
public abstract class CrudBusinessObjectImpl<T, E extends Persistible<T>>
	extends 	DataBusinessObjectImpl<T, E>
	implements 	CrudBusinessObject<T, E> {

	private static final Logger LOG = LoggerFactory.getLogger(CrudBusinessObjectImpl.class);
	private MessageService messageService;
	private ServiceLocator serviceLocator;
	private ValidationExecution validationExecution;

	/**
	 * Constructor por default.
	 */
	public CrudBusinessObjectImpl() {
		super();
	}

	/**
	 * Inicialización del helper de validaciones.
	 */
	public void init() {
		this.validationExecution = new ValidationExecutionDefaultImpl(this, serviceLocator);
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.impl.DataBusinessObjectImpl#getDao()
	 */
	@Override
	protected CrudDAO<T, E> getDao() {
		try {
			CrudDAO<T, E> crudDAO = (CrudDAO<T, E>) super.getDao();
			return crudDAO;
		} catch (Exception e) {
			throw new ServiceCastException();
		}
	}

	/**
	 * @return Retorna el valor del atributo messageService.
	 */
	protected MessageService getMessageService() {
		return this.messageService;
	}

	/**
	 * @param pMessageService Establece el valor del atributo
	 *        messageService.
	 */
	public void setMessageService(MessageService pMessageService) {
		this.messageService = pMessageService;
	}

	/**
	 * @return Retorna el valor del atributo serviceLocator.
	 */
	public ServiceLocator getServiceLocator() {
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
	 * Método que invoca al "helper" que realizará las validaciones.
	 *
	 * @param pEntity Entidad a validar
	 * @param pWarnings Mensajes con de tipo "advertencia" devueltos por
	 *        los validadores
	 * @param pRequestContext Contexto de la petición
	 * @throws BusinessException Excepción que se lanza al encontrar
	 *         validaciones incumplidas
	 */
	protected void validate(E pEntity, List<String> pWarnings, RequestContext pRequestContext)
			throws BusinessException {
		String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		ValidationResults validationResults =
				this.validationExecution.validate(callingMethodName, pEntity, pRequestContext);
		if (validationResults.hasErrors()) {
			throw new BusinessException(validationResults.getErrorMessages());
		}
		if ((validationResults.hasWarnings()) && (pWarnings != null)) {
			pWarnings.addAll(validationResults.getWarningMessages());
		}
	}

	/**
	 * Método que genera la información de tracking en una entidad nueva.
	 *
	 * @param pEntity Entidad nueva
	 * @param pRequestContext Contexto de la petición
	 */
	private void auditoryDataForInsert(E pEntity, RequestContext pRequestContext) {
		LOG.debug("Ingreso a \"auditoryDataForInsert\"");
		if (pEntity instanceof Trackingable) {
			LOG.debug("La entidad implementa \"Trackingable\" --> generar información de seguimiento");
			Trackingable tracking = (Trackingable) pEntity;
			if (tracking.getTrackInfo() == null) {
				tracking.setTrackInfo(new TrackInfo());
			}
			tracking.getTrackInfo().setUsuario(pRequestContext.getUser());
			tracking.getTrackInfo().setEstado(EstadoTracking.Activo);
			tracking.getTrackInfo().setFechaCreacion(new Date());
		}
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.CrudBusinessObject#insert(
	 * org.ambar.core.be.Persistible, java.util.List,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public void insert(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException {
		LOG.info("Ingreso a \"insert\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pWarnings: {}", pWarnings);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		this.validate(pEntity, pWarnings, pRequestContext);
		this.auditoryDataForInsert(pEntity, pRequestContext);
		LOG.debug("Validaciones verificadas se procede a insertar");
		this.getDao().insert(pEntity);
	}

	/**
	 * Método que genera la información de tracking en la actualización de
	 * una entidad.
	 *
	 * @param pEntity Entidad a actualizar
	 * @param pRequestContext Contexto de la petición
	 */
	private void auditoryDataForUpdate(E pEntity, RequestContext pRequestContext) {
		LOG.debug("Ingreso a \"auditoryDataForUpdate\"");
		if (pEntity instanceof Trackingable) {
			LOG.debug("La entidad implementa \"Trackingable\" --> generar información de seguimiento");
			Trackingable tracking = (Trackingable) pEntity;
			tracking.getTrackInfo().setUsuario(pRequestContext.getUser());
			tracking.getTrackInfo().setFechaModificacion(new Date());
			tracking.getTrackInfo().setEstado(EstadoTracking.Modificado);
		}
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.CrudBusinessObject#update(
	 * org.ambar.core.be.Persistible, java.util.List,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public void update(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException {
		LOG.info("Ingreso a \"update\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pWarnings: {}", pWarnings);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		this.validate(pEntity, pWarnings, pRequestContext);
		this.auditoryDataForUpdate(pEntity, pRequestContext);
		LOG.debug("Validaciones verificadas se procede a actualizar");
		this.getDao().update(pEntity);
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.CrudBusinessObject#delete(
	 * org.ambar.core.be.Persistible, java.util.List,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public void remove(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException {
		LOG.info("Ingreso a \"remove\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pWarnings: {}", pWarnings);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		this.validate(pEntity, pWarnings, pRequestContext);
		LOG.debug("Validaciones verificadas se procede a eliminar");
		this.getDao().remove(pEntity);
	}

	/**
	 * Método que genera la información de tracking en la actualización de
	 * una entidad.
	 *
	 * @param pEntity Entidad a actualizar
	 * @param pEstadoTracking Estado a establecer
	 * @param pRequestContext Contexto de la petición
	 */
	private void auditoryDataForStateUpdate(E pEntity,
										EstadoTracking pEstadoTracking,
										RequestContext pRequestContext) {
		LOG.debug("Ingreso a \"auditoryDataForStateUpdate\"");
		if (pEntity instanceof Trackingable) {
			LOG.debug("La entidad implementa \"Trackingable\" --> generar información de seguimiento");
			Trackingable tracking = (Trackingable) pEntity;
			tracking.getTrackInfo().setUsuario(pRequestContext.getUser());
			tracking.getTrackInfo().setFechaModificacion(new Date());
			tracking.getTrackInfo().setEstado(pEstadoTracking);
		} else {
			throw new EntityNotTrackingableException(
					this.messageService.getMessage("org.ambar.core.be.entidad.noTrackingable",
					null,
					pRequestContext.getLocaleData()));
		}
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.CrudBusinessObject#inactivate(
	 * org.ambar.core.be.Persistible, java.util.List,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public void inactivate(E pEntity, List<String> pWarnings, RequestContext pRequestContext)
		throws BusinessException {

		LOG.info("Ingreso a \"inactivate\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pWarnings: {}", pWarnings);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		this.validate(pEntity, pWarnings, pRequestContext);
		this.auditoryDataForStateUpdate(pEntity, EstadoTracking.Baja, pRequestContext);
		LOG.debug("Validaciones verificadas se procede a actualizar");
		this.getDao().update(pEntity);
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.bo.CrudBusinessObject#reactivate(
	 * org.ambar.core.be.Persistible, java.util.List,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	public void reactivate(E pEntity, List<String> pWarnings, RequestContext pRequestContext)
		throws BusinessException {

		LOG.info("Ingreso a \"reactivate\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pWarnings: {}", pWarnings);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		this.validate(pEntity, pWarnings, pRequestContext);
		this.auditoryDataForStateUpdate(pEntity, EstadoTracking.Modificado, pRequestContext);
		LOG.debug("Validaciones verificadas se procede a actualizar");
		this.getDao().update(pEntity);
	}
}
