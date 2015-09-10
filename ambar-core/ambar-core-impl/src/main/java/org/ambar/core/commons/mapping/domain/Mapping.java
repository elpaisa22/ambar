/**
 * ambar-core-impl [12/10/2011 08:20:34]
 */
package org.ambar.core.commons.mapping.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Entidad usada para configurar los servicios de <i>mapping</i> que
 * encapsula los valores de mapeo de clase.</p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "mapping", namespace = "http://www.ambar.org/mapper")
public class Mapping implements Serializable {

	private static final long serialVersionUID = 2047814257993578293L;

	private String classA;
	private String classB;
	private String dateFormat;
	private boolean stopOnErrors;
	private boolean wildcard;
	private boolean trimStrings;
	private boolean mapNull;
	private boolean mapEmptyString;
	private List<Field> fields;
	private List<FieldExclude> fieldsExcluded;

	/**
	 * Constructor por default.
	 */
	public Mapping() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo classA.
	 */
	@XmlElement(name = "class-a", namespace = "http://www.ambar.org/mapper")
	public String getClassA() {
		return this.classA;
	}

	/**
	 * @param pClassA Establece el valor del atributo classA.
	 */
	public void setClassA(final String pClassA) {
		this.classA = pClassA;
	}

	/**
	 * @return Retorna el valor del atributo classB.
	 */
	@XmlElement(name = "class-b", namespace = "http://www.ambar.org/mapper")
	public String getClassB() {
		return this.classB;
	}

	/**
	 * @param pClassB Establece el valor del atributo classB.
	 */
	public void setClassB(final String pClassB) {
		this.classB = pClassB;
	}

	/**
	 * @return Retorna el valor del atributo fields.
	 */
	@XmlElements({
		@XmlElement(name = "field", type = Field.class, namespace = "http://www.ambar.org/mapper")
	})
	public List<Field> getFields() {
		return this.fields;
	}

	/**
	 * @param pFields Establece el valor del atributo fields.
	 */
	public void setFields(final List<Field> pFields) {
		this.fields = pFields;
	}

	/**
	 * @return Retorna el valor del atributo fieldsExcluded.
	 */
	@XmlElements({
		@XmlElement(
				name = "field-exclude",
				type = FieldExclude.class,
				namespace = "http://www.ambar.org/mapper")
	})
	public List<FieldExclude> getFieldsExcluded() {
		return this.fieldsExcluded;
	}

	/**
	 * @param pFieldsExcluded Establece el valor del atributo fieldsExcluded.
	 */
	public void setFieldsExcluded(List<FieldExclude> pFieldsExcluded) {
		this.fieldsExcluded = pFieldsExcluded;
	}

	/**
	 * @return Retorna el valor del atributo dateFormat.
	 */
	@XmlAttribute(name = "date-format")
	public String getDateFormat() {
		return this.dateFormat;
	}

	/**
	 * @param pDateFormat Establece el valor del atributo dateFormat.
	 */
	public void setDateFormat(String pDateFormat) {
		this.dateFormat = pDateFormat;
	}

	/**
	 * @return Retorna el valor del atributo stopOnErrors.
	 */
	@XmlAttribute(name = "stop-on-errors")
	public boolean isStopOnErrors() {
		return this.stopOnErrors;
	}

	/**
	 * @param pStopOnErrors Establece el valor del atributo stopOnErrors.
	 */
	public void setStopOnErrors(boolean pStopOnErrors) {
		this.stopOnErrors = pStopOnErrors;
	}

	/**
	 * @return Retorna el valor del atributo wildcard.
	 */
	@XmlAttribute(name = "wildcard")
	public boolean isWildcard() {
		return this.wildcard;
	}

	/**
	 * @param pWildcard Establece el valor del atributo wildcard.
	 */
	public void setWildcard(boolean pWildcard) {
		this.wildcard = pWildcard;
	}

	/**
	 * @return Retorna el valor del atributo trimStrings.
	 */
	@XmlAttribute(name = "trim-strings")
	public boolean isTrimStrings() {
		return this.trimStrings;
	}

	/**
	 * @param pTrimStrings Establece el valor del atributo trimStrings.
	 */
	public void setTrimStrings(boolean pTrimStrings) {
		this.trimStrings = pTrimStrings;
	}

	/**
	 * @return Retorna el valor del atributo mapNull.
	 */
	@XmlAttribute(name = "map-null")
	public boolean isMapNull() {
		return this.mapNull;
	}

	/**
	 * @param pMapNull Establece el valor del atributo mapNull.
	 */
	public void setMapNull(boolean pMapNull) {
		this.mapNull = pMapNull;
	}

	/**
	 * @return Retorna el valor del atributo mapEmptyString.
	 */
	@XmlAttribute(name = "map-empty-string")
	public boolean isMapEmptyString() {
		return this.mapEmptyString;
	}

	/**
	 * @param pMapEmptyString Establece el valor del atributo mapEmptyString.
	 */
	public void setMapEmptyString(boolean pMapEmptyString) {
		this.mapEmptyString = pMapEmptyString;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mapping [classA=");
		builder.append(this.classA);
		builder.append(", classB=");
		builder.append(this.classB);
		builder.append(", dateFormat=");
		builder.append(this.dateFormat);
		builder.append(", stopOnErrors=");
		builder.append(this.stopOnErrors);
		builder.append(", wildcard=");
		builder.append(this.wildcard);
		builder.append(", trimStrings=");
		builder.append(this.trimStrings);
		builder.append(", mapNull=");
		builder.append(this.mapNull);
		builder.append(", mapEmptyString=");
		builder.append(this.mapEmptyString);
		builder.append(", fields=");
		builder.append(this.fields);
		builder.append(", fieldsExcluded=");
		builder.append(this.fieldsExcluded);
		builder.append("]");
		return builder.toString();
	}
}

