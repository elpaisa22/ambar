/**
 * ambar-core-api [20/08/2011 22:23:58]
 */
package org.ambar.core.exceptions;

/**
 * Aplicación base para manejar las excepciones de negocio que son recuperables.
 *
 * @author Sebastian
 *
 */
public class AmbarException extends Exception {
	private static final long serialVersionUID = 1L;
	/**
	 * Módulo relacionado con al excepción.
	 * */
	private String cModule;
	/**
	 * Constructor.
	 */
	public AmbarException() {
		super();
	}
	/**
	 * Constructor con argumentos mensaje.
	 * @param pMessage String
	 */
	 public AmbarException(String pMessage) {
		super(pMessage);
	}
	/**
	 * Constructor Constructor con argumentos mensaje y módulo.
	 * @param pMessage String
	 * @param pModule String
	 */
	public AmbarException(String pMessage, String pModule) {
		super(pMessage);
		this.setModule(pModule);
	}
	/**
	 * Constructor Constructor con argumentos excepción lanzada.
	 * @param pThr Throwable
	 */
	public AmbarException(Throwable pThr) {
		super(pThr);
	}
	/**
	 * Constructor con argumentos excepción lanzada y módulo.
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public AmbarException(Throwable pThr, String pModule) {
		super(pThr);
		this.setModule(pModule);
	}
	/**
	 * Constructor con mensaje argumentos y excepción lanzada.
	 * @param pMessage String
	 * @param pThr Throwable
	 */
	public AmbarException(String pMessage, Throwable pThr) {
		super(pMessage, pThr);
	}
	/**
	 * Constructor argumentos con mensaje,  excepción lanzada y módulo.
	 * @param pMessage String
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public AmbarException(String pMessage, Throwable pThr, String pModule) {
		super(pMessage, pThr);
		this.setModule(pModule);
	}

	/**
	 *
	 * @return Devuelve del módulo asociado a la excepción producida.
	 *
	 */
	public String getModule() {
		return cModule;
	}

	/**
	 * Set del módulo asociado a la excepción producida.
	 * @param pModule String
	 */
	public void setModule(String pModule) {
		this.cModule = pModule;
	}

	/**
	 * Devuelve el mensaje que contiene la Excepción.
	 * @return String
	 */
	public String getMessage() {
		StringBuffer sb = new StringBuffer("");
		sb.append("Module :: ");
		if (this.cModule != null) {
			sb.append(this.cModule).append("\n");
		} else {
			sb.append("Not specified").append("\n");
		}
		sb.append("Type :: Checked, application or business exception").append("\n");
		sb.append("Message :: ").append(super.getMessage());
		return sb.toString();
	}
}
