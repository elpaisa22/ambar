/**
 * ambar-core-api [21/08/2011 23:17:47]
 */
package org.ambar.core.exceptions;


/**
 * <p>Insertar descripción funcional.</p>
 * @author Sebastian
 * @see <<Insertar clases relacionadas>>
 */
public class ServiceCastException extends SystemException {

	private static final long serialVersionUID = 5289563740752097345L;

	/**
	 * Constructor por default.
	 */
	public ServiceCastException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Orígen de la excepción
	 */
	public ServiceCastException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public ServiceCastException(String pMessage) {
		super(pMessage);
	}
}
