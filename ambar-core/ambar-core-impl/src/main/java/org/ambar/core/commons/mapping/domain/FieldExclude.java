/**
 * ambar-core-impl [12/10/2011 08:19:24]
 */
package org.ambar.core.commons.mapping.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 * <p>Entidad usada para configurar los servicios de <i>mapping</i> que sirve para
 * establecer propiedades de clase que se excluyen del mapeo.</p>
 *
 * @author Sebastian
 *
 */
public class FieldExclude implements Serializable {

	private static final long serialVersionUID = -5614255527211510471L;

	private String fieldA;
	private String fieldB;

	/**
	 * Constructor por default.
	 */
	public FieldExclude() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo fieldA.
	 */
	@XmlElement(name = "a", namespace = "http://www.ambar.org/mapper")
	public String getFieldA() {
		return this.fieldA;
	}

	/**
	 * @param pFieldA Establece el valor del atributo fieldA.
	 */
	public void setFieldA(String pFieldA) {
		this.fieldA = pFieldA;
	}

	/**
	 * @return Retorna el valor del atributo fieldB.
	 */
	@XmlElement(name = "b", namespace = "http://www.ambar.org/mapper")
	public String getFieldB() {
		return this.fieldB;
	}

	/**
	 * @param pFieldB Establece el valor del atributo fieldB.
	 */
	public void setFieldB(String pFieldB) {
		this.fieldB = pFieldB;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FieldExclude [fieldA=");
		builder.append(this.fieldA);
		builder.append(", fieldB=");
		builder.append(this.fieldB);
		builder.append("]");
		return builder.toString();
	}
}

