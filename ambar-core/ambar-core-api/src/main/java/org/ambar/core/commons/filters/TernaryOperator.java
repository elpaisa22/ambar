/**
 * ambar-core-api [25/08/2011 21:51:13]
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
public enum TernaryOperator {
	// [x = propiedad] [? = valor o valores pasados]
	Between("BETWEEN"), // [x Between ? And ? ]
	NotBetween("NOT BETWEEN"); // [!(x Between ? And ?)]
	private String operator;

	/**
	 * Constructor por default.
	 * @param pOperator Valor del enum
	 */
	private TernaryOperator(final String pOperator) {
		this.operator = pOperator;
	}

	/**
	 * @return Devuelve el atributo operator.
	 */
	public String getOperator() {
		return this.operator;
	}
}
