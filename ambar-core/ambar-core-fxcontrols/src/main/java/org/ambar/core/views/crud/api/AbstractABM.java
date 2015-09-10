/**
 *
 */
package org.ambar.core.views.crud.api;

import org.ambar.core.be.Persistible;
import org.ambar.core.dto.DTO;

/**
 * <p>
 * Interfaz que define las operaciones que debe implementar un ABM.
 * </p>
 *
 * @param <K> Tipo del ID del DTO
 * @param <D> Clase del DTO
 * @param <T> ID de la BE
 * @param <E> Clase de la BE
 *
 * @author Sebastian
 *
 */
public interface AbstractABM<K, D extends DTO<K>, T, E extends Persistible<T>> {

	/**
	 * Abre el ABM en modo consulta.
	 * @param pDTO DTO
	 * */
	void abrirConculta(D pDTO);

	/**
	 * Abre el ABM en modo edicion.
	 * @param pDTO DTO
	 * */
	void abrirEdicion(D pDTO);

	/**
	 * Guarda los cambios realizados.
	 * */
	void guardarCambios();

	/**
	 * Descarta los cambios realizados en el ABM.
	 *
	 * */
	void descartarCambios();

	/**
	 * Elimina el registro (DTO) actual.
	 * */
	void eliminarRegistro();

	/**
	 * Crea un nuevo registro.
	 *
	 * */
	void nuevoRegistro();

	/**
	 * Editar el registro.
	 * */
	void editarRegistro();

	/**
	 * Imprime el registro.
	 * */
	void imprimirRegistro();
}
