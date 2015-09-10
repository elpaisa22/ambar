/**
 * ambar-core-api [10/08/2011 23:05:22]
 */
package org.ambar.core.be;

import java.util.List;

/**
 * <p> Objeto que complejo que contiene una lista con los registros filtrados
 * según la pagina que corresponde mostrar y la cantidad total de registros
 * según el filtro establecido.
 * </p>
 *
 * @author Sebastian
 *
 * @param <E> Clase de la entidad
 */
public class FilteredList<E extends Persistible<?>> {
	private List<E> filteredList;
	private Integer rowCount;

	/**
	 * Constructor por default.
	 */
	public FilteredList() {
	}

	/**
	 * @return Retorna el valor del atributo filteredList.
	 */
	public List<E> getFilteredList() {
		return this.filteredList;
	}

	/**
	 * @param pFilteredList Establece el valor del atributo filteredList.
	 */
	public void setFilteredList(List<E> pFilteredList) {
		this.filteredList = pFilteredList;
	}

	/**
	 * @return Retorna el valor del atributo rowCount.
	 */
	public Integer getRowCount() {
		return this.rowCount;
	}

	/**
	 * @param pRowCount Establece el valor del atributo rowCount.
	 */
	public void setRowCount(Integer pRowCount) {
		this.rowCount = pRowCount;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FilteredList [");
		if (rowCount != null) {
			builder.append("rowCount=").append(rowCount);
		}
		builder.append("]");
		return builder.toString();
	}
}

