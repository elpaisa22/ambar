/**
 * ambar-core-testing [17/09/2011 01:53:09]
 */
package org.ambar.core.testing.commons;

/**
 * <p>
 * Estructura de tupla de elementos para la validacion de Business Objects.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ValidationTouple {

	private String validator;
	private String[] profiles;
	private Object entity;
	private Boolean errorExpected;

	/**
	 * Constructor con que recibe los objetos de la estructura por parámetro.
	 *
	 * @param pValidator Establece el valor del atributo validator.
	 * @param pProfiles Establece el valor del atributo profiles.
	 * @param pEntity Establece el valor del atributo entity.
	 * @param pErrorExpected Establece el valor del atributo errorExpected.
	 */
	public ValidationTouple(String pValidator,
							  String[] pProfiles,
							  Object pEntity,
							  Boolean pErrorExpected) {
		this.validator = pValidator;
		this.profiles = pProfiles;
		this.entity = pEntity;
		this.errorExpected = pErrorExpected;
	}


	/**
	 * @return Retorna el valor del atributo validator.
	 */
	public String getValidator() {
		return validator;
	}
	/**
	 * @param pValidator Establece el valor del atributo validator.
	 */
	public void setValidator(String pValidator) {
		validator = pValidator;
	}
	/**
	 * @return Retorna el valor del atributo profiles.
	 */
	public String[ ] getProfiles() {
		return profiles;
	}
	/**
	 * @param pProfiles Establece el valor del atributo profiles.
	 */
	public void setProfiles(String[ ] pProfiles) {
		profiles = pProfiles;
	}
	/**
	 * @return Retorna el valor del atributo entity.
	 */
	public Object getEntity() {
		return entity;
	}
	/**
	 * @param pEntity Establece el valor del atributo entity.
	 */
	public void setEntity(Object pEntity) {
		entity = pEntity;
	}
	/**
	 * @return Retorna el valor del atributo errorExpected.
	 */
	public Boolean getErrorExpected() {
		return errorExpected;
	}
	/**
	 * @param pErrorExpected Establece el valor del atributo errorExpected.
	 */
	public void setErrorExpected(Boolean pErrorExpected) {
		errorExpected = pErrorExpected;
	}


}
