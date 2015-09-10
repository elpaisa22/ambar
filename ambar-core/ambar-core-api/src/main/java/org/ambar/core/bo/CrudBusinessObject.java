/**
 * ambar-core-api [19/08/2011 17:22:40]
 */
package org.ambar.core.bo;

import java.util.List;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.exceptions.BusinessException;

/**
 * <p>Interfaz para las entidades que poseen operaciones de CRUD.</p>
 *
 * @author Sebastian
 *
 * @param <E> Parámetro genérico que representa a la entidad
 * @param <T> Parámetro genérico que representa el tipo del ID de la entidad
 */
public interface CrudBusinessObject<T, E extends Persistible<T>> extends DataBusinessObject<T, E> {

	/**
	 * <p>Inserta la entidad de negocio.</p>
	 * @param pEntity			Entidad a insertar
	 * @param pWarnings			Mensajes con de tipo "advertencia" devueltos por los validadores
	 * @param pRequestContext	Contexto de la petición
	 * @throws BusinessException Ante reglas de negocio no cumplidas o fallos en validaciones
	 */
	void insert(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException;

	/**
	 * <p>Actualiza la entidad de negocio.</p>
	 * @param pEntity			Entidad a actualizar
	 * @param pWarnings			Mensajes con de tipo "advertencia" devueltos por los validadores
	 * @param pRequestContext	Contexto de la petición
	 * @throws BusinessException Ante reglas de negocio no cumplidas o fallos en validaciones
	 */
	void update(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException;

	/**
	 * <p>Elimina una entidad de negocio.</p>
	 * @param pEntity			Entidad a eliminar
	 * @param pWarnings			Mensajes con de tipo "advertencia" devueltos por los validadores
	 * @param pRequestContext	Contexto de la petición
	 * @throws BusinessException Ante reglas de negocio no cumplidas o fallos en validaciones
	 */
	void remove(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException;

	/**
	 * <p>Efectúa la baja lógica de una entidad de negocio.</p>
	 * @param pEntity			Entidad de negocio
	 * @param pWarnings 		Mensajes con de tipo "advertencia" devueltos por los validadores
	 * @param pRequestContext	Contexto de la petición
	 * @throws BusinessException Ante reglas de negocio no cumplidas o fallos en validaciones
	 */
	void inactivate(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException;

	/**
	 * <p>Efectúa la reactivación de una entidad de negocio.</p>
	 * @param pEntity			Entidad de negocio
	 * @param pWarnings			Mensajes con de tipo "advertencia" devueltos por los validadores
	 * @param pRequestContext	Contexto de la petición
	 * @throws BusinessException Ante reglas de negocio no cumplidas o fallos en validaciones
	 */
	void reactivate(E pEntity, List<String> pWarnings, RequestContext pRequestContext) throws BusinessException;
}
