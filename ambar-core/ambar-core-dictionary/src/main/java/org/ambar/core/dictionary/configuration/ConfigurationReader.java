/**
 * ambar-core-dictionary [08/03/2012 22:05:28]
 */
package org.ambar.core.dictionary.configuration;

import org.ambar.core.dictionary.services.DictionaryServices;

/**
 * <p>
 * Clase encargada de leer la configuracion.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface ConfigurationReader {

	/**
	 * Configura el diccionario.
	 *
	 * */
    void configure();

    /**
     * Retorna el diccionario.
     * @return {@link DictionaryServices} Diccionario
     *
     * */
    DictionaryServices getDictionaryServices();
}
