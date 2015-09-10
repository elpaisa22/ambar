/**
 * ambar-core-dictionary [12/03/2012 21:53:02]
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
 * Clase que representa la metadata de la informacion de navegacion de una entidad.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "entity", namespace = "http://www.ambar.org/navigation")
public class EntityInfo implements Serializable {

	private static final long serialVersionUID = -4877966298155143962L;

	private String id;
	private String name;
	private String service;
	private String crud;
	private String smallImage;
	private String largeImage;
	private String browseTitle;
	private List<SubEntity> subentities;

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
	 * @return Retorna el valor del atributo name.
	 */
	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}
	/**
	 * @param pName Establece el valor del atributo name.
	 */
	public void setName(String pName) {
		name = pName;
	}
	/**
	 * @return Retorna el valor del atributo service.
	 */
	@XmlAttribute(name = "service")
	public String getService() {
		return service;
	}
	/**
	 * @param pService Establece el valor del atributo service.
	 */
	public void setService(String pService) {
		service = pService;
	}
	/**
	 * @return Retorna el valor del atributo crud.
	 */
	@XmlAttribute(name = "crud")
	public String getCrud() {
		return crud;
	}
	/**
	 * @param pCrud Establece el valor del atributo crud.
	 */
	public void setCrud(String pCrud) {
		crud = pCrud;
	}
	/**
	 * @return Retorna el valor del atributo smallImage.
	 */
	@XmlAttribute(name = "image-small")
	public String getSmallImage() {
		return smallImage;
	}
	/**
	 * @param pSmallImage Establece el valor del atributo smallImage.
	 */
	public void setSmallImage(String pSmallImage) {
		smallImage = pSmallImage;
	}
	/**
	 * @return Retorna el valor del atributo largeImage.
	 */
	@XmlAttribute(name = "image-big")
	public String getLargeImage() {
		return largeImage;
	}
	/**
	 * @param pLargeImage Establece el valor del atributo largeImage.
	 */
	public void setLargeImage(String pLargeImage) {
		largeImage = pLargeImage;
	}
	/**
	 * @return Retorna el valor del atributo browseTitle.
	 */
	@XmlAttribute(name = "browse-title")
	public String getBrowseTitle() {
		return browseTitle;
	}
	/**
	 * @param pBrowseTitle Establece el valor del atributo browseTitle.
	 */
	public void setBrowseTitle(String pBrowseTitle) {
		browseTitle = pBrowseTitle;
	}
	/**
	 * @return Retorna el valor del atributo subentities.
	 */
	@XmlElements({
        @XmlElement(name = "subentity", type = SubEntity.class, namespace = "http://www.ambar.org/navigation")
    })
	public List<SubEntity> getSubentities() {
		return subentities;
	}
	/**
	 * @param pSubentities Establece el valor del atributo subentities.
	 */
	public void setSubentities(List<SubEntity> pSubentities) {
		subentities = pSubentities;
	}


}
