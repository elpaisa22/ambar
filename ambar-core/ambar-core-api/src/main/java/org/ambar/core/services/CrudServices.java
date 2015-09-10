/**
 * ambar-core-api [20/08/2011 00:16:37]
 */
package org.ambar.core.services;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultVoidDTO;

/**
 * <p>Interfaz que deben implementar todos los servicios
 * de la capa que expone la funcionalidad del backend con operaciones
 * de actualización de datos.</p>
 *
 * @author Sebastian
 *
 * @param <K> Tipo de dato de la clave primaria del DTO sobre el que opera el servicio
 * @param <D> Tipo de dato del DTO sobre el que opera el servicio
 * @param <T> Tipo de dato de la clave primaria de la BE sobre el que opera el servicio
 * @param <E> Tipo de dato de la BE sobre el que opera el servicio
 */
public interface CrudServices<K, D extends DTO<K>, T, E extends Persistible<T>>
		extends DataServices<K, D, T, E> {

/**
* Transforma el DTO en una entidad de negocio y la inserta.
* @param pEntity DTO a insertar
* @param pRequestContext Contexto de la petición
* @return {@link ResultVoidDTO} Encapsula mensajes con el resultado de la operación
*/
ResultVoidDTO<D> insert(D pEntity, RequestContext pRequestContext);

/**
* Transforma el DTO en una entidad de negocio y la actualiza.
* @param pEntity DTO a actualizar
* @param pRequestContext Contexto de la petición
* @return {@link ResultVoidDTO} Encapsula mensajes con el resultado de la operación
*/
ResultVoidDTO<D> update(D pEntity, RequestContext pRequestContext);

/**
* Transforma el DTO en una entidad de negocio y la elimina.
* @param pEntity DTO a eliminar
* @param pRequestContext Contexto de la petici�n
* @return {@link ResultVoidDTO} Encapsula mensajes con el resultado de la operación
*/
ResultVoidDTO<D> remove(D pEntity, RequestContext pRequestContext);

/**
* Transforma el DTO en una entidad de negocio y efectúa la baja lógica.
* @param pEntity DTO a actualizar
* @param pRequestContext Contexto de la petición
* @return {@link ResultVoidDTO} Encapsula mensajes con el resultado de la operación
*/
ResultVoidDTO<D> inactivate(D pEntity, RequestContext pRequestContext);

/**
* Transforma el DTO en una entidad de negocio y efectúa la reactivación.
* @param pEntity DTO a actualizar
* @param pRequestContext Contexto de la petición
* @return {@link ResultVoidDTO} Encapsula mensajes con el resultado de la operación
*/
ResultVoidDTO<D> activate(D pEntity, RequestContext pRequestContext);
}
