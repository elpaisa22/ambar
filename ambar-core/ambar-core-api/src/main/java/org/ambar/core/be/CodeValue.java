/**
 * ambar-core-api [11/03/2014 18:00:12]
 */
package org.ambar.core.be;

/**
 * <p>
 * Clase que representa un valor de un código.
 * </p>
 *
 * @author Sebastian
 */
public class CodeValue {

	private String codeType;
	private String value;
	private String description;

	/**
	 * Constructor por default.
	 * @param pCodeType Tipo del Código
	 * @param pCodeValue Valor del Código
	 * @param pDescripcion Descripción del Valor
	 * */
	public CodeValue(String pCodeType, String pCodeValue, String pDescripcion) {
		this.codeType = pCodeType;
		this.value = pCodeValue;
		this.description = pDescripcion;
	}
	/**
	 * @return Retorna el valor del atributo codeType.
	 */
	public String getCodeType() {
		return codeType;
	}
	/**
	 * @param pCodeType Establece el valor del atributo codeType.
	 */
	public void setCodeType(String pCodeType) {
		codeType = pCodeType;
	}
	/**
	 * @return Retorna el valor del atributo value.
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param pValue Establece el valor del atributo value.
	 */
	public void setValue(String pValue) {
		value = pValue;
	}
	/**
	 * @return Retorna el valor del atributo description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param pDescription Establece el valor del atributo description.
	 */
	public void setDescription(String pDescription) {
		description = pDescription;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CodeValue [");
		builder.append("codeType=").append(codeType);
		builder.append("value=").append(value);
		builder.append("description=").append(description);
		builder.append("]");
		return builder.toString();
	}
}
