/**
 * ambar-core-testing [17/09/2011 01:58:05]
 */
package org.ambar.core.testing.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>
 * Excepción que se dispara cuando esta mal configurada la clase
 * del BO en los Test de BO.
 * </p>
 *
 * @author Sebastian
 *
 */
public class InvalidBusinessObjectClassForTestException  extends SystemException {

	private static final long serialVersionUID = -2291486668148192418L;

	/**
	 * Constructor por default.
	 */
	public InvalidBusinessObjectClassForTestException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public InvalidBusinessObjectClassForTestException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public InvalidBusinessObjectClassForTestException(String pMessage) {
		super(pMessage);
	}
}
