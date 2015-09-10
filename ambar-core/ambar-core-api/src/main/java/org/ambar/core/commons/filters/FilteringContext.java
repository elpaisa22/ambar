/**
 * ambar-core-api [11/10/2011 01:38:10]
 */
package org.ambar.core.commons.filters;

import java.io.Serializable;


/**
 * <p>
 * Entidad usada para configurar los servicios de <i>mapping</i> a nivel de propiedad
 * de clase.
 * </p>
 *
 * @author Sebastian
 *
 */
public class FilteringContext implements Serializable {

	private static final long serialVersionUID = -7726620815961774973L;

    private Filter filter;
    private Pager pager;

    /**
     * Constructor por default.
     */
    public FilteringContext() {
        super();
    }

    /**
     * Constructor que establece el estado del objeto con los parámetros recibidos.
     * @param pFilter   Configuración de los filtros
     * @param pPager    Configuración de paginación y ordenamiento
     */
    public FilteringContext(Filter pFilter, Pager pPager) {
        super();
        this.setFilter(pFilter);
        this.setPager(pPager);
    }

    /**
     * @return Retorna el valor del atributo filter.
     */
    public Filter getFilter() {
        return this.filter;
    }

    /**
     * @param pFilter Establece el valor del atributo filter.
     */
    public void setFilter(final Filter pFilter) {
        this.filter = pFilter;
    }

    /**
     * @return Retorna el valor del atributo pager.
     */
    public Pager getPager() {
        return this.pager;
    }

    /**
     * @param pPager Establece el valor del atributo pager.
     */
    public void setPager(final Pager pPager) {
        this.pager = pPager;
    }

    /* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FilteringContext");
        sb.append("{filter=").append(filter);
        sb.append(", pager=").append(pager);
        sb.append('}');
        return sb.toString();
    }
}
