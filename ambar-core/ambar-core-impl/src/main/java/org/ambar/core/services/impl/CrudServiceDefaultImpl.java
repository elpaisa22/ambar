/**
 * ambar-core-impl [22/08/2011 13:11:52]
 */
package org.ambar.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.ambar.core.be.Persistible;
import org.ambar.core.be.Versionable;
import org.ambar.core.bo.CrudBusinessObject;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.MessageKind;
import org.ambar.core.dto.results.MessageResult;
import org.ambar.core.dto.results.ResultVoidDTO;
import org.ambar.core.exceptions.BusinessException;
import org.ambar.core.exceptions.ServiceCastException;
import org.ambar.core.exceptions.SystemException;
import org.ambar.core.services.CrudServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.JpaSystemException;

/**
 * <p>
 * Implementación por default de {@link CrudServices}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <K> Tipo de dato de la clave primaria del DTO sobre el que opera el servicio
 * @param <D> Tipo de dato del DTO sobre el que opera el servicio
 * @param <T> Tipo de dato de la clave primaria de la BE sobre el que opera el servicio
 * @param <E> Tipo de dato de la BE sobre el que opera el servicio
 */
public abstract class CrudServiceDefaultImpl<K, D extends DTO<K>, T, E extends Persistible<T>>
	extends DataServiceDefaultImpl<K, D, T, E>
	implements CrudServices<K, D, T, E> {

	private static final long serialVersionUID = 5411519748789642261L;

	private static final Logger LOG = LoggerFactory.getLogger(CrudServiceDefaultImpl.class);

	/* (non-JSDoc)
	 * @see org.ambar.core.services.impl.DataServicesDefaultImpl#getBusinessObject()
	 */
	@Override
	protected CrudBusinessObject<T, E> getBusinessObject() {
		try {
			CrudBusinessObject<T, E> crudBusinessObject =
					(CrudBusinessObject<T, E>) super.getBusinessObject();
			return crudBusinessObject;
		} catch (Exception e) {
			throw new ServiceCastException();
		}
	}

	/**
	 * Método que encapsula los mensajes recibidos como <i>warnings</i> en
	 * un objeto que servicio pueda retornar.
	 * @param pWarningMessages Lista de mensajes
	 * @return {@link List} de {@link MessageResult}
	 */
	protected List<MessageResult> processedWarnings(List<String> pWarningMessages) {
		List<MessageResult> messages = new ArrayList<MessageResult>();
		for (String message : pWarningMessages) {
			MessageResult mr = new MessageResult();
			mr.setMessage(message);
			mr.setKind(MessageKind.Warning);
		}
		return messages;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.services.TransactionalServices#insert(
	 * 		org.ambar.core.dto.DTO, org.ambar.core.commons.context.RequestContext)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVoidDTO<D> insert(D pEntity, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"insert\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);

		assert pEntity != null : "La entidad a insertar no puede ser null";

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultVoidDTO<D> resultVoidDTO = new ResultVoidDTO<D>();
		try {
			E entity = this.getEntityMapper().mapFromB(pEntity);

			assert pEntity != null : "Entidad luego del mapeo es null " + entity;

			List<String> warnings = new ArrayList<String>();

			this.getBusinessObject().insert(entity, warnings, pRequestContext);
			Persistible<T> entityPersistible = (Persistible<T>) entity;
			if (entityPersistible.getId() instanceof Number) {
				DTO<K> dto = (DTO<K>) pEntity;
				dto.setId((K) entityPersistible.getId());
			}

			if (messages.isEmpty()) {
				MessageResult mr = new MessageResult(this.getMessageService().getMessage(
						"org.ambar.core.be.entidad.insert.ok",
						null,
						pRequestContext.getLocaleData()));
				mr.setKind(MessageKind.Info);
				messages.add(mr);
			}

			messages.addAll(processedWarnings(warnings));
		} catch (BusinessException e) {
			for (String msg : e.getMessages()) {
				MessageResult mr = new MessageResult(msg);
				mr.setKind(MessageKind.Error);
				messages.add(mr);
			}
		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (JpaSystemException e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getCause().getCause().getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultVoidDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultVoidDTO);
		LOG.info("Fin de \"insert\"");

		return resultVoidDTO;
	}

	@Override
	public ResultVoidDTO<D> update(D pEntity, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"update\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);

		assert pEntity != null : "Entidad null en update";
		assert pEntity.getId() != null : "ID null en update";

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultVoidDTO<D> resultVoidDTO = new ResultVoidDTO<D>();
		try {
			E entity = this.getEntityMapper().mapFromB(pEntity);

			assertMapping(entity);

			List<String> warnings = new ArrayList<String>();
			this.getBusinessObject().update(entity, warnings, pRequestContext);

			if (messages.isEmpty()) {
				MessageResult mr = new MessageResult(this.getMessageService().getMessage(
						"org.ambar.core.be.entidad.update.ok",
						null,
						pRequestContext.getLocaleData()));
				mr.setKind(MessageKind.Info);
				messages.add(mr);
			}

			messages.addAll(processedWarnings(warnings));
		} catch (BusinessException e) {
			for (String msg : e.getMessages()) {
				MessageResult mr = new MessageResult(msg);
				mr.setKind(MessageKind.Error);
				messages.add(mr);
			}
		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultVoidDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultVoidDTO);
		LOG.info("Fin de \"update\"");

		return resultVoidDTO;
	}

	@Override
	public ResultVoidDTO<D> remove(D pEntity, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"remove\"");
		LOG.info("Parámetro pEntity: {}", pEntity);
		LOG.info("Parámetro pContext: {}", pRequestContext);

		assert pEntity != null : "Entidad null en delete";
		assert pEntity.getId() != null : "ID null en delete";

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultVoidDTO<D> resultVoidDTO = new ResultVoidDTO<D>();
		try {
			E entity = this.getEntityMapper().mapFromB(pEntity);

			assertMapping(entity);
			assert (entity instanceof Versionable) : "La entidad no implementa versionable";

			List<String> warnings = new ArrayList<String>();
			this.getBusinessObject().remove(entity, warnings, pRequestContext);

			if (messages.isEmpty()) {

				MessageResult mr = new MessageResult(
					this.getMessageService().getMessage(
							"org.ambar.core.be.entidad.remove.ok",
							null,
							pRequestContext.getLocaleData()));
				mr.setKind(MessageKind.Info);
				messages.add(mr);
			}

			messages.addAll(processedWarnings(warnings));
		} catch (BusinessException e) {
			for (String msg : e.getMessages()) {
				MessageResult mr = new MessageResult(msg);
				mr.setKind(MessageKind.Error);
				messages.add(mr);
			}
		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultVoidDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultVoidDTO);
		LOG.info("Fin de \"delete\"");

		return resultVoidDTO;
	}

	@Override
	public ResultVoidDTO<D> inactivate(D pEntity, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"inactivate\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pRequestContextt: {}", pRequestContext);

		assert pEntity != null : "Entidad null en inactivate";
		assert pEntity.getId() != null : "ID null en inactivate";

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultVoidDTO<D> resultVoidDTO = new ResultVoidDTO<D>();
		try {
			E entity = this.getEntityMapper().mapFromB(pEntity);

			assertMapping(entity);

			List<String> warnings = new ArrayList<String>();
			this.getBusinessObject().inactivate(entity, warnings, pRequestContext);

			if (messages.isEmpty()) {
				MessageResult mr = new MessageResult(this.getMessageService().getMessage(
						"org.ambar.core.be.entidad.update.ok",
						null,
						pRequestContext.getLocaleData()));
				mr.setKind(MessageKind.Info);
				messages.add(mr);
			}

			messages.addAll(processedWarnings(warnings));
		} catch (BusinessException e) {
			for (String msg : e.getMessages()) {
				MessageResult mr = new MessageResult(msg);
				mr.setKind(MessageKind.Error);
				messages.add(mr);
			}
		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultVoidDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultVoidDTO);
		LOG.info("Fin de \"inactivate\"");

		return resultVoidDTO;
	}

	@Override
	public ResultVoidDTO<D> activate(D pEntity, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"reactivate\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		LOG.debug("Parámetro pRequestContextt: {}", pRequestContext);

		assert pEntity != null : "Entidad null en reactivate";
		assert pEntity.getId() != null : "ID null en reactivate";

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultVoidDTO<D> resultVoidDTO = new ResultVoidDTO<D>();
		try {
			E entity = this.getEntityMapper().mapFromB(pEntity);

			assertMapping(entity);

			List<String> warnings = new ArrayList<String>();
			this.getBusinessObject().reactivate(entity, warnings, pRequestContext);

			if (messages.isEmpty()) {
				MessageResult mr = new MessageResult(this.getMessageService().getMessage(
						"org.ambar.core.be.entidad.update.ok",
						null,
						pRequestContext.getLocaleData()));
				mr.setKind(MessageKind.Info);
				messages.add(mr);
			}

			messages.addAll(processedWarnings(warnings));
		} catch (BusinessException e) {
			for (String msg : e.getMessages()) {
				MessageResult mr = new MessageResult(msg);
				mr.setKind(MessageKind.Error);
				messages.add(mr);
			}
		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultVoidDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultVoidDTO);
		LOG.info("Fin de \"reactivate\"");

		return resultVoidDTO;
	}
}
