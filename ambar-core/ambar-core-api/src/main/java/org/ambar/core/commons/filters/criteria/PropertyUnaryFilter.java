/**
 * ambar-core-api [11/10/2011 01:54:51]
 */
package org.ambar.core.commons.filters.criteria;

import org.ambar.core.commons.filters.UnaryFilter;
import org.ambar.core.commons.filters.UnaryOperator;

/**
 * <p>
 * Wrapper de {@link PropertyFilter} con métodos que retornan expresiones
 * unarias configuradas.
 * </p>
 *
 * @author Sebastian
 *
 */
public class PropertyUnaryFilter extends PropertyFilter {

	/**
	 * Constructor por default.
	 * @param pProperty Operando de la expresión
	 */
	public PropertyUnaryFilter(final String pProperty) {
		super(pProperty);
	}

	/**
	 * Método que retorna la expresión unaria: <pre>propiedad is null</pre>.
	 * @return {@link UnaryFilter}
	 */
	public UnaryFilter isNull() {
		return new UnaryFilter(this.getProperty(), UnaryOperator.IsNull);
	}

	/**
	 * Método que retorna la expresión unaria: <pre>propiedad is not null</pre>.
	 * @return {@link UnaryFilter}
	 */
	public UnaryFilter isNotNull() {
		return new UnaryFilter(this.getProperty(), UnaryOperator.IsNotNull);
	}
}
