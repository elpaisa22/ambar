/**
 * ambar-core-impl [12/10/2011 08:23:26]
 */
package org.ambar.core.commons.mapping.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Clase que encapsula las configuraciones utilizadas en los servicios
 * de <i>mapping</i>.</p>
 *
 * @author Sebastian
 *
 */
@XmlRootElement(name = "mappings", namespace = "http://www.ambar.org/mapper")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType
public class Mappings implements Serializable {

	private static final long serialVersionUID = -5089707781407353689L;

	private Configuration configuration;
	private List<Mapping> mappings;

	/**
	 * Constructor por default.
	 */
	public Mappings() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo configuration.
	 */
	@XmlElement(name = "configuration", type = Configuration.class, namespace = "http://www.ambar.org/mapper")
	public Configuration getConfiguration() {
		return this.configuration;
	}

	/**
	 * @param pConfiguration Establece el valor del atributo configuration.
	 */
	public void setConfiguration(Configuration pConfiguration) {
		this.configuration = pConfiguration;
	}

	/**
	 * @return Retorna el valor del atributo mappings.
	 */
	@XmlElements({
		@XmlElement(name = "mapping", type = Mapping.class, namespace = "http://www.ambar.org/mapper")
	})
	public List<Mapping> getMappings() {
		return this.mappings;
	}

	/**
	 * @param pMappings Establece el valor del atributo mappings.
	 */
	public void setMappings(List<Mapping> pMappings) {
		this.mappings = pMappings;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mappings [configuration=");
		builder.append(this.configuration);
		builder.append(", mappings=");
		builder.append(this.mappings);
		builder.append("]");
		return builder.toString();
	}
}

