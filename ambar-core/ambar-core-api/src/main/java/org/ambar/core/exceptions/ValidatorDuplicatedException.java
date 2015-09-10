/**
 * ambar-core-api [21/08/2011 23:34:03]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción que se lanza cuando un <i>validator</i> aparece
 * mas de una vez en la configuración de profiles.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ValidatorDuplicatedException extends SystemException {

	private static final long serialVersionUID = 6469636833379285162L;

	/**
	 * Constructor por default.
	 */
	public ValidatorDuplicatedException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Orígen de la excepción
	 */
	public ValidatorDuplicatedException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public ValidatorDuplicatedException(String pMessage) {
		super(pMessage);
	}
}
