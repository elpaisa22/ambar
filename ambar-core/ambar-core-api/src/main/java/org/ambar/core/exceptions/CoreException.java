/**
 * ambar-core-api [20/08/2011 22:29:15]
 */
package org.ambar.core.exceptions;


/**
 *
 * @author Sebastian
 */
public class CoreException extends AmbarException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public CoreException() {
		super();
	}

	/**
	 * Constructor con argumentos mensaje y módulo donde se ha producido.
	 * @param pMessage String
	 * @param pModule String
	 */
	public CoreException(String pMessage, String pModule) {
		super(pMessage, pModule);
	}

	/**
	 * Constructor con argumentos mensaje, excepción origen y módulo donde se ha producido.
	 * @param pMessage String
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public CoreException(String pMessage, Throwable pThr, String pModule) {
		super(pMessage, pThr, pModule);
	}

	/**
	 * Constructor con argumentos mensaje y excepción origen.
	 * @param pMessage String
	 * @param pThr Throwable
	 */
	public CoreException(String pMessage, Throwable pThr) {
		super(pMessage, pThr);
	}

	/**
	 * Constructor con argumentos mensaje.
	 * @param pMessage String
	 */
	public CoreException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructor con argumentos excepción origen y módulo.
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public CoreException(Throwable pThr, String pModule) {
		super(pThr, pModule);
	}

	/**
	 * Constructor con argumentos excepción origen.
	 * @param pThr Throwable
	 */
	public CoreException(Throwable pThr) {
		super(pThr);
	}

}
