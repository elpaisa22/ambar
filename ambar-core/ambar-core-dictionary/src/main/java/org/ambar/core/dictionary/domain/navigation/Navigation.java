/**
 * ambar-core-dictionary [12/03/2012 22:19:29]
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
 * Clase que representa la informacion de navegacion.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "navigation", namespace = "http://www.ambar.org/navigation")
public class Navigation {

	private List<Module> modules;

	/**
	 * @return Retorna el valor del atributo modules.
	 */
	@XmlElements({
        @XmlElement(name = "module", type = Module.class, namespace = "http://www.ambar.org/navigation")
    })
	public List<Module> getModules() {
		return modules;
	}

	/**
	 * @param pModules Establece el valor del atributo modules.
	 */
	public void setModules(List<Module> pModules) {
		modules = pModules;
	}
}
