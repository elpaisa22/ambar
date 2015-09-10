/**
 * ambar-core-api [11/10/2011 01:46:28]
 */
package org.ambar.core.commons.filters.criteria;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.GroupFilter;
import org.ambar.core.commons.filters.GroupOperator;

/**
 * <p>
 * Lista de filtros vacía.
 * </p>
 *
 * @author Sebastian
 *
 */
public class EmptyGroupFilter implements EmptyFilter {
	/**
	 * Método que retorna una expresión agrupadora de filtros
	 * encadenada por el operador <pre>and</pre>.
	 * @param pFilter1 Primer operando (obligatorio)
	 * @param pFilter2 Segundo operando (obligatorio)
	 * @param pFilters Operandos opcionales
	 * @return {@link GroupFilter}
	 */
	public GroupFilter and(final Filter pFilter1, final Filter pFilter2, final Filter... pFilters) {
		return new GroupFilter(GroupOperator.And, pFilter1, pFilter2, pFilters);
	}

	/**
	 * Método que retorna una expresión agrupadora de filtros
	 * encadenada por el operador <pre>or</pre>.
	 * @param pFilter1 Primer operando (obligatorio)
	 * @param pFilter2 Segundo operando (obligatorio)
	 * @param pFilters Operandos opcionales
	 * @return {@link GroupFilter}
	 */
	public GroupFilter or(final Filter pFilter1, final Filter pFilter2, final Filter... pFilters) {
		return new GroupFilter(GroupOperator.Or, pFilter1, pFilter2, pFilters);
	}
}
