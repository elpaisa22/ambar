/**
 * ambar-core-testing [17/09/2011 01:59:13]
 */
package org.ambar.core.testing.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>
 * Excepción que se dispara cuando esta mal configurado
 * el DTO en los Test de Services.
 * </p>
 *
 * @author Sebastian
 *
 */
public class InvalidDTOException extends SystemException {

	private static final long serialVersionUID = -2160117747732645312L;

	/**
	 * Constructor por default.
	 */
	public InvalidDTOException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public InvalidDTOException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public InvalidDTOException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pCause	Origen de la excepción
	 */
	public InvalidDTOException(Throwable pCause) {
		super(pCause);
	}
}
