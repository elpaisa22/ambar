/**
 * ambar-core-api [11/10/2011 01:54:13]
 */
package org.ambar.core.commons.filters.criteria;

import org.ambar.core.commons.filters.TernaryFilter;
import org.ambar.core.commons.filters.TernaryOperator;

/**
 * <p>
 * Wrapper de {@link PropertyFilter} con métodos que retornan expresiones
 * ternarias configuradas.
 * </p>
 *
 * @author Sebastian
 *
 */
public class PropertyTernaryFilter extends PropertyFilter {

	/**
	 * Constructor por default.
	 * @param pProperty Operando izquierdo de la expresión
	 */
	public PropertyTernaryFilter(final String pProperty) {
		super(pProperty);
	}

	/**
	 * Método que retorna la expresión ternaria: <pre>propiedad between value1 and value2</pre>.
	 * @param pValue1 Primer operando de la derecha de la expresión
	 * @param pValue2 Segundo operando de la derecha de la expresión
	 * @return {@link TernaryFilter}
	 */
	public TernaryFilter between(final Object pValue1, final Object pValue2) {
		return new TernaryFilter(this.getProperty(), TernaryOperator.Between, pValue1, pValue2);
	}

	/**
	 * Método que retorna la expresión ternaria: <pre>not propiedad between value1 and value2</pre>.
	 * @param pValue1 Primer operando de la derecha de la expresión
	 * @param pValue2 Segundo operando de la derecha de la expresión
	 * @return {@link TernaryFilter}
	 */
	public TernaryFilter notBetween(final Object pValue1, final Object pValue2) {
		return new TernaryFilter(this.getProperty(), TernaryOperator.NotBetween, pValue1, pValue2);
	}
}
