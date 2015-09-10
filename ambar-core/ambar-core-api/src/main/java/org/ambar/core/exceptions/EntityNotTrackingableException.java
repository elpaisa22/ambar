/**
 * foundation-api [21/08/2011 23:21:14]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción que se lanza al encontrar una entidad que no implementa
 * Auditable.
 * </p>
 *
 * @author Sebastian
 *
 */
public class EntityNotTrackingableException extends SystemException {
	private static final long serialVersionUID = -8870980454992640704L;

	/**
	 * Constructor por default.
	 */
	public EntityNotTrackingableException() {
		super();
	}

	/**
	 * @param pMessage Mensaje <i>personalizado</i> del error
	 */
	public EntityNotTrackingableException(final String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage Mensaje <i>personalizado</i> del error
	 * @param pCause Orígen de la excepción
	 */
	public EntityNotTrackingableException(final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	}
}
