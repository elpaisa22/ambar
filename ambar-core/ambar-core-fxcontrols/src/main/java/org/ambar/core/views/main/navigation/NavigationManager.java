/**
 *
 */
package org.ambar.core.views.main.navigation;

import javafx.scene.control.TableView;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

import org.ambar.core.views.main.components.tree.item.LazyTreeItem;

/**
 * Interfaz que define las operaciones a ser implementadas
 * por la clase encargada de administrar la navegacion principal
 * de la aplicacion.
 *
 * @author Sebastian
 *
 */
public interface NavigationManager {

	/**
	 * Retorna la grilla principal.
	 *
	 * @return {@link TableView} Grilla
	 * */
	TableView<Object> getTableView();

	/**
	 * Asigna la grilla principal al NavigationManager.
	 * @param pTableView Grilla
	 * */
	void setTableView(TableView<Object> pTableView);

	/**
	 * Asigna el arbol de subentidades al NavigationManager.
	 * @param pTreeView Arbol
	 * */
	void setTreeView(TreeView<Object> pTreeView);

	/**
	 * Indica al NavigationManager que una nueva entidad fue seleccionada.
	 * @param pSelectedItem TreeItem raiz del arbol que fue seleccionado
	 * */
	void newEntitySelected(LazyTreeItem pSelectedItem);

	/**
	 * Indica que una nueva subentidad fue seleccionada.
	 * @param pSelectedItem TreeItem seleccionado
	 * */
	void subEntitySelected(LazyTreeItem pSelectedItem);

	/**
	 * Indica si la grilla principal, actualmente posee datos.
	 * @return {@link Boolean} True si la grilla posee datos
	 * */
	Boolean getGridHasData();

	/**
	 * Retorna el ultimo elemento seleccionado.
	 * @return {@link LazyTreeItem} Elemento
	 * */
	LazyTreeItem getLastSelectedTreeItem();

	/**
	 * Setea el header de la entidad.
	 * @param pTableContentPane Table Header
	 * */
	void setTableHeaderPane(Pane pTableContentPane);

	/**
	 * Abre el ABM de la entidad.
	 * @param pSelectedItem Elemento DTO
	 * */
	void openCrud(Object pSelectedItem);
}
