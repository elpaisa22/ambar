/**
 * ambar-core-dictionary [12/03/2012 22:07:23]
 */
package org.ambar.core.dictionary.domain.navigation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa la lista de info de entidades de navegacion.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "entitiesInformation", namespace = "http://www.ambar.org/navigation")
public class EntitiesInformation {

	private List<EntityInfo> entityInfos;

	/**
	 * @return Retorna el valor del atributo entityInfos.
	 */
	@XmlElements({
        @XmlElement(name = "entity", type = EntityInfo.class, namespace = "http://www.ambar.org/navigation")
    })
	public List<EntityInfo> getEntities() {
		return entityInfos;
	}

	/**
	 * @param pEntityInfos Establece el valor del atributo entityInfos.
	 */
	public void setEntities(List<EntityInfo> pEntityInfos) {
		entityInfos = pEntityInfos;
	}
}
