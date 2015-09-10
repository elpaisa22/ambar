/**
 * ambar-core-fxcontrols [20/02/2014 00:46:02]
 */
package org.ambar.core.views.crud.components.toolbar.events;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * <p>
 * Evento disparado por el ToolBar del ABM al presionar un boton.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ToolBarEvent extends Event {

	private static final long serialVersionUID = 6103843972392163647L;

	public static final int BUTTONNEW = 1;
	public static final int BUTTONEDIT = 2;
	public static final int BUTTONDELETE = 3;
	public static final int BUTTONSAVE = 4;
	public static final int BUTTONCANCEL = 5;
	public static final int BUTTONPRINT = 6;


	public static final EventType<ToolBarEvent> TOOLBAREVENTTYPE = new EventType<ToolBarEvent>();


	private int actionType;

	/**
	 * @return Retorna el valor del atributo actionType.
	 */
	public int getActionType() {
		return actionType;
	}

	/**
	 * Constructor default.
	 * @param pEventType Event type
	 * */
	public ToolBarEvent(int pEventType) {
		super(TOOLBAREVENTTYPE);
		this.actionType = pEventType;
	}
}
