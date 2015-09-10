/**
 * ambar-core-impl [12/10/2011 08:25:42]
 */
package org.ambar.core.commons.mapping.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>Excepción que se lanza cuando el archivo con las configuraciones de mapeo
 * indicado en el contenedor IoC no se puede leer.</p>
 *
 * @author Sebastian
 *
 */
public class MappingConfigurationFileIOException extends SystemException {
	private static final long serialVersionUID = -2262709261763406400L;

	/**
	 * Constructor por default.
	 */
	public MappingConfigurationFileIOException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public MappingConfigurationFileIOException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public MappingConfigurationFileIOException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}

