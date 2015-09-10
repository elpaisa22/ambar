/**
 * ambar-core-api [21/08/2011 15:04:19]
 */
package org.ambar.core.exceptions;

/**
 * <p>
 * Excepción Base para el sistema (o no recuperable) excepción declarada por los componentes del framework.
 * tambien, se puede usar en aplicaciones que usen el Framework de Ambar declarandola como excepcion
 * no recuperable. En estos casos, será usado para declarar un cModule para el manejo de la Excepcion.
 * </p>
 *
 * @author Sebastian
 *
 */
public class AmbarRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Módulo relacionado con al excepción.
	 * */
	private String cModule;

	/**
	 * Base constructor.
	 */
	public AmbarRuntimeException() {
	}
	/**
	 * Constructor con argumento mensaje.
	 *
	 * @param pMessage the message to be showed
	 */
	public AmbarRuntimeException(String pMessage) {
		super(pMessage);
	}

	/**
	 * Constructor con argumentos mensaje y módulo.
	 * @param pMessage String
	 * @param pModule String
	 */
	public AmbarRuntimeException(String pMessage, String pModule) {
		super(pMessage);
		this.setModule(pModule);
	}
	/**
	 * Constructor con argumentos excepción lanzada.
	 * @param pThr Throwable
	 */
	public AmbarRuntimeException(Throwable pThr) {
		super(pThr);
	}
	/**
	 * Constructor con argumentos excepción lanzada y módulo.
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public AmbarRuntimeException(Throwable pThr, String pModule) {
		super(pThr);
		this.setModule(pModule);
	}
	/**
	 * Constructor con mensaje argumentos y excepción lanzada.
	 * @param pMessage String
	 * @param pThr Throwable
	 */
	public AmbarRuntimeException(String pMessage, Throwable pThr) {
		super(pMessage, pThr);
	}

	/**
	 * Constructor.
	 * @param pMessage String
	 * @param pThr Throwable
	 * @param pModule String
	 */
	public AmbarRuntimeException(String pMessage, Throwable pThr, String pModule) {
		super(pMessage, pThr);
		this.setModule(pModule);
	}

	/**
	 * @return String
	 * Devuelve del módulo asociado a la excepción producida.
	 **/
	public String getModule() {
		return cModule;
	}
	/**
	 * @param pModule String
	 * Set del módulo asociado a la excepción producida.
	 */
	public void setModule(String pModule) {
		this.cModule = pModule;
	}
	/**
	 * Devuelve el mensaje que contiene la Excepción.
	 * @see java.lang.Throwable#getMessage()
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
		sb.append("Type :: Unchecked, unrecoverable or system exception").append("\n");
		sb.append("Message :: ").append(super.getMessage());
		return sb.toString();
	}
}
