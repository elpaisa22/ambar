/**
 *
 */
package org.ambar.core.views.main.components.tree.item;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;

/**
 * @author Sebastian
 *
 */
public class LazyTreeItem extends TreeItem<Object> {
	private String entityId;
	private String filter;
	private Integer level;
	private Boolean enabled;

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}


	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * @param pEnabled the enabled to set
	 */
	public void setEnabled(Boolean pEnabled) {
		enabled = pEnabled;
	}

	/**
	 * Constructor default.
	 * @param pText Texto para mostrar
	 * @param pNode Nodo
	 * @param pEntityId ID de la entidad
	 * @param pFilter Filtro de navegacion
	 * @param pLevel Nivel de profundidad dentro del arbol
	 *
	 * */
	public LazyTreeItem(String pText, Node pNode, String pEntityId, String pFilter, Integer pLevel) {
		super(pText, pNode);

		entityId = pEntityId;
		filter = pFilter;
		level = pLevel;
		enabled = true;
	}

}
