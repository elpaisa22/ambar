/**
 *
 */
package org.ambar.core.views.main.components.grid;

import javafx.scene.control.TableView;

import org.ambar.core.commons.filters.Filter;

/**
 * Interfaz qeu define las operaciones a ser implementadas por
 * la clase que se encarga de cargar la grilla principal.
 *
 * @author Sebastian
 *
 */
public interface GridLoader {

	/**
	 * Asigna el TableView.
	 * @param pTableView Table View
	 * */
	void setTableView(TableView<Object> pTableView);

	/**
	 * Carga la grilla con los datos de la entidad que
	 * recibe por parametro y el filtro.
	 * @param pEntityId ID de la entidad
	 * @param pFilter Filtro
	 * @return {@link Boolean} True si la entidad contiene datos.
	 * */
	Boolean loadTableView(String pEntityId, Filter pFilter);
}
