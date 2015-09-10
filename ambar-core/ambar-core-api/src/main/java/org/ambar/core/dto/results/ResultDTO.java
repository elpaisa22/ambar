/**
 * ambar-core-api [20/08/2011 00:24:14]
 */
package org.ambar.core.dto.results;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Clase base para los valores de retorno de los servicios.
 * </p>
 *
 * @author Sebastian
 * @see org.ambar.core.dto.results.MessageResult
 */
public abstract class ResultDTO implements Serializable {
	private static final long serialVersionUID = -4429740537297942183L;
	private List<MessageResult> messages;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public ResultDTO() {
		messages = new ArrayList<MessageResult>();
	}

	/**
	 * <p>
	 * Constructor que recibe la lista de mensajes por parámetro.
	 * </p>
	 *
	 * @param pMessages Lista de mensajes
	 */
	public ResultDTO(List<MessageResult> pMessages) {
		messages = pMessages;
	}

	/**
	 * @return Retorna el valor del atributo messages.
	 */
	public List<MessageResult> getMessages() {
		return this.messages;
	}

	/**
	 * @param pMessages Establece el valor del atributo messages.
	 */
	public void setMessages(List<MessageResult> pMessages) {
		this.messages = pMessages;
	}

	/**
	 * <p>
	 * Retorna el número de mensajes de Error que se produjeron.
	 * </p>
	 *
	 * @return count número de mensajes de Error.
	 */
	public Integer countErrors() {
		int count = 0;
		for (MessageResult pMessages : messages) {
			if (pMessages.getKind() == MessageKind.Error) {
				count++;
			}
		}
		return count;
	}

	/**
	 * <p>
	 * Retorna el número de mensajes de Alerta que se produjeron.
	 * </p>
	 *
	 * @return count número de mensajes de Alerta.
	 */
	public Integer countWarnings() {
		int count = 0;
		for (MessageResult pMessages : messages) {
			if (pMessages.getKind() == MessageKind.Warning) {
				count++;
			}
		}
		return count;
	}

	/**
	 * <p>
	 * Retorna el número de mensajes de Información que se produjeron.
	 * </p>
	 *
	 * @return count número de mensajes de Información.
	 */
	public Integer countInfos() {
		int count = 0;
		for (MessageResult pMessages : messages) {
			if (pMessages.getKind() == MessageKind.Info) {
				count++;
			}
		}
		return count;
	}

	/**
	 * <p>
	 * Retorna un Boolean de acuerdo al valor retornado por el método
	 * countErrors().
	 * </p>
	 *
	 * @return Devuelve true countErrors() es mayor que 0.
	 */
	public Boolean hasErrors() {
		return countErrors() > 0;
	}

	/**
	 * <p>
	 * Retorna un Boolean de acuerdo al valor retornado por el método
	 * countWarnings().
	 * </p>
	 *
	 * @return Devuelve true countWarnings() es mayor que 0.
	 */
	public Boolean hasWarnings() {
		return countWarnings() > 0;
	}

	/**
	 * <p>
	 * Retorna un Boolean de acuerdo al valor retornado por el método
	 * countInfos().
	 * </p>
	 *
	 * @return Devuelve true countInfos() es mayor que 0.
	 */
	public Boolean hasInfos() {
		return countInfos() > 0;
	}

	/*
	 * (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultDTO [");
		if (messages != null) {
			builder.append("messages=");
			builder.append(messages);
		}
		builder.append("]");
		return builder.toString();
	}
}
