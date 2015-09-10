/**
 * ambar-core-api [25/08/2011 21:53:56]
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
public enum UnaryOperator {
	// [x = propiedad] [? = valor o valores pasados]
	// Filtros Unarios
	IsNull("is null"), // []
	IsNotNull("is not null"); // []
	private String operator;

	/**
	 * Constructor por default.
	 * @param pOperator Valor del enum
	 */
	private UnaryOperator(final String pOperator) {
		this.operator = pOperator;
	}

	/**
	 * @return Devuelve el atributo operator.
	 */
	public String getOperator() {
		return this.operator;
	}
}
