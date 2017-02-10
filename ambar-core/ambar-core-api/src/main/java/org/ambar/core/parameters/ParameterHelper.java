/**
 * ambar-core-api [10 de feb. de 2017 5:42:24 p. m.]
 */
package org.ambar.core.parameters;

/**
 * <p>
 * Interfaz que define las operaciones a implementar
 * por el administrador de parametros de la aplicacion.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface ParameterHelper {
	
	/**
	 * Verifica si existe la propiedad pProperty y la retorna como boolean.
	 * @return {@link Boolean} Resultado
	 * */
	public boolean getBoolean(String pProperty, boolean pDefaultValue);
}
