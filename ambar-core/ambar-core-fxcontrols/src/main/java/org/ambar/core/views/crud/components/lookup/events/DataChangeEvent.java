/**
 *
 */
package org.ambar.core.views.crud.components.lookup.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 *
 * Evento disparado por el lookup al cambiar su valor.
 *
 * @author Ecclesia
 *
 */
public class DataChangeEvent extends Event {

	private static final long serialVersionUID = -4441254861438671775L;

	private Object dto;

	/**
	 * Retorna el DTO seleccionado.
	 * @return {@link Object} DTO seleccioneado.
	 * */
	public Object getDTO() {
		return dto;
	}

	/**
	 * Setea el DTO seleccionado.
	 * @param pDTO DTO seleccionado
	 * */
	public void setDTO(Object pDTO) {
		this.dto = pDTO;
	}

	public static final EventType<DataChangeEvent> DATACHANGED = new EventType<DataChangeEvent>("DATACHANGED");

	/**
	 * Constructor por default.
	 * @param pDTO DTO seleccionado.
	 * */
	public DataChangeEvent(Object pDTO) {
		super(DATACHANGED);
		this.dto = pDTO;
	}

}
