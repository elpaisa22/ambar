/**
 * ambar-core-impl [22/08/2011 12:54:42]
 */
package org.ambar.core.services.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.bo.DataBusinessObject;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.commons.filters.helpers.FilterExpressionBuilderEx;
import org.ambar.core.commons.mapping.configuration.FieldMapping;
import org.ambar.core.commons.mapping.configuration.MappingConfiguration;
import org.ambar.core.commons.messages.MessageService;
import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.TrackInfoDTO;
import org.ambar.core.dto.TrackingableDTO;
import org.ambar.core.dto.results.MessageKind;
import org.ambar.core.dto.results.MessageResult;
import org.ambar.core.dto.results.ResultListDTO;
import org.ambar.core.dto.results.ResultObjectDTO;
import org.ambar.core.exceptions.InvalidMappingForFilterException;
import org.ambar.core.exceptions.SystemException;
import org.ambar.core.mappers.GenericMapper;
import org.ambar.core.services.DataServices;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;




/**
 * <p>
 * Implementación por default de {@link DataServices}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <K> Tipo de dato de la clave primaria del DTO sobre el que opera el servicio
 * @param <D> Tipo de dato del DTO sobre el que opera el servicio
 * @param <T> Tipo de dato de la clave primaria de la BE sobre el que opera el servicio
 * @param <E> Tipo de dato de la BE sobre el que opera el servicio
 */
public abstract class DataServiceDefaultImpl<K, D extends DTO<K>, T, E extends Persistible<T>>
	implements DataServices<K, D, T, E>, Serializable {

	private static final long serialVersionUID = -7084174513647296744L;

	private static final Logger LOG = LoggerFactory.getLogger(DataServiceDefaultImpl.class);

	private DataBusinessObject<T, E> businessObject;
	private MessageService messageService;
	private GenericMapper<E, D> entityMapper;
    private MappingConfiguration mappingConfiguration;
    private ServiceLocator serviceLocator;
    private String expressionBuilderBeanName;

	/**
	 * @return Retorna el valor del atributo businessObject.
	 */
	protected DataBusinessObject<T, E> getBusinessObject() {
		return this.businessObject;
	}

	/**
	 * @param pBusinessObject Establece el valor del atributo
	 *        businessObject.
	 */
	public void setBusinessObject(DataBusinessObject<T, E> pBusinessObject) {
		this.businessObject = pBusinessObject;
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
	 * @return Retorna el valor del atributo entityMapper.
	 */
	protected GenericMapper<E, D> getEntityMapper() {
		return this.entityMapper;
	}

	/**
	 * @param pEntityMapper Establece el valor del atributo entityMapper.
	 */
	public void setEntityMapper(GenericMapper<E, D> pEntityMapper) {
		this.entityMapper = pEntityMapper;
	}

    /**
     * @return Retorna el valor del atributo mappingConfiguration.
     */
    protected MappingConfiguration getMappingConfiguration() {
        return this.mappingConfiguration;
    }

    /**
     * @param pMappingConfiguration Establece el valor del atributo mappingConfiguration.
     */
    public void setMappingConfiguration(final MappingConfiguration pMappingConfiguration) {
        this.mappingConfiguration = pMappingConfiguration;
    }

    /**
     * @return Retorna el valor del atributo serviceLocator.
     */
    protected ServiceLocator getServiceLocator() {
        return this.serviceLocator;
    }

    /**
     * @param pServiceLocator Establece el valor del atributo serviceLocator.
     */
    public void setServiceLocator(final ServiceLocator pServiceLocator) {
        this.serviceLocator = pServiceLocator;
    }

    /**
     * @return Retorna el valor del atributo expressionBuilderBeanName.
     */
    public String getExpressionBuilderBeanName() {
        return this.expressionBuilderBeanName;
    }

    /**
     * @param pExpressionBuilderBeanName Establece el valor del atributo expressionBuilderBeanName.
     */
    public void setExpressionBuilderBeanName(final String pExpressionBuilderBeanName) {
        this.expressionBuilderBeanName = pExpressionBuilderBeanName;
    }

    /**
     * Método que permite modificar la configuración de mappings utilizados en el servicio.
     * @param pFieldsMapping mapeo de campos
     */
    protected void configureFieldsMappings(Set<FieldMapping> pFieldsMapping) {
    	//Sobreescribir en caso de necesitar configurar algún mapping en forma custom
    }

	/**
     * Método que se usará para obtener el helper que resuelve las expresiones
     * de filtros dinámicos.
     * @return {@link FilterExpressionBuilderEx}
     */
    protected FilterExpressionBuilderEx createFilterExpressionBuilder() {
        final Type aRawType = ((ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        Class<?> dtoClass = (Class<?>) aRawType;

        final Type bRawType = ((ParameterizedType)
                this.getClass().getGenericSuperclass()).getActualTypeArguments()[3];
        Class<?> beClass = (Class<?>) bRawType;

        FilterExpressionBuilderEx expressionBuilder =
                (FilterExpressionBuilderEx) this.serviceLocator.getService(this.expressionBuilderBeanName);

        Set<FieldMapping> fields = this.mappingConfiguration.fieldsFromClassMapping(
                beClass.getName(),
                dtoClass.getName());
        configureFieldsMappings(fields);
        expressionBuilder.setMappings(fields);

        return expressionBuilder;
    }

	/**
	 * Retorna la clave primaria de la entidad de negocio a partir de la
	 * conversión de la clave primaria del DTO.
	 *
	 * @param pDTOId Clave primaria del DTO
	 * @return Clave primaria de la entidad de negocio
	 */
	@SuppressWarnings("unchecked")
	protected T getBussinessEntityId(K pDTOId) {
		LOG.debug("Ingreso a \"getBussinessEntityId\"");
		LOG.debug("pDTOId: " + pDTOId);
		T id = (T) pDTOId;
		LOG.debug("Valor de retorno: " + id);
		LOG.debug("Fin de \"getBussinessEntityId\"");
		return id;
	}

	/**
	 * Valida que la entidad luego de ser mapeada a BE no tenga id en que
	 * sea del tipo DTO.
	 *
	 * @param pEntity D
	 */
	protected void assertMapping(E pEntity) {
		LOG.debug("Ingreso a \"assertMapping\"");
		assert pEntity != null : "Entidad (BE) luego del mapeo es null " + pEntity;
		assert pEntity.getId() != null : "Id de la entidad (BE) luego del mapeo es null " + pEntity;
		assert !(pEntity.getId() instanceof DTO<?>)
			: "El tipo de Id de la entidad (BE) es DTO luego del mapeo " + pEntity;
	}

	/**
	 * Validaciones sobre la entidad luego de ser mapeada a DTO.
	 *
	 * @param pEntity D
	 */
	protected void assertMapping(D pEntity) {
		LOG.debug("Ingreso a \"assertMapping\"");
		assert pEntity != null : "Entidad (DTO) luego del mapeo es null " + pEntity;
		assert pEntity.getId() != null : "Id de la entidad (DTO) luego del mapeo es null " + pEntity;
	}

	/**
	 * Valida los campos de {@link TrackInfoDTO} estén seteados.
	 *
	 * @param pEntity Entidad a validar
	 */
	protected void assertInfoAuditoriaNotEmpty(D pEntity) {
		LOG.debug("Ingreso a \"assertTrackInfoNotEmpty\"");
		String callingMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		if (pEntity instanceof TrackingableDTO) {
			TrackInfoDTO infoAuditoriaDTO = ((TrackingableDTO) pEntity).getTrackInfo();
			assert infoAuditoriaDTO != null : callingMethodName + " - TrackInfoDTO es null " + pEntity;
			assert infoAuditoriaDTO.getEstado() != null
					: callingMethodName + " - El estado de trackInfoDTO es null " + pEntity;
			assert infoAuditoriaDTO.getFechaCreacion() != null
					: callingMethodName + " - El estado de trackInfoDTO es null " + pEntity;
			assert infoAuditoriaDTO.getUsuario() != null
					: callingMethodName + " - El usuario de trackInfoDTO es null " + pEntity;
		}
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.services.DataServices#getById(
	 * java.lang.Object,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	@Transactional(readOnly = true)
	public ResultObjectDTO<D> getById(K pId, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"getById\"");
		LOG.debug("Parámetro pId: {}", pId);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);
		assert pId != null : "Id es null para getById";
		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultObjectDTO<D> resultObjectDTO = new ResultObjectDTO<D>();
		MessageResult message = new MessageResult();
		message.setKind(MessageKind.Info);
		try {
			T entityId = getBussinessEntityId(pId);
			E entity = (E) businessObject.getById(entityId);
			if (entity != null) {
				D dto = entityMapper.mapFromA(entity);
				assertMapping(dto);
				resultObjectDTO.setResult(dto);
				message.setMessage(messageService.getMessage("org.ambar.core.be.entidad.getById.ok",
						null,
						pRequestContext.getLocaleData()));
			} else {
				message.setMessage(messageService.getMessage(
						"org.ambar.core.be.entidad.getById.inexistente",
						new Object[ ] {pId},
						pRequestContext.getLocaleData()));
			}
			messages.add(message);
			assertInfoAuditoriaNotEmpty(resultObjectDTO.getResult());
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
		resultObjectDTO.setMessages(messages);
		LOG.debug("Valor de retorno: " + resultObjectDTO);
		LOG.info("Fin de \"getById\"");
		return resultObjectDTO;
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.services.DataServices#getFilteredList(
	 * org.ambar.core.commons.filters.Filter,
	 * org.ambar.core.commons.paging.Pager,
	 * org.ambar.core.commons.context.RequestContext)
	 */
	@Override
	@Transactional(readOnly = true)
	public ResultListDTO<D> getFilteredList(FilteringContext pFilteringContext, RequestContext pRequestContext) {
		LOG.info("Ingreso a \"getFilteredList\"");
		LOG.debug("Parámetro pFilteringContext: {}", pFilteringContext);
		LOG.debug("Parámetro pRequestContext: {}", pRequestContext);

		List<MessageResult> messages = new ArrayList<MessageResult>();
		ResultListDTO<D> resultListDTO = new ResultListDTO<D>();
		MessageResult message = new MessageResult();
		message.setKind(MessageKind.Info);
		try {
			List<D> listDTOs = new ArrayList<D>();
			FilteredList<E> filteredListObject;
			try {
                FilterExpressionBuilderEx expressionBuilder = this.createFilterExpressionBuilder();

                if ((pFilteringContext != null) && (pFilteringContext.getFilter() != null)) {
                	final Type aRawType = ((ParameterizedType) this.getClass().getGenericSuperclass())
                			                                                  .getActualTypeArguments()[1];
                    Class<?> dtoClass = (Class<?>) aRawType;
                    pFilteringContext.getFilter().build(expressionBuilder, dtoClass);
                }
                if ((pFilteringContext != null) && (pFilteringContext.getPager() != null)) {
                    expressionBuilder.processOrderAndPagination(pFilteringContext.getPager());
                }

				filteredListObject = this.businessObject.getFilteredList(
                        expressionBuilder.getPredicate());
			} catch (Exception e) {
				String errorMessage = this.messageService.getMessage(
						"org.ambar.core.be.entidad.getFilteredList.error.mapping",
						new Object[] {e.getMessage()},
						pRequestContext.getLocaleData());
				LOG.error(errorMessage, e);
				throw new InvalidMappingForFilterException(errorMessage, e);
			}

			List<E> listBEs = filteredListObject.getFilteredList();

			if (listBEs != null && listBEs.size() > 0) {
				for (E be : listBEs) {
					D dto = entityMapper.mapFromA(be);
					assertMapping(dto);
					assertInfoAuditoriaNotEmpty(dto);
					listDTOs.add(dto);
				}
				resultListDTO.setResultList(listDTOs);
				resultListDTO.setCompleteListSize(filteredListObject.getRowCount());
				message.setMessage(messageService.getMessage(
						"org.ambar.core.be.entidad.getFilteredList.ok",
						null,
						pRequestContext.getLocaleData()));
			} else {
				message.setMessage(messageService.getMessage(
						"org.ambar.core.be.entidad.getFilteredList.noResults",
						null,
						pRequestContext.getLocaleData()));
			}

			messages.add(message);

		} catch (SystemException e) {
			LOG.warn("Excepciones controladas por el sistema: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (MappingException e) {
			LOG.warn("Error en el mapeo de datos: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		} catch (Exception e) {
			LOG.warn("Excepción no controlada: {}", e);
			MessageResult mr = new MessageResult(e.getMessage());
			mr.setKind(MessageKind.Error);
			messages.add(mr);
		}

		resultListDTO.setMessages(messages);

		LOG.debug("Valor de retorno: " + resultListDTO);
		LOG.info("Fin de \"getFilteredList\"");

		return resultListDTO;
	}
}
