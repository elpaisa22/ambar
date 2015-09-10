/**
 * ambar-core-api [25/08/2011 21:44:54]
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
public enum GroupOperator {
	// [x = propiedad] [? = valor o valores pasados]
	And("AND"), // [x && y && ...]
	Or("OR"); // [x || y || ...]

	private String operator;

	/**
	 * Constructor por default.
	 * @param pOperator Valor del enum
	 */
	private GroupOperator(final String pOperator) {
		this.operator = pOperator;
	}

	/**
	 * @return Devuelve el atributo operator.
	 */
	public String getOperator() {
		return this.operator;
	}

	/**
     * Retorna el enum a partir del código.
     * @param pValue Código del enum
     * @return {@link GroupOperator}
     */
	public static GroupOperator fromValue(final String pValue) {
        GroupOperator result = null;

        if (pValue.equals(GroupOperator.And.getOperator())) {
            result = GroupOperator.And;
        } else if (pValue.equals(GroupOperator.Or.getOperator())) {
                result = GroupOperator.Or;
        }

        return result;
    }
}
