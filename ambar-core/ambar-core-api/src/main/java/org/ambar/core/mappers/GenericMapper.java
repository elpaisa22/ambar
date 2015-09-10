/**
 * ambar-core-api [19/08/2011 20:21:10]
 */
package org.ambar.core.mappers;

/**
 * Interfaz que sirve de base a la funcionalidad de mapear
 * entidades. Será utilizada en la conversión de DTOs a entidades
 * de negocio y viceversa.
 *
 * @author Sebastian
 *
 * @param <A> Primer tipo de dato del mapeo denominada genéricamente A en el servicio.
 * @param <B> Primer tipo de dato del mapeo denominada genéricamente B en el servicio.
 */
public interface GenericMapper<A, B> {
	/**
	 * Genera un nuevo objeto del tipo <i>A</i> a partir del parámetro recibido.
	 * @param pObject Objeto a mapear
	 * @return Objeto mapeado
	 */
	A mapFromB(B pObject);

	/**
	 * Mapea el objeto del tipo <i>A</i> con el contenido del objeto de tipo <i>B</i>.
	 * @param pSourceObject Objeto origen
	 * @param pDestinationObject Objeto destino
	 */
	void mapFromB(B pSourceObject, A pDestinationObject);

	/**
	 * Genera un nuevo objeto del tipo <i>B</i> a partir del parámetro recibido.
	 * @param pObject Objeto a mapear
	 * @return Objeto mapeado
	 */
	B mapFromA(A pObject);

	/**
	 * Mapea el objeto del tipo <i>B</i> con el contenido del objeto de tipo <i>A</i>.
	 * @param pSourceObject Objeto origen
	 * @param pDestinationObject Objeto destino
	 */
	void mapFromA(A pSourceObject, B pDestinationObject);
}
