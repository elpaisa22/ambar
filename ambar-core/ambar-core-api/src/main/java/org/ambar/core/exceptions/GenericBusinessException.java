/**
 * ambar-core-api [20/08/2011 22:35:57]
 */
package org.ambar.core.exceptions;

import java.util.List;

/**
 * <p>
 * Excepción base para las reglas de negocio y validaciones.
 * </p>
 *
 * @author Sebastian
 * @see org.ambar.core.exceptions.CoreException
 */
public class GenericBusinessException extends GenericException {

	private static final long serialVersionUID = 9064100658842795908L;

	/**
	* Constructor por defecto.
	*/
	public GenericBusinessException() {

	}

	/**
	 * <p>
	 * <Constructor que recibe la lista de mensajes.
	 * </p>
	 *
	 * @param pMessages Lista de mensajes
	 */
	public GenericBusinessException(List<String> pMessages) {
		this.setMessages(pMessages);
	}

	/**
	 * <p>
	 * Constructor que recibe la lista de mensajes y la causa.
	 * </p>
	 *
	 * @param pMessages Lista de mensajes
	 * @param pCause Causa
	 */
	public GenericBusinessException(List<String> pMessages, Throwable pCause) {
		super(pCause);
		this.setMessages(pMessages);
	}

	/**
	 * @return Devuelve el atributo messages.
	 */
	public List<String> getMessages() {
		return this.getMessages();
	}

	/**
	 * @param pMessages Fija el valor de pMessages al campo messages
	 */
	public void setMessages(List<String> pMessages) {
		this.setMessages(pMessages);
	}

}
