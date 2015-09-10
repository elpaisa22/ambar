/**
 * ambar-core-dictionary [12/03/2012 21:50:51]
 */
package org.ambar.core.dictionary.domain.navigation;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa el nodo raiz de la navegacion de entidades.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlRootElement(name = "navigationManager", namespace = "http://www.ambar.org/navigation")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType
public class NavigationManager implements Serializable {

	private static final long serialVersionUID = 7701658887189009849L;

	private EntitiesInformation entitiesInformation;
	private Navigation navigation;

	/**
	 * @return Retorna el valor del atributo entitiesInformation.
	 */
	@XmlElement(name = "entitiesInformation",
			    type = EntitiesInformation.class,
			    namespace = "http://www.ambar.org/navigation")
	public EntitiesInformation getEntitiesInformation() {
		return entitiesInformation;
	}

	/**
	 * @param pEntitiesInformation Establece el valor del atributo entitiesInformation.
	 */
	public void setEntitiesInformation(EntitiesInformation pEntitiesInformation) {
		entitiesInformation = pEntitiesInformation;
	}

	/**
	 * @return Retorna el valor del atributo navigation.
	 */
	@XmlElement(name = "navigation", type = Navigation.class, namespace = "http://www.ambar.org/navigation")
	public Navigation getNavigation() {
		return navigation;
	}

	/**
	 * @param pNavigation Establece el valor del atributo navigation.
	 */
	public void setNavigation(Navigation pNavigation) {
		navigation = pNavigation;
	}
}
