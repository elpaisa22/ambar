/**
 * ambar-core-impl [12/10/2011 08:27:24]
 */
package org.ambar.core.commons.mapping.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>Excepción que se lanza ante errores de una operación de mapeo
 * fundamentalmente cuando se manipulan datos por <i>reflection</i>.</p>
 *
 * @author Sebastian
 *
 */
public class MappingOperationException extends SystemException {
	private static final long serialVersionUID = -6033861535814901660L;

	/**
	 * Constructor por default.
	 */
	public MappingOperationException() {
		super();
	}

	/**
	 * @param pCause	Origen de la excepción
	 */
	public MappingOperationException(Throwable pCause) {
		super(pCause);
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public MappingOperationException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public MappingOperationException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}

