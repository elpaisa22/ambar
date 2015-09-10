/**
 *
 */
package org.ambar.core.views.main.navigation;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.dto.DTO;
import org.ambar.core.spring.SpringHelper;
import org.ambar.core.views.crud.api.AbstractABM;
import org.ambar.core.views.main.components.grid.GridLoader;
import org.ambar.core.views.main.components.menu.MenuLoader;
import org.ambar.core.views.main.components.tree.RelationItemTreeListener;
import org.ambar.core.views.main.components.tree.TreeLoader;
import org.ambar.core.views.main.components.tree.item.LazyTreeItem;
import org.springframework.context.ApplicationContext;

/**
 * @author Sebastian
 *
 */
public class NavigationManagerImpl implements NavigationManager {

	private TableView<Object> tableView;
	private Pane tableHeaderPane;
	private GridLoader gridLoader;
	private MenuLoader menuLoader;
	private TreeView<Object> treeView;
	private TreeLoader treeLoader;
	private DictionaryServices dictionaryServices;

	private Map<Integer, Object> parentDTOs;
	private Map<Integer, Integer> selectedIndexes;
	private LazyTreeItem lastSelectedItem;
	private Boolean gridHasData;


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
	 * @return the tableView
	 */
	public TableView<Object> getTableView() {
		return tableView;
	}

	/**
	 * @param pTableView the tableView to set
	 */
	public void setTableView(TableView<Object> pTableView) {
		tableView = pTableView;
		this.getGridLoader().setTableView(pTableView);
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
		this.getTreeLoader().setTreeView(pTreeView);
	}

	/**
	 * @return the gridLoader
	 */
	public GridLoader getGridLoader() {
		return gridLoader;
	}

	/**
	 * @param pGridLoader the gridLoader to set
	 */
	public void setGridLoader(GridLoader pGridLoader) {
		gridLoader = pGridLoader;
	}

	/**
	 * @return the treeLoader
	 */
	public TreeLoader getTreeLoader() {
		return treeLoader;
	}

	/**
	 * @param pTreeLoader the treeLoader to set
	 */
	public void setTreeLoader(TreeLoader pTreeLoader) {
		treeLoader = pTreeLoader;
	}

	/**
	 * @return the menuLoader
	 */
	public MenuLoader getMenuLoader() {
		return menuLoader;
	}

	/**
	 * @param pMenuLoader the menuLoader to set
	 */
	public void setMenuLoader(MenuLoader pMenuLoader) {
		menuLoader = pMenuLoader;
	}

	/**
	 * @return the gridHasData
	 */
	@Override
	public Boolean getGridHasData() {
		return gridHasData;
	}

	@Override
	public void newEntitySelected(LazyTreeItem pSelectedItem) {
		parentDTOs = new HashMap<Integer, Object>();
		selectedIndexes = new HashMap<Integer, Integer>();

		lastSelectedItem = null;

		//Carga el titulo de la entidad seleccionada
		EntityInfo entityInfo =
				this.dictionaryServices.getNavigationEntityInfoById(pSelectedItem.getEntityId());
		Label lbltitulo = (Label) tableHeaderPane.getScene().lookup("#IDTituloEntidad");
		lbltitulo.setText(entityInfo.getName());

		this.getTreeLoader().loadTreeView(pSelectedItem.getEntityId());
	}

	@Override
	public void subEntitySelected(LazyTreeItem pSelectedItem) {
		//Si es la primera entidad del modulo (Entidad raiz del modulo)
		if (lastSelectedItem == null) {
			this.lastSelectedItem = pSelectedItem;

			//Carga la grilla
			gridHasData = this.getGridLoader().loadTableView(pSelectedItem.getEntityId(), null);

		//Si esta navegando por una sub-entidad
		} else {

			//Elimina el listener del treeView para que no dispare eventos
			this.removeTreeListener();

			Object selected = null;
			Object parentSelected = null;
			Boolean cargarSubItems = false;
			Boolean cargarIndiceGrilla = false;

			//Obtiene el indice de la ultima entidad
			Integer lastIndex = lastSelectedItem.getLevel();

			//Si ingresa en profundidad del arbol
			if (lastIndex < pSelectedItem.getLevel()) {
				selected = tableView.getSelectionModel().getSelectedItem();
				parentSelected = selected;
				parentDTOs.put(pSelectedItem.getLevel() - 1, selected);

				//Guarda el indice de seleccion de la entidad anterior
				selectedIndexes.put(TreeView.getNodeLevel(this.lastSelectedItem),
                                                          this.getTableView().getSelectionModel().getSelectedIndex());
				cargarSubItems = true;

			//Si vuelve a un item padre
			} else if (lastIndex > pSelectedItem.getLevel()) {

				cargarIndiceGrilla = true;

				//Si el nivel del item seleccionado NO es la raiz
				if (pSelectedItem.getLevel() > 0) {

					//Toma el DTO padre para realizar los filtros luego
					parentSelected = parentDTOs.get(pSelectedItem.getLevel() - 1);
					selected = parentSelected;

					//Toma el TreeItem Padre
					LazyTreeItem parent = (LazyTreeItem) this.lastSelectedItem.getParent();

					//Mientras no llegue al item padre que esta en el mismo nivel que
					//el item seleccionado
					while (parent.getLevel() != pSelectedItem.getLevel()) {
						//Toma el padre del item parent
						parent = (LazyTreeItem) parent.getParent();
					}

					//Si el item seleccionado no es ancestro del item anterior
					if (!parent.getEntityId().equals(pSelectedItem.getEntityId())) {

						//Colapsa los hijos del ancestro del ultimo item
						this.collapseChildren(parent);

						//Indica que debe habilitar los subitems del item seleccionado
						cargarSubItems = true;
					} else {

						//Si el item seleccionado es ancestro del ultimo item seleccionado,
						//debe colapsar los hijos del item seleccionado
						for (TreeItem<Object> item : pSelectedItem.getChildren()) {
							this.collapseChildren((LazyTreeItem) item);
						}
					}

				//Si el item seleccionado es la raiz del arbol
				} else {
					//Colapsa todos los hijos de la raiz
					for (TreeItem<Object> item : pSelectedItem.getChildren()) {
						this.collapseChildren((LazyTreeItem) item);
					}
				}

			// Si esta en un mismo nivel que el nodo anterior seleccionado
			} else {
				//Colapsa los hijos del ultimo item seleccionado
				this.collapseChildren(this.lastSelectedItem);

				//Indica que debe habilitar los subitems del item seleccionado
				cargarSubItems = true;

				//Indica de debe actualizar el elemento seleccionado de la grilla si existe
				cargarIndiceGrilla = true;

				//Toma el DTO padre del item actual
				parentSelected = parentDTOs.get(pSelectedItem.getLevel() - 1);
			}

			//Calcula el filtro a aplicar de acuerdo al DTO padre
			String filter = pSelectedItem.getFilter();
			Filter expr = null;
			if (filter != null && parentSelected != null) {
				expr = dictionaryServices.expressionToFilter(filter, parentSelected);
			}

			//Carga la grilla con la entidad seleccionada enviando el filtro creado anteriormente
			gridHasData = this.getGridLoader().loadTableView(pSelectedItem.getEntityId(), expr);
			//Si la grilla posee datos y debe habilitar los subitem de la entidad actual
			if (gridHasData && cargarSubItems) {
					//Expande los hijos del item actual seleccionado
					pSelectedItem.setExpanded(true);
			}

			//Si ya cargo la grilla y debe reataurar el item seleccionado de la grilla
			if (cargarIndiceGrilla && selectedIndexes.containsKey(TreeView.getNodeLevel(pSelectedItem))) {
				//Actualiza el indice de seleccion de la grilla
				int gridSelectedIndex = selectedIndexes.get(TreeView.getNodeLevel(pSelectedItem));
				this.getTableView().getSelectionModel().select(gridSelectedIndex);
			}

			//Carga el titulo de la entidad seleccionada
			EntityInfo entityInfo =
                    this.dictionaryServices.getNavigationEntityInfoById(pSelectedItem.getEntityId());
			Label lbltitulo = (Label) tableHeaderPane.getScene().lookup("#IDTituloEntidad");
			lbltitulo.setText(entityInfo.getName());

			lastSelectedItem = pSelectedItem;

			//Setea nuevamente el indice del item seleccionado en el TreeView
			this.getTreeView().getSelectionModel().select(((TreeItem<Object>) pSelectedItem));

			//Restaura el listener del TreeView para que vuelva a disparar eventos
			this.restoreTreeListener();
		}
	}

	/**
	 * Colapsa los hijos del Item que se recibe por parametro y todos
	 * sus hijos recursivamente.
	 * @param pItem TreeItem
	 *
	 * */
	private void collapseChildren(LazyTreeItem pItem) {
		ObservableList<TreeItem<Object>> children = pItem.getChildren();
		if (children != null) {
			for (TreeItem<Object> treeItem : children) {
				LazyTreeItem lazyTreeItem = (LazyTreeItem) treeItem;
				collapseChildren(lazyTreeItem);
			}
		}
		pItem.setExpanded(false);
	}


	/**
	 * Elimina (temporalmente) el listener del TreeView para que no se
	 * disparen eventos indeseados.
	 * */
	private void restoreTreeListener() {
		ApplicationContext applicationContext = SpringHelper.getContext();
		RelationItemTreeListener treeListener = applicationContext.getBean(RelationItemTreeListener.class);
		this.getTreeView().getSelectionModel().selectedItemProperty().addListener(treeListener);
	}

	/**
	 * Restaura el listener del TreeView para que se
	 * vuelvan a disparar disparar eventos al seleccionar un Item.
	 * */
	private void removeTreeListener() {
		ApplicationContext applicationContext = SpringHelper.getContext();
		RelationItemTreeListener treeListener = applicationContext.getBean(RelationItemTreeListener.class);
		this.getTreeView().getSelectionModel().selectedItemProperty().removeListener(treeListener);
	}

	@Override
	public LazyTreeItem getLastSelectedTreeItem() {
		return lastSelectedItem;
	}

	@Override
	public void setTableHeaderPane(Pane pTableContentPane) {
		this.tableHeaderPane = pTableContentPane;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void openCrud(Object pSelectedItem) {

		if (pSelectedItem != null) {
           EntityInfo entity = this.dictionaryServices.getNavigationEntityInfoById(this.lastSelectedItem.getEntityId());

		try {
			AbstractABM crud = (AbstractABM) SpringHelper.getContext().getBean(entity.getCrud());

			if (crud != null) {
				crud.abrirConculta((DTO) pSelectedItem);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		}
	}




}
