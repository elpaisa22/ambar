/**
 * ambar-core-api [20/08/2011 23:12:59]
 */
package org.ambar.core.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Excepción para las validaciones incumplidas.</p>
 *
 * @author Sebastian
 *
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1390640126044051254L;
	private List<String> messages;

	/**
	 * @param pMessage Mensaje de error
	 */
	public BusinessException(final String pMessage) {
		super();
		this.messages = new ArrayList<String>();
		this.messages.add(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public BusinessException(final String pMessage, final Throwable pCause) {
		super(pCause);
		this.messages = new ArrayList<String>();
		this.messages.add(pMessage);
	}

	/**
	 * @param pMessages Lista de mensajes de error
	 */
	public BusinessException(final List<String> pMessages) {
		super();
		this.messages = pMessages;
	}

	/**
	 * @param pMessages	Lista de mensajes de error
	 * @param pCause	Origen de la excepción
	 */
	public BusinessException(final List<String> pMessages, final Throwable pCause) {
		super(pCause);
		this.messages = pMessages;
	}

	/**
	 * @return Retorna el valor del atributo messages.
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * @param pMessages Establece el valor del atributo messages.
	 */
	public void setMessages(final List<String> pMessages) {
		messages = pMessages;
	}

}
