/**
 * ambar-core-dictionary [12/03/2012 22:13:09]
 */
package org.ambar.core.dictionary.domain.navigation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Insertar descripción funcional.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "item", namespace = "http://www.ambar.org/navigation")
public class Item implements Serializable {

	private static final long serialVersionUID = 7283840478865484703L;

	private String entity;
	private String text;
	private String targetState;

	/**
	 * @return Retorna el valor del atributo id.
	 */
	@XmlAttribute(name = "entity")
	public String getId() {
		return entity;
	}
	/**
	 * @param pEntity Establece el valor del atributo id.
	 */
	public void setId(String pEntity) {
		entity = pEntity;
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
	 * @return Retorna el valor del atributo targetState.
	 */
	@XmlAttribute(name = "targetState")
	public String getTargetState() {
		return targetState;
	}
	/**
	 * @param pTargetState Establece el valor del atributo targetState.
	 */
	public void setTargetState(String pTargetState) {
		targetState = pTargetState;
	}
}
