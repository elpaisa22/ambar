/**
 * ambar-core-api [20/08/2011 00:28:07]
 */
package org.ambar.core.dto.results;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Clase que tipifica los mensajes que retornan los servicios del sistema.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.dto.results.MessageKind
 */
public class MessageResult  implements Serializable {

	private static final long serialVersionUID = 674744562346867443L;

	private MessageKind kind;
	private Integer messageNum;
	private String messageKind;
	private String message;
	private String caller;
	private Integer errorCode;
	private String strackTrace;
	private Date timeStamp;
	private String source;

	/**
	 * <p>
	 * Constructor por default, establece Tipo = "Info".
	 * </p>
	 */
	public MessageResult() {
		timeStamp = new Date();
		kind = MessageKind.Info;
	}

	/**
	 * <p>
	 * Constructor que recibe un mensaje.
	 * </p>
	 * @param pMessage Mensaje a mostrar
	 */
	public MessageResult(String pMessage) {
		this();
		message = pMessage;
	}

	/**
	 * <p>
	 * Constructor que recibe una excepci�n.
	 * </p>
	 * @param pEx Excepción a registrar
	 */
	public MessageResult(Exception pEx) {
		this(pEx.getMessage());
		strackTrace = pEx.toString();
		source = pEx.getCause().toString();
		kind = MessageKind.Error;
	}

	/**
	 * @return Retorna el valor del atributo kind.
	 */
	public MessageKind getKind() {
		return this.kind;
	}

	/**
	 * @param pKind Establece el valor del atributo kind.
	 */
	public void setKind(MessageKind pKind) {
		this.kind = pKind;
	}

	/**
	 * @return Retorna el valor del atributo messageNum.
	 */
	public Integer getMessageNum() {
		return this.messageNum;
	}

	/**
	 * @param pMessageNum Establece el valor del atributo messageNum.
	 */
	public void setMessageNum(Integer pMessageNum) {
		this.messageNum = pMessageNum;
	}

	/**
	 * @return Retorna el valor del atributo messageKind.
	 */
	public String getMessageKind() {
		return this.messageKind;
	}

	/**
	 * @param pMessageKind Establece el valor del atributo messageKind.
	 */
	public void setMessageKind(String pMessageKind) {
		this.messageKind = pMessageKind;
	}

	/**
	 * @return Retorna el valor del atributo message.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * @param pMessage Establece el valor del atributo message.
	 */
	public void setMessage(String pMessage) {
		this.message = pMessage;
	}

	/**
	 * @return Retorna el valor del atributo caller.
	 */
	public String getCaller() {
		return this.caller;
	}

	/**
	 * @param pCaller Establece el valor del atributo caller.
	 */
	public void setCaller(String pCaller) {
		this.caller = pCaller;
	}

	/**
	 * @return Retorna el valor del atributo errorCode.
	 */
	public Integer getErrorCode() {
		return this.errorCode;
	}

	/**
	 * @param pErrorCode Establece el valor del atributo errorCode.
	 */
	public void setErrorCode(Integer pErrorCode) {
		this.errorCode = pErrorCode;
	}

	/**
	 * @return Retorna el valor del atributo strackTrace.
	 */
	public String getStrackTrace() {
		return this.strackTrace;
	}

	/**
	 * @param pStrackTrace Establece el valor del atributo strackTrace.
	 */
	public void setStrackTrace(String pStrackTrace) {
		this.strackTrace = pStrackTrace;
	}

	/**
	 * @return Retorna el valor del atributo timeStamp.
	 */
	public Date getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * @param pTimeStamp Establece el valor del atributo timeStamp.
	 */
	public void setTimeStamp(Date pTimeStamp) {
		this.timeStamp = pTimeStamp;
	}

	/**
	 * @return Retorna el valor del atributo source.
	 */
	public String getSource() {
		return this.source;
	}

	/**
	 * @param pSource Establece el valor del atributo source.
	 */
	public void setSource(String pSource) {
		this.source = pSource;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageResult [");
		if (caller != null) {
			builder.append("caller=");
			builder.append(caller);
			builder.append(", ");
		}
		if (errorCode != null) {
			builder.append("errorCode=");
			builder.append(errorCode);
			builder.append(", ");
		}
		if (kind != null) {
			builder.append("kind=");
			builder.append(kind);
			builder.append(", ");
		}
		if (message != null) {
			builder.append("message=");
			builder.append(message);
			builder.append(", ");
		}
		if (messageKind != null) {
			builder.append("messageKind=");
			builder.append(messageKind);
			builder.append(", ");
		}
		if (messageNum != null) {
			builder.append("messageNum=");
			builder.append(messageNum);
			builder.append(", ");
		}
		if (source != null) {
			builder.append("source=");
			builder.append(source);
			builder.append(", ");
		}
		if (strackTrace != null) {
			builder.append("strackTrace=");
			builder.append(strackTrace);
			builder.append(", ");
		}
		if (timeStamp != null) {
			builder.append("timeStamp=");
			builder.append(timeStamp);
		}
		builder.append("]");
		return builder.toString();
	}
}
