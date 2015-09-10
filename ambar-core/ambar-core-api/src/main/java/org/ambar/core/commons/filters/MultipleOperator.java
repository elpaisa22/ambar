/**
 * ambar-core-api [25/08/2011 21:48:57]
 */
package org.ambar.core.commons.filters;

/**
 * <p>
 * Enumeración con todos los tipos de filtro disponibles.
 * </p>
 *
 * @author Sebastian
 *
 */
public enum MultipleOperator {
	// [x = propiedad] [? = valor o valores pasados]
	In("IN"), // []
	NotIn("NOT IN"); // []

	private String operator;

	/**
	 * Constructor por default.
	 * @param pOperator Valor del enum
	 */
	private MultipleOperator(final String pOperator) {
		this.operator = pOperator;
	}

	/**
	 * @return Devuelve el atributo operator.
	 */
	public String getOperator() {
		return this.operator;
	}
}
