/**
 * ambar-core-api [21/08/2011 15:09:51]
 */
package org.ambar.core.exceptions;


/**
 * <p>
 * Excepción Runtime para los componentes del core.
 * </p>
 *
 * @author Sebastian
 *
 */
public class CoreRuntimeException extends AmbarRuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 */
	public CoreRuntimeException() {
		super();
	}

	/**
	 * Constructor con argumentos mensaje y módulo.
	 * @param pMessage String
	 * @param pModule String
	 */
	public CoreRuntimeException(String pMessage, String pModule) {
		super(pMessage, pModule);
	}

	/**
	 * Constructor con argumentos mensaje, excepción origen y módulo.
	 * @param pMessage String
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public CoreRuntimeException(String pMessage, Throwable pThr, String pModule) {
		super(pMessage, pThr, pModule);
	}

	/**
	 * Constructor con argumentos mensaje, excepción origen.
	 * @param pMessage String
	 * @param pThr Throwable
	 */
	public CoreRuntimeException(String pMessage, Throwable pThr) {
		super(pMessage, pThr);
	}

	/**
	 * Constructor con argumentos mensaje.
	 * @param pMessage String
	 */
	public CoreRuntimeException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructor Constructor con argumentos excepción origen y módulo.
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public CoreRuntimeException(Throwable pThr, String pModule) {
		super(pThr, pModule);
	}

	/**
	 * Constructor con argumentos excepción origen.
	 * @param pThr Throwable
	 */
	public CoreRuntimeException(Throwable pThr) {
		super(pThr);
	}

}
