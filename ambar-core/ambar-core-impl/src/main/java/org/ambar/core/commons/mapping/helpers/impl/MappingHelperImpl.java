/**
 * ambar-core-impl [12/10/2011 08:30:20]
 */
package org.ambar.core.commons.mapping.helpers.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ambar.core.commons.mapping.configuration.FieldMapping;
import org.ambar.core.commons.mapping.configuration.MappingConfiguration;
import org.ambar.core.commons.mapping.exceptions.MappingOperationException;
import org.ambar.core.commons.mapping.helpers.MappingHelper;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * <p>Insertar descripción funcional.</p>
 * @author Sebastian
 * @see <<Insertar clases relacionadas>>
 */
public class MappingHelperImpl implements MappingHelper {
	private static final Logger LOG = LoggerFactory.getLogger(MappingHelperImpl.class);
	private MappingConfiguration mappingConfiguration;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor por default.
	 */
	public MappingHelperImpl() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo mappingConfiguration.
	 */
	protected MappingConfiguration getMappingConfiguration() {
		return this.mappingConfiguration;
	}

	/**
	 * @param pMappingConfiguration Establece el valor del atributo
	 *        mappingConfiguration.
	 */
	public void setMappingConfiguration(MappingConfiguration pMappingConfiguration) {
		this.mappingConfiguration = pMappingConfiguration;
	}

	/**
	 * Método que resuelve el mapeo de colecciones.
	 * @param pField	Configuración
	 * @param pObjectA	Objeto origen
	 * @param pObjectB	Objeto destino
	 */
	private void mapCollection(FieldMapping pField, Object pObjectA, Object pObjectB) {
		LOG.debug("Ingreso a \"mapCollection\"");

		try {
			Field dtoField = pObjectB.getClass().getDeclaredField(pField.getFieldB());
			dtoField.setAccessible(true);
			Object dtoFieldValue = dtoField.get(pObjectB);

			Field beField = pObjectA.getClass()
					.getDeclaredField(pField.getFieldA());
			beField.setAccessible(true);
			Object beFieldValue = beField.get(pObjectA);

			Iterator<?> dtoIterator = IteratorUtils.getIterator(dtoFieldValue);
			Iterator<?> beIterator = IteratorUtils.getIterator(beFieldValue);

			while ((dtoIterator.hasNext()) && (beIterator.hasNext())) {
				Object dtoDetail = dtoIterator.next();
				Object beDetail = beIterator.next();
				mapAssociatedObjects(beDetail, dtoDetail);
				if (pField.getDetailParent() != null) {
					Field parentField = beDetail.getClass()
							.getDeclaredField(pField.getDetailParent());
					parentField.setAccessible(true);
					parentField.set(beDetail, pObjectA);
				} else {
					LOG.warn("El atributo \"detail-parent\" no se configuró para el field "
							+ pField.getFieldA());
				}
			}
		} catch (SecurityException e) {
			String msg = "Error de seguridad en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (NoSuchFieldException e) {
			String msg = "Error por \"field\" no encontrado en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (IllegalArgumentException e) {
			String msg = "Error por acceso ilegal vía reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (IllegalAccessException e) {
			String msg = "Error en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		}
	}

	/**
	 * Aun no usado.
	 * @return {@link Object}
	 * @param pFieldName Nombre del Field
	 * @param pValue Valor
	 * */
    @SuppressWarnings("unused")
	private Object findFieldValue(String pFieldName, Object pValue) {

        return null;
    }

	/**
	 * Método que resuelve el mapeo de un objeto asociado.
	 * @param pField	Configuración
	 * @param pObjectA	Objeto destino
	 * @param pObjectB	Objeto origen
	 */
	private void mapAssociatedObject(FieldMapping pField, Object pObjectA, Object pObjectB) {
		try {
//			Field dtoField = pObjectB.getClass().getDeclaredField(pField.getFieldB());
//			dtoField.setAccessible(true);
//			Object dtoFieldValue = dtoField.get(pObjectB);
            Object dtoFieldValue = BeanUtilsBean.getInstance()
                            .getPropertyUtils().getProperty(pObjectB, pField.getFieldB());

			if ((!pField.isComplexAssociation()) && (pField.getFieldAParent() == null)) {
				throw new MappingOperationException(
						"Atributo \"field-a-parent\" no configurado para el field "
								+ pField.getFieldA());
			}

			String beFieldName = (pField.isComplexAssociation())
					? pField.getFieldA() : pField.getFieldAParent();
			Field beField = pObjectA.getClass()
					.getDeclaredField(beFieldName);
			beField.setAccessible(true);
            Object beFieldValue = beField.get(pObjectA);

			if (pField.isComplexAssociation()) {
				mapAssociatedObjects(beFieldValue, dtoFieldValue);
			} else {
				if (beFieldValue != null) {
                    Object realBeFieldValue = BeanUtilsBean.getInstance()
                            .getPropertyUtils().getNestedProperty(pObjectA, pField.getFieldA());
					Object obj = this.entityManager.find(
							beField.getType(), realBeFieldValue);
					beField.set(pObjectA, obj);
				}
			}
		} catch (SecurityException e) {
			String msg = "Error de seguridad en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (NoSuchFieldException e) {
			String msg = "Error por \"field\" no encontrado en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (IllegalArgumentException e) {
			String msg = "Error por acceso ilegal vía reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (IllegalAccessException e) {
			String msg = "Error en el acceso por reflection";
			LOG.error(msg);
			throw new MappingOperationException(msg, e);
		} catch (InvocationTargetException e) {
            String msg = "Error en el acceso por reflection a propiedad anidada";
            LOG.error(msg);
            throw new MappingOperationException(msg, e);
        } catch (NoSuchMethodException e) {
            String msg = "Error en el acceso por reflection por propiedad anidada inexistente";
            LOG.error(msg);
            throw new MappingOperationException(msg, e);
        }
	}

	/*
	 * (non-JSDoc)
	 * @see org.ambar.core.commons.mapping.helpers.MappingHelper#
	 * mapAssociatedObjects( java.lang.Object, java.lang.Object)
	 */
	@Override
	public void mapAssociatedObjects(Object pObjectA, Object pObjectB) {
		LOG.info("Ingreso a \"mapAssociatedObjects\"");
		Set<FieldMapping> fields = this.mappingConfiguration.fieldsFromClassMapping(
				pObjectA.getClass().getName(),
				pObjectB.getClass().getName());

		if (fields != null) {
			// Proceder solo si hay configuraciones de mapeo
			LOG.debug("Se encontraron configuraciones de mapeo");

			for (FieldMapping field : fields) {
				if ((field.isMapWithPersisted()) || (field.isComplexAssociation())) {
					if (field.isDetailCollection()) {
						this.mapCollection(field, pObjectA, pObjectB);
					} else {
						this.mapAssociatedObject(field, pObjectA, pObjectB);
					}
				}
			}
		}
	}


}

