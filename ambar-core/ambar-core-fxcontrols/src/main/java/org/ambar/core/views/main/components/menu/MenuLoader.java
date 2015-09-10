/**
 *
 */
package org.ambar.core.views.main.components.menu;

import javafx.scene.control.Accordion;

/**
 * Interfaz que define las operaciones a ser implementadas por
 * la clase que se encarga de cargar el menu principal de entidades de
 * la aplicacion.
 *
 * @author Sebastian
 *
 */
public interface MenuLoader {

    /**
     * Carga el menu principal.
     *
	 * @param pAccordion Accordion
	 *
	 * */
	void loadAccordionMenu(Accordion pAccordion);

}
