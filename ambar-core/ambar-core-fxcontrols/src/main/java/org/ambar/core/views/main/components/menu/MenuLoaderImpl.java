/**
 *
 */
package org.ambar.core.views.main.components.menu;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.Item;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.views.main.components.tree.MenuItemTreeListener;
import org.ambar.core.views.main.components.tree.item.LazyTreeItem;
import org.ambar.core.views.main.navigation.NavigationManager;

/**
 * @author Sebastian
 *
 */
public class MenuLoaderImpl implements MenuLoader {

	private DictionaryServices dictionaryServices;
	private NavigationManager navigationManager;

	/**
	 * @return the dictionaryServices
	 */
	public DictionaryServices getDictionaryServices() {
		return dictionaryServices;
	}

	/**
	 * @param pDictionaryServices the dictionaryServices to set
	 */
	public void setDictionaryServices(DictionaryServices pDictionaryServices) {
		this.dictionaryServices = pDictionaryServices;
	}

	/**
	 * @return the navigationManager
	 */
	public NavigationManager getNavigationManager() {
		return navigationManager;
	}

	/**
	 * @param pNavigationManager the navigationManager to set
	 */
	public void setNavigationManager(NavigationManager pNavigationManager) {
		navigationManager = pNavigationManager;
	}

	/**
	 * Método que carga el accordion principal del menu de navegacion.
	 * @param pAccordion Accordion
	 * */
	public void loadAccordionMenu(Accordion pAccordion) {

		for (Module module : dictionaryServices.getAllModules()) {
			TitledPane titledPane = new TitledPane();
			titledPane.setText(module.getText());
			titledPane.setId(module.getId());
			pAccordion.getPanes().add(titledPane);

        	TreeView<Object> treeView = new TreeView<Object>();
			treeView.setShowRoot(false);
			TreeItem<Object> rootItem = new TreeItem<Object>();
			treeView.setRoot(rootItem);
			treeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			treeView.getSelectionModel().selectedItemProperty().addListener(
					new MenuItemTreeListener(navigationManager, treeView));


			List<Item> items =  module.getItems();
			if (items != null) {
				for (Item item : items) {
					loadMenuForModule(rootItem, item);
				}
			}

			titledPane.setContent(treeView);

		}

		//El arbol dentro de cada title pane se deselecciona dinamicamente al abrirse
		pAccordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
                @SuppressWarnings("unchecked")
				public void changed(ObservableValue<? extends TitledPane> pObservableValue,
                    TitledPane pLastPane, TitledPane pNewPane) {
                        if (pNewPane != null) {
                        	((TreeView<Object>) pNewPane.getContent()).getSelectionModel().select(-1);
                        }
              }
        });

	}

	/**
	 * Carga las entidades que pertenecen a un modulo.
	 * @param pRootItem Item Raiz o principal (siempre es un nodo no visible)
	 * @param pEntityItem Item de la Entidad.
	 * */
	private void loadMenuForModule(TreeItem<Object> pRootItem, Item pEntityItem) {

		EntityInfo navInfo = dictionaryServices.getNavigationEntityInfoById(pEntityItem.getId());

        Node icon = new ImageView(new Image(getClass().getResourceAsStream("/images/" + navInfo.getLargeImage())));
		LazyTreeItem treeItem = new LazyTreeItem(pEntityItem.getText(), icon, pEntityItem.getId(), null, 0);

		pRootItem.getChildren().add(treeItem);
	}

}
