/**
 * ambar-core-api [20/08/2011 23:38:46]
 */
package org.ambar.core.commons.order;


/**
 * <p>
 * Enumeración con todos los tipos de ordenamiento disponibles.
 * </p>
 *
 * @author Sebastian
 *
 */
public enum OrderType {
	Asc("ASC"), // Tipo de ordenamiento ascendente
	Desc("DESC"); // Tipo de ordenamiento descendente

	private String value;

	/**
	 * Constructor por default.
	 * @param pValue Valor del enum
	 */
	private OrderType(String pValue) {
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
	 * @return {@link OrderType}
	 */
	public static OrderType fromString(final String pValue) {
		OrderType result = OrderType.Asc;

		if (pValue.equals(OrderType.Desc.toString())) {
			result = OrderType.Desc;
		}

		return result;
	}
}
