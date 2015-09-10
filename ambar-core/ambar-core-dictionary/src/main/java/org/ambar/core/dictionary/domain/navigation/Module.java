/**
 * ambar-core-dictionary [12/03/2012 22:15:24]
 */
package org.ambar.core.dictionary.domain.navigation;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa un modulo de navegacion.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "module", namespace = "http://www.ambar.org/navigation")
public class Module implements Serializable {

	private static final long serialVersionUID = -6680836451097836123L;

	private String id;
	private String text;
	private String icon;
	private List<Item> items;

	/**
	 * @return Retorna el valor del atributo id.
	 */
	@XmlAttribute(name = "id")
	public String getId() {
		return id;
	}
	/**
	 * @param pId Establece el valor del atributo id.
	 */
	public void setId(String pId) {
		id = pId;
	}
	/**
	 * @return Retorna el valor del atributo text.
	 */
	@XmlAttribute(name = "text")
	public String getText() {
		return text;
	}
	/**
	 * @param pText Establece el valor del atributo text.
	 */
	public void setText(String pText) {
		text = pText;
	}
	/**
	 * @return Retorna el valor del atributo icon.
	 */
	@XmlAttribute(name = "icon")
	public String getIcon() {
		return icon;
	}
	/**
	 * @param pIcon Establece el valor del atributo icon.
	 */
	public void setIcon(String pIcon) {
		icon = pIcon;
	}
	/**
	 * @return Retorna el valor del atributo items.
	 */
	@XmlElements({
        @XmlElement(name = "item", type = Item.class, namespace = "http://www.ambar.org/navigation")
    })
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param pItems Establece el valor del atributo items.
	 */
	public void setItems(List<Item> pItems) {
		items = pItems;
	}
}
