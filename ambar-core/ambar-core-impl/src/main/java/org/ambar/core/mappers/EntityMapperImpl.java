/**
 * ambar-core-impl [21/08/2011 19:43:20]
 */
package org.ambar.core.mappers;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.ambar.core.commons.mapping.helpers.MappingHelper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Implementación basada en <i>Dozer</i> de {@link GenericMapper}.</p>
 *
 * @author Sebastian
 *
 * @param <A> Primer tipo de dato del mapeo denominada genéricamente A en el servicio.
 * @param <B> Primer tipo de dato del mapeo denominada genéricamente B en el servicio.
 */
public class EntityMapperImpl <A, B> implements GenericMapper<A, B> {
	private static final Logger LOG = LoggerFactory.getLogger(EntityMapperImpl.class);

	private Mapper mapper;
	private MappingHelper mappingHelper;
	private final Class<?> aClass;
	private final Class<?> bClass;

	/**
	 * Constructor por default.
	 */
	public EntityMapperImpl() {
		super();

		final Type aRawType = ((ParameterizedType)
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.aClass = (Class<?>) aRawType;

		final Type bRawType = ((ParameterizedType)
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
		this.bClass = (Class<?>) bRawType;
	}

	/**
	 * @return Retorna el valor del atributo mapper.
	 */
	protected Mapper getMapper() {
		return this.mapper;
	}

	/**
	 * @param pMapper Establece el valor del atributo mapper.
	 */
	public void setMapper(final Mapper pMapper) {
		this.mapper = pMapper;
	}

	/**
	 * @return Retorna el valor del atributo mappingHelper.
	 */
	public MappingHelper getMappingHelper() {
		return this.mappingHelper;
	}

	/**
	 * @param pMappingHelper Establece el valor del atributo mappingHelper.
	 */
	public void setMappingHelper(MappingHelper pMappingHelper) {
		this.mappingHelper = pMappingHelper;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.mappers.EntityMapper#mapFromB(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public A mapFromB(B pObject) {
		LOG.info("Ingreso a \"mapFromB\"");
		LOG.debug("Parámetro pObject: " + pObject);

		final A obj = (A) this.mapper.map(pObject, this.aClass);

		this.mappingHelper.mapAssociatedObjects(obj, pObject);

		LOG.debug("Valor de retorno: " + obj);
		LOG.debug("Fin de \"mapFromB\"");

		return obj;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.mappers.EntityMapper#mapFromB(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void mapFromB(B pSourceObject, A pDestinationObject) {
		LOG.info("Ingreso a \"mapFromB\"");
		LOG.debug("Parámetro pSourceObject: " + pSourceObject);
		LOG.debug("Parámetro pDestinationObject: " + pDestinationObject);

		this.mapper.map(pSourceObject, pDestinationObject);

		this.mappingHelper.mapAssociatedObjects(pDestinationObject, pSourceObject);

		LOG.debug("Fin de \"mapFromB\"");
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.mappers.GenericMapper#mapFromA(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public B mapFromA(A pObject) {
		LOG.info("Ingreso a \"mapFromA\"");
		LOG.debug("Parámetro pObject: " + pObject);

		final B obj = (B) this.mapper.map(pObject, this.bClass);

		LOG.debug("Valor de retorno: " + obj);
		LOG.debug("Fin de \"mapFromA\"");

		return obj;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.mappers.GenericMapper#mapFromA(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void mapFromA(A pSourceObject, B pDestinationObject) {
		LOG.info("Ingreso a \"mapFromA\"");
		LOG.debug("Parámetro pSourceObject: " + pSourceObject);
		LOG.debug("Parámetro pDestinationObject: " + pDestinationObject);

		this.mapper.map(pSourceObject, pDestinationObject);

		LOG.debug("Fin de \"mapFromA\"");
	}
}
