/**
 * ambar-core-api [11/10/2011 01:48:30]
 */
package org.ambar.core.commons.filters.criteria;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.GroupOperator;
import org.ambar.core.commons.filters.NotGroupFilter;

/**
 * <p>
 * Lista de filtros precedida por <pre>not</pre> vacía.
 * </p>
 *
 * @author Sebastian
 *
 */
public class EmptyNotGroupFilter implements EmptyFilter {

	/**
	 * Método que retorna una expresión agrupadora de filtros vacía
	 * encadenada por el operador <pre>and</pre>.
	 * @param pFilter1 Primer operando (obligatorio)
	 * @param pFilter2 Segundo operando (obligatorio)
	 * @param pFilters Operandos opcionales
	 * @return {@link NotGroupFilter}
	 */
	public NotGroupFilter and(final Filter pFilter1, final Filter pFilter2, final Filter... pFilters) {
		return new NotGroupFilter(GroupOperator.And, pFilter1, pFilter2, pFilters);
	}

	/**
	 * Método que retorna una expresión agrupadora de filtros vacía
	 * encadenada por el operador <pre>or</pre>.
	 * @param pFilter1 Primer operando (obligatorio)
	 * @param pFilter2 Segundo operando (obligatorio)
	 * @param pFilters Operandos opcionales
	 * @return {@link NotGroupFilter}
	 */
	public NotGroupFilter or(final Filter pFilter1, final Filter pFilter2, final Filter... pFilters) {
		return new NotGroupFilter(GroupOperator.Or, pFilter1, pFilter2, pFilters);
	}
}
