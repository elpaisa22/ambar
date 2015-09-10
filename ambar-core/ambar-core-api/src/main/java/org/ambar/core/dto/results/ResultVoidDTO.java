/**
 * ambar-core-api [20/08/2011 00:32:02]
 */
package org.ambar.core.dto.results;

import java.util.List;

import org.ambar.core.dto.DTO;

/**
 * <p>
 * Clase base para los servicios que retornan {@link Void}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T>
 * @see org.ambar.core.dto.DTO
 */
public class ResultVoidDTO<T extends DTO<?>> extends ResultDTO {

	private static final long serialVersionUID = -444922977126768776L;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public ResultVoidDTO() {
	}

	/**
	 * <p>
	 * Constructor que recibe por parametro la lista de mensajes.
	 * </p>
	 * @param pMessage Lista de mensajes
	 */
	public ResultVoidDTO(List<MessageResult> pMessage) {
		super(pMessage);
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.dto.results.ResultDTO#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultVoidDTO [");
		if (getMessages() != null) {
			builder.append("getMessages()=");
			builder.append(getMessages());
		}
		builder.append("]");
		return builder.toString();
	}
}
