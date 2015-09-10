/**
 * ambar-core-impl [12/10/2011 08:16:40]
 */
package org.ambar.core.commons.mapping.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Entidad usada para configurar los servicios de <i>mapping</i> que
 * encapsula valores globales.</p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "configuration", namespace = "http://www.ambar.org/mapper")
public class Configuration implements Serializable {

	private static final long serialVersionUID = -6866251560059613034L;

	private boolean stopOnErrors;
	private String dateFormat;
	private boolean wildcard;
	private boolean trimStrings;

	/**
	 * Constructor por default.
	 */
	public Configuration() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo stopOnErrors.
	 */
	@XmlElement(name = "stop-on-errors", namespace = "http://www.ambar.org/mapper")
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
	 * @return Retorna el valor del atributo dateFormat.
	 */
	@XmlElement(name = "date-format", namespace = "http://www.ambar.org/mapper")
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
	 * @return Retorna el valor del atributo wildcard.
	 */
	@XmlElement(name = "wildcard", namespace = "http://www.ambar.org/mapper")
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
	@XmlElement(name = "trim-strings", namespace = "http://www.ambar.org/mapper")
	public boolean isTrimStrings() {
		return this.trimStrings;
	}

	/**
	 * @param pTrimStrings Establece el valor del atributo trimStrings.
	 */
	public void setTrimStrings(boolean pTrimStrings) {
		this.trimStrings = pTrimStrings;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Configuration [stopOnErrors=");
		builder.append(this.stopOnErrors);
		builder.append(", dateFormat=");
		builder.append(this.dateFormat);
		builder.append(", wildcard=");
		builder.append(this.wildcard);
		builder.append(", trimStrings=");
		builder.append(this.trimStrings);
		builder.append("]");
		return builder.toString();
	}
}

