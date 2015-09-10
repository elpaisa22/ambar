/**
 * ambar-core-api [22/08/2011 13:04:22]
 */
package org.ambar.core.exceptions;


/**
 *
 * @author Sebastian
 *
 */
public class InvalidMappingForFilterException extends SystemException {

	private static final long serialVersionUID = -7036363552315363963L;

	/**
	 * Constructor por default.
	 */
	public InvalidMappingForFilterException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public InvalidMappingForFilterException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public InvalidMappingForFilterException(String pMessage) {
		super(pMessage);
	}
}
