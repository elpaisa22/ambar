/**
 * ambar-core-dictionary [12/03/2012 22:01:48]
 */
package org.ambar.core.dictionary.domain.navigation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa una subentidad de una entidad.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "subentity", namespace = "http://www.ambar.org/navigation")
public class SubEntity implements Serializable {

	private static final long serialVersionUID = 396665572952545259L;

	private String id;
	private String text;
	private String filterCondition;

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
	 * @return Retorna el valor del atributo filterCondition.
	 */
	@XmlAttribute(name = "filterCondition")
	public String getFilterCondition() {
		return filterCondition;
	}
	/**
	 * @param pFilterCondition Establece el valor del atributo filterCondition.
	 */
	public void setFilterCondition(String pFilterCondition) {
		filterCondition = pFilterCondition;
	}
}
