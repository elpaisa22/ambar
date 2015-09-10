/**
 * ambar-core-api [20/08/2011 00:36:58]
 */
package org.ambar.core.dto.results;

import java.util.List;

import org.ambar.core.dto.DTO;

/**
 * <p>
 * Clase base para los servicios que retornan una lista de objetos
 * que implementen {@link DTO}.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T> Tipo de entidad DTO
 * @see org.ambar.core.dto.results.ResultDTO
 */
public class ResultListDTO <T extends DTO<?>> extends ResultDTO {

	private static final long serialVersionUID = -7945837695845638871L;

	private List<T> resultList;
	private int completeListSize;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public ResultListDTO() {
	}

	/**
	 * <p>
	 * Constructor que recibe la lista de DTO a retornar.
	 * </p>
	 * @param pDto Lista de DTOs
	 */
	public ResultListDTO(List<T> pDto) {
		resultList = pDto;
	}

	/**
	 * <p>
	 * Constructor que recibe la lista de DTO y la lista de mensajes a
	 * retornar.
	 * </p>
	 * @param pDto Lista de DTOs
	 * @param pMessage Lista de mensajes
	 */
	public ResultListDTO(List<T> pDto, List<MessageResult> pMessage) {
		super(pMessage);
		resultList = pDto;
	}

	/**
	 * @return Retorna el valor del atributo resultList.
	 */
	public List<T> getResultList() {
		return this.resultList;
	}

	/**
	 * @param pResultList Establece el valor del atributo resultList.
	 */
	public void setResultList(List<T> pResultList) {
		this.resultList = pResultList;
	}

	/**
	 * @return Retorna el valor del atributo completeListSize.
	 */
	public int getCompleteListSize() {
		return this.completeListSize;
	}

	/**
	 * @param pCompleteListSize Establece el valor del atributo completeListSize.
	 */
	public void setCompleteListSize(int pCompleteListSize) {
		this.completeListSize = pCompleteListSize;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.dto.results.ResultDTO#toString()
	 */
	@Override
	public String toString() {
		final int maxLen = 10;
		StringBuilder builder = new StringBuilder();
		builder.append("ResultListDTO [completeListSize=").append(completeListSize).append(", ");
		if (resultList != null) {
			builder.append("resultList=").append(
					resultList.subList(0, Math.min(resultList.size(), maxLen)));
		}
		builder.append("]");
		return builder.toString();
	}

}
