package org.ambar.core.views.crud.components.lookup.events;

import javafx.event.EventHandler;

/**
 * Clase que representa la clase abstracta que tiene la capacidad de
 * "escuchar" eventos del lookup.
 *
 * @author Sebastian
 * */
public abstract class DataChangeHandler implements EventHandler<DataChangeEvent> {

	/**
	 * Metodo a implementar por la clase concreta en el cual se
	 * manejara el evento.
	 * @param pEvent Evento
	 * */
    public abstract void handle(DataChangeEvent pEvent);
}
