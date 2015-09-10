/**
 * ambar-core-api [20/08/2011 22:32:31]
 */
package org.ambar.core.exceptions;

import java.util.List;

/**
 * <p>
 * Excepción base para las reglas de negocio y validaciones.
 * </p>
 *
 * @author Sebastian
 *
 *
 */
public class GenericException  extends CoreException {

	private static final long serialVersionUID = 9064100658842795908L;

	private List<String> messages;

	/**
	* Constructor por defecto.
	*/
	public GenericException() {
	}

	/**
	 * <p> Constructor que recibe la lista de mensajes y la causa.</p>
	 *
	 * @param pCause Causa
	 */
	public GenericException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * <p>
	 * <Constructor que recibe la lista de mensajes.
	 * </p>
	 *
	 * @param pMessages Lista de mensajes
	 */
	public GenericException(List<String> pMessages) {
		this.messages = pMessages;
	}

	/**
	 * <p>
	 * <Constructor que recibe un mensaje de error.
	 * </p>
	 *
	 * @param pMessage mensajes
	 */
	public GenericException(String pMessage) {
		super(pMessage);
	}

	/**
	 * <p>
	 * Constructor que recibe la lista de mensajes y la causa.
	 * </p>
	 *
	 * @param pMessages Lista de mensajes
	 * @param pCause Causa
	 */
	public GenericException(List<String> pMessages, Throwable pCause) {
		super(pCause);
		this.messages = pMessages;
	}

	/**
	 * @return Devuelve el atributo messages.
	 */
	protected List<String> getMessages() {
		return messages;
	}

	/**
	 * @param pMessages Fija el valor de pMessages al campo messages
	 */
	protected void setMessages(List<String> pMessages) {
		messages = pMessages;
	}
}
