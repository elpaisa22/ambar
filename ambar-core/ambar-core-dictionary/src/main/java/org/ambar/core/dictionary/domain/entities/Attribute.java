/**
 * ambar-core-dictionary [11/03/2012 18:03:14]
 */
package org.ambar.core.dictionary.domain.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Entidad que representa la meta data de un atributo de una entidad.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "attribute", namespace = "http://www.ambar.org/entities")
public class Attribute implements Serializable {

	private static final long serialVersionUID = -5236728142320616971L;

	private String id;
	private String visibleName;
	private FieldType fieldType;
	private DataType dataType;
	private Integer width;
	private Integer order;
	private String mask;
	private Boolean gridVisible;
	private Boolean crudVisible;
	private Boolean editable;
	private Boolean searchEnabled;
	private String lookupName;
	private String converter;
	@SuppressWarnings("rawtypes")
	private Class enumType;

	/**
	 * Constructor por defecto.
	 * */
	public Attribute() {
		this.gridVisible = true;
		this.crudVisible = true;
		this.editable = true;
		this.searchEnabled = true;
	}

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
	 * @return Retorna el valor del atributo visibleName.
	 */
	@XmlAttribute(name = "visibleName")
	public String getVisibleName() {
		return visibleName;
	}
	/**
	 * @param pVisibleName Establece el valor del atributo visibleName.
	 */
	public void setVisibleName(String pVisibleName) {
		visibleName = pVisibleName;
	}
	/**
	 * @return Retorna el valor del atributo fieldType.
	 */
	@XmlAttribute(name = "fieldType")
	public FieldType getFieldType() {
		return fieldType;
	}
	/**
	 * @param pFieldType Establece el valor del atributo fieldType.
	 */
	public void setFieldType(FieldType pFieldType) {
		fieldType = pFieldType;
	}
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
	 * @return Retorna el valor del atributo width.
	 */
	@XmlAttribute(name = "width")
	public Integer getWidth() {
		return width;
	}
	/**
	 * @param pWidth Establece el valor del atributo width.
	 */
	public void setWidth(Integer pWidth) {
		width = pWidth;
	}
	/**
	 * @return Retorna el valor del atributo order.
	 */
	@XmlAttribute(name = "order")
	public Integer getOrder() {
		return order;
	}
	/**
	 * @param pOrder Establece el valor del atributo order.
	 */
	public void setOrder(Integer pOrder) {
		order = pOrder;
	}
	/**
	 * @return Retorna el valor del atributo mask.
	 */
	@XmlAttribute(name = "mask")
	public String getMask() {
		return mask;
	}
	/**
	 * @param pMask Establece el valor del atributo mask.
	 */
	public void setMask(String pMask) {
		mask = pMask;
	}
	/**
	 * @return Retorna el valor del atributo gridVisible.
	 */
	@XmlAttribute(name = "gridVisible")
	public Boolean getGridVisible() {
		return gridVisible;
	}
	/**
	 * @param pVisible Establece el valor del atributo gridVisible.
	 */
	public void setGridVisible(Boolean pVisible) {
		gridVisible = pVisible;
	}
	/**
	 * @return the crudVisible
	 */
	@XmlAttribute(name = "crudVisible")
	public Boolean getCrudVisible() {
		return crudVisible;
	}

	/**
	 * @param pCrudVisible the crudVisible to set
	 */
	public void setCrudVisible(Boolean pCrudVisible) {
		crudVisible = pCrudVisible;
	}

	/**
	 * @return the editable
	 */
	@XmlAttribute(name = "editable")
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param pEditable the editable to set
	 */
	public void setEditable(Boolean pEditable) {
		editable = pEditable;
	}

	/**
	 * @return Retorna el valor del atributo searchEnabled.
	 */
	@XmlAttribute(name = "searchEnabled")
	public Boolean getSearchEnabled() {
		return searchEnabled;
	}
	/**
	 * @param pSearchEnabled Establece el valor del atributo searchEnabled.
	 */
	public void setSearchEnabled(Boolean pSearchEnabled) {
		searchEnabled = pSearchEnabled;
	}

	/**
	 * @return the lookupName
	 */
	@XmlAttribute(name = "lookupName")
	public String getLookupName() {
		return lookupName;
	}

	/**
	 * @param pLookupName the lookupName to set
	 */
	public void setLookupName(String pLookupName) {
		lookupName = pLookupName;
	}

	/**
	 * @return Retorna el valor del atributo converter.
	 */
	@XmlAttribute(name = "converter")
	public String getConverter() {
		return converter;
	}

	/**
	 * @param pConverter Establece el valor del atributo converter.
	 */
	public void setConverter(String pConverter) {
		converter = pConverter;
	}

	/**
	 * @return the enumType
	 */
	@SuppressWarnings("rawtypes")
	@XmlAttribute(name = "enumType")
	public Class getEnumType() {
		return enumType;
	}

	/**
	 * @param pEnumType the enumType to set
	 */
	@SuppressWarnings("rawtypes")
	public void setEnumType(Class pEnumType) {
		enumType = pEnumType;
	}

}
