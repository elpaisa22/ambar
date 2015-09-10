/**
 * ambar-core-api [25/08/2011 21:16:56]
 */
package org.ambar.core.commons.filters;


/**
 * <p>Helper que permite construir la sentencia de una consulta a la base
 * con JPQL.</p>
 *
 * @author Sebastian
 *
 */
public class QueryBuilder {
	private final StringBuilder sb;

	/**
	 * Constructor por default.
	 */
	public QueryBuilder() {
		this.sb = new StringBuilder();
	}

	/**
	 * Constructor que recibe la consulta por parámetro.
	 * @param pQuery Consulta
	 */
	public QueryBuilder(String pQuery) {
		this.sb = new StringBuilder(pQuery);
	}

	/**
	 * Método que permite agregar un {@link String} a la consulta.
	 * @param pQuery Valor a agregar
	 */
	public void append(String pQuery) {
		this.sb.append(" ");
		this.sb.append(pQuery);
		this.sb.append(" ");
	}

	/**
	 * Método que permite agregar un objeto {@link QueryBuilder} a la
	 * consulta que se está construyendo.
	 * @param pQuery Valor a agregar
	 */
	public void append(QueryBuilder pQuery) {
		this.sb.append(" ");
		this.sb.append(pQuery.sb);
		this.sb.append(" ");
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.sb.toString();
	}

	/**
	 * @return Devuelve el atributo sb.
	 */
	public StringBuilder getStringBuilder() {
		return this.sb;
	}
}
