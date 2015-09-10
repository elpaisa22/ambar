/**
 * ambar-core-api [25/08/2011 21:26:35]
 */
package org.ambar.core.commons.filters;


/**
 * <p>
 * Enumeración con todos los tipos de filtro disponibles.
 * </p>
 *
 * @author Sebastian
 */
public enum BinaryOperator {
	// [x = propiedad] [? = valor o valores pasados]
	EqualTo("="), // [x == ?]
	NotEqualTo("<>"), // [!(x == ?)]
	GreaterThan(">"), // [x > ?]
	GreaterThanOrEqualTo(">="), // [x >= ?]
	LessThan("<"), // [x < ?]
	LessThanOrEqualTo("<="), // [x <= ?]
	Like("LIKE"), // []
	NotLike("NOT LIKE"); // []

	private String operator;

	/**
	 * Constructor por default.
	 * @param pOperator Valor del enum
	 */
	private BinaryOperator(final String pOperator) {
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
     * @return {@link BinaryOperator}
     */
    public static BinaryOperator fromValue(final String pValue) {
        BinaryOperator result = null;

        if (pValue.equals(BinaryOperator.EqualTo.getOperator())) {
            result = BinaryOperator.EqualTo;
        } else if (pValue.equals(BinaryOperator.NotEqualTo.getOperator())) {
            result = BinaryOperator.NotEqualTo;
        } else if (pValue.equals(BinaryOperator.GreaterThan.getOperator())) {
            result = BinaryOperator.GreaterThan;
        } else if (pValue.equals(BinaryOperator.GreaterThanOrEqualTo.getOperator())) {
            result = BinaryOperator.GreaterThan;
        } else if (pValue.equals(BinaryOperator.LessThan.getOperator())) {
            result = BinaryOperator.LessThan;
        } else if (pValue.equals(BinaryOperator.LessThanOrEqualTo.getOperator())) {
            result = BinaryOperator.LessThanOrEqualTo;
        } else if (pValue.equals(BinaryOperator.Like.getOperator())) {
            result = BinaryOperator.Like;
        } else if (pValue.equals(BinaryOperator.NotLike.getOperator())) {
            result = BinaryOperator.NotLike;
        }

        return result;
    }
}
