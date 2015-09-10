/**
 * ambar-core-api [21/08/2011 23:19:41]
 */
package org.ambar.core.exceptions;

/**
 * <p>
 * Excepción para fallas de infraestructura.
 * </p>
 *
 * @author Sebastian
 *
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -2862489861040800311L;

	/**
	 * Constructor por default.
	 */
	public SystemException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public SystemException(final String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public SystemException(final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pCause	Origen de la excepción
	 */
	public SystemException(Throwable pCause) {
		super(pCause);
	}
}
