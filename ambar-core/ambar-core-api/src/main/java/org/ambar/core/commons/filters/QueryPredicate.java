/**
 * ambar-core-api [11/10/2011 02:02:33]
 */
package org.ambar.core.commons.filters;

import java.util.HashMap;
import java.util.Map;

import org.ambar.core.commons.order.OrderType;

/**
 * <p>
 * Helper que permite construir la sentencia de una consulta a la base
 * con JPQL.
 * </p>
 *
 * @author Sebastian
 *
 */
public class QueryPredicate {
    private final StringBuilder whereClause;
    private final StringBuilder orderByClause;
    private final Map<String, Object> params;
    private Integer pageNumber;
    private Integer pageSize;

    /**
     * Constructor por default.
     */
    public QueryPredicate() {
        this.whereClause = new StringBuilder();
        this.orderByClause = new StringBuilder();
        this.params = new HashMap<String, Object>();
    }

    /**
     * @return Retorna el valor del atributo pageNumber.
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pPageNumber Establece el valor del atributo pageNumber.
     */
    public void setPageNumber(final Integer pPageNumber) {
        pageNumber = pPageNumber;
    }

    /**
     * @return Retorna el valor del atributo pageSize.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pPageSize Establece el valor del atributo pageSize.
     */
    public void setPageSize(final Integer pPageSize) {
        pageSize = pPageSize;
    }

    /**
     * Método que permite agregar un {@link String} a la consulta.
     *
     * @param pValue Valor a agregar
     */
    public void appendToWhere(String pValue) {
    	if (pValue != null && !pValue.trim().equals("")) {
    		if (this.whereClause.length() == 0) {
                this.whereClause.append("WHERE ");
            } else {
                this.whereClause.append(" ");
            }
            this.whereClause.append(pValue);
    	}
    }

    /**
     * Método que permite agregar un {@link String} a la cláusula <i>ORDER BY</i>
     * de la consulta.
     *
     * @param pValue Valor a agregar
     */
    public void appendToOrderBy(String pValue) {
        if (this.orderByClause.length() == 0) {
            this.orderByClause.append("ORDER BY ");
        } else {
            if ((!pValue.equals(OrderType.Asc.toString())) && (!pValue.equals(OrderType.Desc.toString()))) {
                this.orderByClause.append(",");
            }
            this.orderByClause.append(" ");
        }
        this.orderByClause.append(pValue);
    }

    /**
     * Método que agrega un par parámetro / valor a la lista
     * de parámetros garantizando que los nombres de los parámetros
     * no se repiten y retornando el nombre del parámetro que efectivamente
     * se agregó, generando un nombre único si el nombre del parámetro
     * ya existe.
     * @param pParamName    Nombre del parámetro
     * @param pValue        Valor del parámetro
     * @return              {@link String} Nombre del parámetro agregado
     */
    public String addParam(String pParamName, Object pValue) {
        String paramName = pParamName.replaceAll("\\.", "_");
        if (!this.params.containsKey(pParamName)) {
            paramName = pParamName;
        } else {
            paramName = calculateParamName(pParamName);
        }
        this.params.put(paramName, pValue);

        return paramName;
    }

    /**
	 * Obtiene el nombre del parámetro.
	 * @param pParamName Nombre original
	 * @return {@link String} Nombre calculado
	 */
	private String calculateParamName(String pParamName) {
		Integer i = 1;

		String calcName = pParamName + "_" + i;
		while (this.params.containsKey(calcName)) {
			i++;
			calcName = pParamName + "_" + i;
		}
		return calcName;
	}

	/* (non-JSDoc)
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        return this.getQueryCondition();
    }

    /**
     * @return Retorna el valor del atributo queryCondition.
     */
    public String getQueryCondition() {
        final StringBuilder queryCondition = new StringBuilder();
        queryCondition.append(this.whereClause);
        queryCondition.append(" ");
        queryCondition.append(this.orderByClause);

        return queryCondition.toString();
    }

    /**
     * @return Retorna el valor del atributo params.
     */
    public Map<String, Object> getParams() {
        return this.params;
    }
}
