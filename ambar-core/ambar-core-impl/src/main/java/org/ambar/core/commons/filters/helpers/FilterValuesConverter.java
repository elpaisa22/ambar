/**
 * ambar-core-api [19/05/2015 19:55:15]
 */
package org.ambar.core.commons.filters.helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Clase utilitaria para convertir valores filtros.
 * </p>
 *
 * @author Sebastian
 *
 */
public class FilterValuesConverter {

	private static final Logger LOG = LoggerFactory.getLogger(FilterValuesConverter.class);

	private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	private static FilterValuesConverter instance = null;

	/**
	 * Constructor default.
	 * */
	private FilterValuesConverter() {
		// Exists only to defeat instantiation.
	}

	/**
	 * Retorna una instancia del converter.
	 * */
	public static FilterValuesConverter getInstance() {
		if(instance == null) {
			instance = new FilterValuesConverter();
		}
		return instance;
	}

	public Object convert(Class<?> pObjectClass, String pProperty, Object pValueToConvert) {
		Class<?> propertyClass = getPropertyClass(pObjectClass, pProperty);
		
		Object value = pValueToConvert;
		if (propertyClass != null) {
			if (propertyClass == java.util.Date.class) {
				value = getDateFromValue(pValueToConvert); 
			} else {
				value = ConvertUtils.convert(pValueToConvert, propertyClass);
			}
		}

		return value;
	}

	/**
	 * Convierte el valor recibido por parámetro a una fecha. 
	 * @param pValue Valor (String o Date)
	 * @return {@link Date} Fecha
	 */
	private Date getDateFromValue(Object pValue) {

		try {
			if (pValue instanceof String) {
				return this.formatter.parse((String) pValue);
			} else {
				return (Date) pValue;
			}
		} catch (Exception e) {
			LOG.error("Error convirtiendo a una fecha : " + pValue + " no es una fecha válida.");
		}
		
		return null;
	}

	/**
	 * TODO Insertar descripción funcional 
	 * @param pObjectClass
	 * @param pProperty
	 * @return
	 */
	private Class<?> getPropertyClass(Class<?> pObjectClass, String pProperty) {
		try {
			String[] road = pProperty.split("\\.");
			String prop = road[0];
			Object obj = pObjectClass.newInstance();
			Class<?> type = PropertyUtils.getPropertyType(obj, prop);

			Integer i = 0;
			while (i < road.length - 1) {
				i++;
				prop = road[i];
				obj = type.newInstance();
				type = PropertyUtils.getPropertyType(obj, prop);
			}

			return type;
		} catch (Exception e) {
			LOG.error("Error obteniendo el tipo de la propiedad : " + pObjectClass + "@" + pProperty);
		}
		

		return null;
	}
}
