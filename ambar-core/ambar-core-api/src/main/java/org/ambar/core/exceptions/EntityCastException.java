/**
 * ambar-core-api [26/1/2015 17:37:33]
 */
package org.ambar.core.exceptions;

/**
 * <p>
 * Excepción a lanzar cuando se produzca un error de <i>cast</i>
 * con una entidad de negocio.
 * </p>
 *
 * @author Sebastian
 *
 */
public class EntityCastException extends SystemException {

	private static final long serialVersionUID = 8211596704881057779L;

	/**
	 * Constructor por default.
	 */
	public EntityCastException() {
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public EntityCastException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public EntityCastException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}