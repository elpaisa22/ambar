/**
 * ambar-core-api [25/08/2011 21:46:43]
 */
package org.ambar.core.commons.filters;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;


/**
 * <p>Lista de filtros precedida por <pre>not</pre>.</p>
 *
 * @author Sebastian
 *
 */
public class NotGroupFilter extends GroupFilter {
	private static final long serialVersionUID = 5273444880617599156L;

	/**
	 * Constructor propio del tipo de filtro.
	 * @param pGroupOperator	Operador
	 * @param pFilter1			Primer operando (obligatorio)
	 * @param pFilter2			Segundo operando (obligatorio)
	 * @param pFilters			Operandos opcionales
	 */
	public NotGroupFilter(final GroupOperator pGroupOperator, final Filter pFilter1,
			final Filter pFilter2, final Filter... pFilters) {
		super(pGroupOperator, pFilter1, pFilter2, pFilters);
	}

    /**
     * Constructor con los parámetros mínimos para garantizar la corrección del tipo
     * de filtro.
     * @param pGroupOperator    Operador
     * @param pFilters          Lista de operandos
     */
    public NotGroupFilter(final GroupOperator pGroupOperator, final Filter... pFilters) {
        super(pGroupOperator, pFilters);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
