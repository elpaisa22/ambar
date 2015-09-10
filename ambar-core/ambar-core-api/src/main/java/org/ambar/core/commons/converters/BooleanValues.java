/**
 * ambar-core-api [25/08/2011 21:22:24]
 */
package org.ambar.core.commons.converters;


/**
 * <p>Valores para {@link Boolean}.</p>
 *
 * @author Sebastian
 *
 */
public enum BooleanValues {
	FALSE("N"),
	TRUE("S");

	private String value;

	/**
	 * Constructor por default.
	 * @param pValue Valor del enum
	 */
	private BooleanValues(final String pValue) {
		this.value = pValue;
	}

	/**
	 * @return {@link String} Valor del enum
	 */
	public String toString() {
		return this.value;
	}

	/**
	 * Método que retorna el enum a partir de un {@link String}.
	 * @param pValue Valor a convertir
	 * @return {@link BooleanValues}
	 */
	public static BooleanValues fromString(final String pValue) {
		BooleanValues result = BooleanValues.TRUE;

		if (pValue.equals(BooleanValues.FALSE.toString())) {
			result = BooleanValues.FALSE;
		}

		return result;
	}
}
