/**
 * ambar-core-impl [12/10/2011 08:26:39]
 */
package org.ambar.core.commons.mapping.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>Excepción que se lanza cuando el archivo con las configuraciones de mapeo
 * indicado en el contenedor IoC no es encontrado.</p>
 *
 * @author Sebastian
 *
 */
public class MappingConfigurationFileNotFoundException extends SystemException {
	private static final long serialVersionUID = 9115898733241726976L;

	/**
	 * Constructor por default.
	 */
	public MappingConfigurationFileNotFoundException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public MappingConfigurationFileNotFoundException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public MappingConfigurationFileNotFoundException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}

