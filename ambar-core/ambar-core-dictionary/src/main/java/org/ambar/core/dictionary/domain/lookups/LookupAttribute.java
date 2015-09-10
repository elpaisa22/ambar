/**
 * ambar-core-dictionary [18/01/2014 13:35:43]
 */
package org.ambar.core.dictionary.domain.lookups;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import org.ambar.core.dictionary.domain.entities.DataType;

/**
 * <p>
 * Clase que representa el tag <i>Attribute</i> en el xml donde
 * se configura información del diccionario de los lookups.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "attribute")
public class LookupAttribute implements Serializable {

	private static final long serialVersionUID = -3454122613001582895L;

	private String name;
	private String description;
	private int width;
	private DataType dataType;

	/**
	 * @return Retorna el valor del atributo dataType.
	 */
	@XmlAttribute(name = "dataType")
	public DataType getDataType() {
		return dataType;
	}
	/**
	 * @param pDataType Establece el valor del atributo dataType.
	 */
	public void setDataType(DataType pDataType) {
		dataType = pDataType;
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
	 * @return the width
	 */
	@XmlAttribute(name = "width")
	public int getWidth() {
		return width;
	}
	/**
	 * @param pWidth the width to set
	 */
	public void setWidth(int pWidth) {
		width = pWidth;
	}
	/**
	 * @return Retorna el valor del atributo description.
	 */
    @XmlAttribute(name = "description")
	public String getDescription() {
		return description;
	}
	/**
	 * @param pDescription Establece el valor del atributo description.
	 */
	public void setDescription(String pDescription) {
		description = pDescription;
	}
}
