/**
 * ambar-core-api [20/08/2011 00:34:45]
 */
package org.ambar.core.dto.results;

import java.util.List;

import org.ambar.core.dto.DTO;

/**
 * <p>
 * Clase base para los servicios que retornan una clase que implemente {@link DTO}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T>
 * @see org.ambar.core.dto.results.ResultDTO
 */
public class ResultObjectDTO<T extends DTO<?>> extends ResultDTO {

	private static final long serialVersionUID = -2215711009574161320L;

	private T result;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public ResultObjectDTO() {
	}

	/**
	 * <p>
	 * Constructor que recibe por parámetro el objeto a retornar.
	 * </p>
	 * @param pDto Objeto a retornar
	 */
	public ResultObjectDTO(T pDto) {
		result = pDto;
	}

	/**
	 * <p>
	 * Constructor que recibe el objeto y la lista de mensajes a retornar.
	 * </p>
	 * @param pDto Objeto a retornar
	 * @param pMessages Lista de mensajes
	 */
	public ResultObjectDTO(T pDto, List<MessageResult> pMessages) {
		super(pMessages);
		result = pDto;
	}

	/**
	 * @return Retorna el valor del atributo result.
	 */
	public T getResult() {
		return this.result;
	}

	/**
	 * @param pResult Establece el valor del atributo result.
	 */
	public void setResult(T pResult) {
		this.result = pResult;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.dto.results.ResultDTO#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResultObjectDTO [");
		if (result != null) {
			builder.append("result=");
			builder.append(result);
			builder.append(", ");
		}
		if (getMessages() != null) {
			builder.append("getMessages()=");
			builder.append(getMessages());
		}
		builder.append("]");
		return builder.toString();
	}
}
