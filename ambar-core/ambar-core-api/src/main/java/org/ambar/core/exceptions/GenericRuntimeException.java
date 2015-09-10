/**
 * ambar-core-api [21/08/2011 15:12:29]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción base para errores en la infraestructura del sistema.
 * </p>
 *
 * @author Sebastian
 *
 */
public class GenericRuntimeException extends CoreRuntimeException {

	private static final long serialVersionUID = -5214388850733933168L;

	/**
	 *
	 */
	public GenericRuntimeException() {
	}

	/**
	 * @param pCause Orígen de la excepción
	 */
	public GenericRuntimeException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * @param pMessage Mensaje <i>personalizado</i> del error
	 */
	public GenericRuntimeException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage Mensaje <i>personalizado</i> del error
	 * @param pCause Orígen de la excepción
	 */
	public GenericRuntimeException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}
