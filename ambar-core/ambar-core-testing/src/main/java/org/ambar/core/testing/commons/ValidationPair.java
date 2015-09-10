/**
 * ambar-core-testing [17/09/2011 01:51:03]
 */
package org.ambar.core.testing.commons;

/**
 * <p>
 * Clase que contiene un par ordenado de elementos que contiene una
 * entidad y un valor Boolean que indica si se esperan errores en la
 * validación de esta entidad.
 * </p>
 *
 * @author Sebastian
 *
 * @param <E> Tipo de la entidad.
 */
public class ValidationPair<E> {

	private E entity;
	private Boolean errorExpected;


	/**
	 * Constructor con que recibe los objetos del par por parámetro.
	 *
	 * @param pEntity Establece el valor del atributo entidad.
	 * @param pErrorExpected Establece el valor del atributo errorExpected.
	 */
	public ValidationPair(E pEntity, Boolean pErrorExpected) {
		this.entity = pEntity;
		this.errorExpected = pErrorExpected;
	}

	/**
	 * @return Retorna el valor del atributo entity.
	 */
	public E getEntity() {
		return entity;
	}

	/**
	 * @param pEntity Establece el valor del atributo entity.
	 */
	public void setEntity(E pEntity) {
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
