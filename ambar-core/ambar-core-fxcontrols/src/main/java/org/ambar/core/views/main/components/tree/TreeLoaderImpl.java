/**
 *
 */
package org.ambar.core.views.main.components.tree;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.SubEntity;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.views.main.components.tree.item.LazyTreeItem;
import org.ambar.core.views.main.navigation.NavigationManager;

/**
 * @author Sebastian
 *
 */
public class TreeLoaderImpl implements TreeLoader {

	private NavigationManager navigationManager;
	private DictionaryServices dictionaryServices;
	private TreeView<Object> treeView;

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
	 * @return the dictionaryServices
	 */
	public DictionaryServices getDictionaryServices() {
		return dictionaryServices;
	}

	/**
	 * @param pDictionaryServices the dictionaryServices to set
	 */
	public void setDictionaryServices(DictionaryServices pDictionaryServices) {
		dictionaryServices = pDictionaryServices;
	}

	/**
	 * @return the treeView
	 */
	public TreeView<Object> getTreeView() {
		return treeView;
	}

	@Override
	public void setTreeView(TreeView<Object> pTreeView) {
		treeView = pTreeView;
	}

	@Override
	public void loadTreeView(String pEntityId) {

		EntityInfo navInfo = dictionaryServices.getNavigationEntityInfoById(pEntityId);

		Node icon =
                new ImageView(new Image(getClass().getResourceAsStream("/images/" + navInfo.getSmallImage())));

		LazyTreeItem lazyTreeItem = new LazyTreeItem(navInfo.getName(), icon, pEntityId, null, 0);

		loadSubEntities(lazyTreeItem, pEntityId, 1, this.treeView.getSelectionModel());

		this.treeView.setRoot(lazyTreeItem);
		lazyTreeItem.setExpanded(true);

		this.treeView.getSelectionModel().select(lazyTreeItem);
	}

	@Override
	public void loadSubEntities(TreeItem<Object> pTreeItem,
			                    String pEntityId,
			                    Integer pLevel,
			                    final SelectionModel<TreeItem<Object>> pSelectionModel) {

		EntityInfo navInfo = dictionaryServices.getNavigationEntityInfoById(pEntityId);

		List<SubEntity> subentities = navInfo.getSubentities();
		if (subentities != null) {
			for (SubEntity subEntity : subentities) {

                EntityInfo subNavInfo = dictionaryServices.getNavigationEntityInfoById(subEntity.getId());

				Node icon =	new ImageView(
                                new Image(getClass().getResourceAsStream("/images/" + subNavInfo.getSmallImage())));

				LazyTreeItem subTreeItem = new LazyTreeItem(subEntity.getText(),
						                                    icon,
						                                    subEntity.getId(),
						                                    subEntity.getFilterCondition(),
						                                    pLevel);

				subTreeItem.addEventHandler(TreeItem.branchExpandedEvent(),

					new EventHandler<TreeItem.TreeModificationEvent<Object>>() {

					@Override
					public void handle(TreeModificationEvent<Object> pEvent) {
						if (!navigationManager.getGridHasData()) {
							pEvent.getTreeItem().setExpanded(false);
							return;
						}

						LazyTreeItem treeItem = (LazyTreeItem) pEvent.getTreeItem();
						if (pEvent.wasExpanded()
								&& treeItem.getEnabled()
                                && !treeItem.equals(navigationManager.getLastSelectedTreeItem())) {
							pSelectionModel.select(pEvent.getTreeItem());
						}
					}
		        });

				loadSubEntities(subTreeItem, subNavInfo.getId(), pLevel + 1, pSelectionModel);

				pTreeItem.getChildren().add(subTreeItem);
			}
		}
	}

	@Override
	public void loadSubItems(LazyTreeItem pTreeItem) {
		EntityInfo navInfo = dictionaryServices.getNavigationEntityInfoById(pTreeItem.getEntityId());

		if (navInfo.getSubentities() != null) {
			for (SubEntity subEntity : navInfo.getSubentities()) {
                EntityInfo subNavInfo = dictionaryServices.getNavigationEntityInfoById(subEntity.getId());
                Node icon = new ImageView(
                                    new Image(getClass().getResourceAsStream("/images/" + subNavInfo.getSmallImage())));
                TreeItem<Object> subTreeItem = new LazyTreeItem(subEntity.getText(),
                		                                        icon,
                		                                        subEntity.getId(),
                		                                        subEntity.getFilterCondition(),
                		                                        pTreeItem.getLevel() + 1);
				pTreeItem.getChildren().add(subTreeItem);
			}
		}
	}

}
