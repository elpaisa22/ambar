/**
 *
 */
package org.ambar.core.views.main.components.tree;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.TreeView;

import org.ambar.core.views.main.components.tree.item.LazyTreeItem;
import org.ambar.core.views.main.navigation.NavigationManager;

/**
 * @author Sebastian
 *
 */
public class MenuItemTreeListener implements InvalidationListener {

	private NavigationManager navigationManager;
	private TreeView<Object> treeView;

	/**
	 * Constructor default.
	 * @param pNavigationManager Navigation manager
	 * @param pTreeView Tree View
	 * */
	public MenuItemTreeListener(NavigationManager pNavigationManager, TreeView<Object> pTreeView) {
		navigationManager = pNavigationManager;
		treeView = pTreeView;
	}

	@Override
	public void invalidated(Observable pParamObservable) {
		System.out.println("Menu Item: " + treeView.getSelectionModel().getSelectedItem());

		LazyTreeItem selectedItem = (LazyTreeItem) treeView.getSelectionModel().getSelectedItem();

		if (selectedItem != null) {
			navigationManager.newEntitySelected(selectedItem);
		}
	}

}
