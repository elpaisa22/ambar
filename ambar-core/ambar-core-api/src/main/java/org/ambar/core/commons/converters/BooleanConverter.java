/**
 * ambar-core-api [25/08/2011 21:23:42]
 */
package org.ambar.core.commons.converters;


/**
 * <p>
 * Conversor de valores {@link Boolean}.
 * </p>
 *
 * @author Sebastian
 *
 */
public final class BooleanConverter {

	/**
	 * Constructor por default.
	 */
	private BooleanConverter() {
		super();
	}

	/**
	 * Método que retorna un {@link String} a partir de un {@link Boolean}.
	 * @param pValue Valor a comparar
	 * @return {@link String}
	 */
	public static String booleanToString(Boolean pValue) {
		return Boolean.TRUE.equals(pValue) ? BooleanValues.TRUE.toString() : BooleanValues.FALSE.toString();
	}

	/**
	 * Método que retorna un {@link Boolean} a partir de un {@link String}.
	 * @param pValue Valor a comparar
	 * @return {@link Boolean}
	 */
	public static Boolean stringToBoolean(String pValue) {
		return BooleanValues.TRUE.toString().equals(pValue) ? Boolean.TRUE : Boolean.FALSE;
	}
}
