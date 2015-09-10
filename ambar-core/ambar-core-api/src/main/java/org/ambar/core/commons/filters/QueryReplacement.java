/**
 * ambar-core-api [25/08/2011 21:18:29]
 */
package org.ambar.core.commons.filters;

import java.util.Map;
import java.util.TreeMap;

/**
 * <p>Helper para reemplazar parámetros en una consulta JPQL.</p>
 *
 * @author Sebastian
 *
 */
public class QueryReplacement {
	private int total;
	private final Map<String, Object> replacements;

	/**
	 * Constructor por default.
	 */
	public QueryReplacement() {
		this.total = 0;
		this.replacements = new TreeMap<String, Object>();
	}

	/**
	 * Método que reemplaza un valor por su nombre de parámetro.
	 * @param pFilterValue Valor a reemplazar
	 * @return {@link String}
	 */
	public String getReplacementForValue(FilterValue pFilterValue) {
		final String replacement = "R" + this.total;
		this.replacements.put(replacement, pFilterValue.getValue());
		this.total++;
		return ":" + replacement;
	}

	/**
	 * @return Devuelve el atributo replacements.
	 */
	public Map<String, Object> getReplacements() {
		return this.replacements;
	}
}
