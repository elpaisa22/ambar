/**
 * ambar-core-api [11/10/2011 01:51:21]
 */
package org.ambar.core.commons.filters.criteria;

import org.ambar.core.commons.filters.BinaryFilter;
import org.ambar.core.commons.filters.BinaryOperator;

/**
 * <p>
 * Wrapper de {@link PropertyFilter} con métodos que retornan expresiones
 * de tipo binarias configuradas.
 * </p>
 *
 * @author Sebastian
 *
 */
public class PropertyBinaryFilter extends PropertyFilter {

	/**
	 * Constructor por default.
	 * @param pProperty Operando izquierdo de la expresión
	 */
	public PropertyBinaryFilter(final String pProperty) {
		super(pProperty);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad == value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter equalTo(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.EqualTo, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad != value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter notEqualTo(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.NotEqualTo, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad like value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter like(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.Like, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>not propiedad like value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter notLike(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.NotLike, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad > value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter greaterThan(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.GreaterThan, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad >= value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter greaterThanOrEqualTo(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.GreaterThanOrEqualTo, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad < value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter lessThan(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.LessThan, pValue);
	}

	/**
	 * Método que retorna la expresión: <pre>propiedad <= value</pre>
	 * a partir del parámetro recibido.
	 * @param pValue Operando de la derecha de la expresión
	 * @return {@link BinaryFilter}
	 */
	public BinaryFilter lessThanOrEqualTo(final Object pValue) {
		return new BinaryFilter(this.getProperty(), BinaryOperator.LessThanOrEqualTo, pValue);
	}

	/**
     * Método que retorna la expresión: <pre>propiedad <= value</pre>
     * a partir de los parámetros recibidos.
     * @param pOperator Operador de la espresión
     * @param pValue Operando de la derecha de la expresión
     * @return {@link BinaryFilter}
     */
    public BinaryFilter fromStringOperator(final String pOperator, final Object pValue) {
        return new BinaryFilter(this.getProperty(), BinaryOperator.fromValue(pOperator), pValue);
    }
}
