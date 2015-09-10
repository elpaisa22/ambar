/**
 * ambar-core-api [22/08/2011 14:09:08]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción que el sistema lanza en el caso de no encontrar
 * la clase configurada como "profile" de un validator.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ProfileClassNotFoundException extends SystemException {

	private static final long serialVersionUID = 4488528113998068081L;

	/**
	 * Constructor por default.
	 */
	public ProfileClassNotFoundException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Orígen de la excepción
	 */
	public ProfileClassNotFoundException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public ProfileClassNotFoundException(String pMessage) {
		super(pMessage);
	}
}
