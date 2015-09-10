/**
 * ambar-core-api [25/08/2011 21:42:26]
 */
package org.ambar.core.commons.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ambar.core.commons.filters.helpers.FilterExpressionBuilder;

/**
 * <p>
 * Lista de filtros.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.Filter
 */
public class GroupFilter extends Filter {

	private static final long serialVersionUID = -835141544044612491L;

	private final List<Filter> innerFilters;
	private final GroupOperator groupOperator;

	/**
	 * Constructor propio del tipo de filtro.
	 * @param pGroupOperator	Operador
	 * @param pFilter1			Primer operando (obligatorio)
	 * @param pFilter2			Segundo operando (obligatorio)
	 * @param pFilters			Operandos opcionales
	 */
	public GroupFilter(final GroupOperator pGroupOperator, final Filter pFilter1,
			final Filter pFilter2, final Filter... pFilters) {
		this.groupOperator = pGroupOperator;
		this.innerFilters = new ArrayList<Filter>();
		this.innerFilters.add(pFilter1);
		this.innerFilters.add(pFilter2);
        this.innerFilters.addAll(Arrays.asList(pFilters));
	}

    /**
     * Constructor con los parámetros mínimos para garantizar la corrección del tipo
     * de filtro.
     * @param pGroupOperator    Operador
     * @param pFilters          Lista de operandos
     */
    public GroupFilter(final GroupOperator pGroupOperator, final Filter... pFilters) {
        super();
        this.groupOperator = pGroupOperator;
        this.innerFilters = new ArrayList<Filter>();
        this.innerFilters.addAll(Arrays.asList(pFilters));
    }
	/**
	 * @return Retorna el valor del atributo innerFilters.
	 */
	public List<Filter> getInnerFilters() {
		return this.innerFilters;
	}

    /**
     * @return Retorna el valor del atributo groupOperator.
     */
    public GroupOperator getGroupOperator() {
        return this.groupOperator;
    }

    /* (non-JSDoc)
      * @see com.inworx.foundation.commons.filters.Filter#toString()
      */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("GroupFilter [");
		if (this.innerFilters != null) {
			builder.append("innerFilters=");
			builder.append(this.innerFilters);
		}
		builder.append("]");
		return builder.toString();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final FilterExpressionBuilder pBuilder, Class<?> pObjectClass) {
        pBuilder.buildExpression(this, pObjectClass);
    }
}
