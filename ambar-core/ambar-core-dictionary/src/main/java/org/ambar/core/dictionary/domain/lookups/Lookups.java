/**
 * ambar-core-dictionary [18/01/2014 12:01:08]
 */
package org.ambar.core.dictionary.domain.lookups;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase raíz de la jerarquía correspondiente en el xml donde
 * se configura información de los Lookups.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlRootElement(name = "lookups")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType
public class Lookups implements Serializable {

	private static final long serialVersionUID = 7661726072420610939L;

	private List<LookupMetaData> lookups;

	/**
	 * @return Retorna el valor del atributo lookups.
	 */
    @XmlElements({
        @XmlElement(name = "lookup", type = LookupMetaData.class)
    })
	public List<LookupMetaData> getLookups() {
		return lookups;
	}

	/**
	 * @param pLookups Establece el valor del atributo lookups.
	 */
	public void setLookups(List<LookupMetaData> pLookups) {
		lookups = pLookups;
	}
}
