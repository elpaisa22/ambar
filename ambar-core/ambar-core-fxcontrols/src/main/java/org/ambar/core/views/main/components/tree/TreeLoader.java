/**
 *
 */
package org.ambar.core.views.main.components.tree;

import javafx.scene.control.SelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import org.ambar.core.views.main.components.tree.item.LazyTreeItem;

/**
 * Interfaz que define las operaciones a implementar por la
 * clase encargada de cargar el TreeView.
 *
 * @author Sebastian
 *
 */
public interface TreeLoader {

	/**
	 * Asigna el TreeView.
	 * @param pTreeView TreeView
	 *
	 * */
	void setTreeView(TreeView<Object> pTreeView);

	/**
	 * Carga el TreeView para la entidad recibida por
	 * parametro (con sus respectivas subentidades).
	 *
	 * @param pEntityId Id de la entidad
	 *
	 * */
	void loadTreeView(String pEntityId);

	/**
	 * Carga las subentidades para la entidad pEntityId.
	 * @param pTreeItem TreeItem Padre
	 * @param pEntityId Id de la entidad
	 * @param pLevel Nivel de profundidad en el arbol
	 * @param pSelectionModel Selection Model
	 * */
	void loadSubEntities(TreeItem<Object> pTreeItem,
			             String pEntityId,
			             Integer pLevel,
			             SelectionModel<TreeItem<Object>> pSelectionModel);

	/**
	 * Carga solo los sub-items para el TreeItem.
	 * @param pTreeItem Tree Item
	 * */
	void loadSubItems(LazyTreeItem pTreeItem);

}
