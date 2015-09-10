/**
 * ambar-core-api [21/08/2011 23:29:55]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción a lanzar cuando se produzca un error de <i>cast</i>
 * con un "validator".
 * </p>
 *
 * @author Sebastian
 *
 */
public class ValidatorCastException extends SystemException {

	private static final long serialVersionUID = -7887534759075584156L;

	/**
	 * Constructor por default.
	 */
	public ValidatorCastException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public ValidatorCastException(String pMessage) {
		super(pMessage);
	}

}
