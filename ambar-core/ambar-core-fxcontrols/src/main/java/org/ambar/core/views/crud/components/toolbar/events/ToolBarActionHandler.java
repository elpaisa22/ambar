/**
 * ambar-core-fxcontrols [20/02/2014 00:54:45]
 */
package org.ambar.core.views.crud.components.toolbar.events;

import javafx.event.EventHandler;


/**
 * <p>
 * Clase que representa la clase abstracta que tiene la capacidad de
 * "escuchar" eventos del ToolBar.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface ToolBarActionHandler extends EventHandler<ToolBarEvent> {

	/**
	 * Metodo a implementar por la clase concreta en el cual se
	 * manejara el evento.
	 * @param pToolBarEvent Evento
	 * */
	void handle(ToolBarEvent pToolBarEvent);
}
