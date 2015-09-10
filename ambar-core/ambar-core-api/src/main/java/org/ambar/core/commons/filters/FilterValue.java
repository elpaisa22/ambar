/**
 * ambar-core-api [25/08/2011 21:19:38]
 */
package org.ambar.core.commons.filters;

import java.io.Serializable;

import org.ambar.core.commons.converters.BooleanConverter;


/**
 * <p>Clase que encapsula las conversiones de datos necesarias para
 * poder establecerlos como parámetros de una consulta JPQL.</p>
 *
 * @author Sebastian
 *
 */
public class FilterValue implements Serializable {
	private static final long serialVersionUID = 582773107266153759L;
	private final Object value;

	/**
	 * Constructor por default.
	 * @param pValue Valor del filtro
	 */
	public FilterValue(Object pValue) {
		this.value = FilterValue.convertValue(pValue);
	}

	/**
	 * @return Devuelve el atributo value.
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Método que efectúa las conversiones necesarias para armar
	 * el filtro.
	 * @param pValue Valor a convertir
	 * @return {@link Object} Valor convertido
	 */
	private static Object convertValue(Object pValue) {
		if (pValue instanceof Boolean) {
			return BooleanConverter.booleanToString((Boolean) pValue);
		}
		return pValue;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		final StringBuilder builder = new StringBuilder();
		builder.append("FilterValue [");

		builder.append("value=");
		builder.append(this.value);

		builder.append("]");
		return builder.toString();
	}
}
