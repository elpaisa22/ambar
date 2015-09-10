/**
 *
 */
package org.ambar.core.views.main.components.tree;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import org.ambar.core.views.main.components.tree.item.LazyTreeItem;
import org.ambar.core.views.main.navigation.NavigationManager;

/**
 * @author Sebastian
 *
 */
public class RelationItemTreeListener implements ChangeListener<TreeItem<Object>>  {

	private NavigationManager navigationManager;
	private TreeView<Object> treeView;


	/**
	 * Setea el navigation manager.
	 * @param pNavigationManager Navigation manager
	 * */
	public void setNavigationManager(NavigationManager pNavigationManager) {
		this.navigationManager = pNavigationManager;
	}

	/**
	 * Setea el TreeView.
	 * @param pTreeView TreeView
	 * */
	public void setTreeView(TreeView<Object> pTreeView) {
		this.treeView = pTreeView;
	}

	@Override
	public void changed(ObservableValue<? extends TreeItem<Object>> pObservableValue,
			            TreeItem<Object> pTreeItem,
			            TreeItem<Object> pTreeItem2) {
		LazyTreeItem selectedItem = (LazyTreeItem) treeView.getSelectionModel().getSelectedItem();

		if (selectedItem != null && selectedItem.getEnabled()) {
			System.out.println("Tree Item: " + treeView.getSelectionModel().getSelectedItem());
			navigationManager.subEntitySelected(selectedItem);
		}
	}

}
