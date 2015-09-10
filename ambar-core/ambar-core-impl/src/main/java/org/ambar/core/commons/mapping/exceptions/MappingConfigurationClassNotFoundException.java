/**
 * ambar-core-impl [12/10/2011 08:24:52]
 */
package org.ambar.core.commons.mapping.exceptions;

import org.ambar.core.exceptions.SystemException;

/**
 * <p>Excepción que se lanza cuando una clase indicada en la
 * configuración de mapeos no es encontrada.</p>
 *
 * @author Sebastian
 *
 */
public class MappingConfigurationClassNotFoundException extends SystemException {
	private static final long serialVersionUID = -113843340286064532L;

	/**
	 * Constructor por default.
	 */
	public MappingConfigurationClassNotFoundException() {
		super();
	}

	/**
	 * @param pMessage	Mensaje de error
	 */
	public MappingConfigurationClassNotFoundException(String pMessage) {
		super(pMessage);
	}

	/**
	 * @param pMessage	Mensaje de error
	 * @param pCause	Origen de la excepción
	 */
	public MappingConfigurationClassNotFoundException(String pMessage, Throwable pCause) {
		super(pMessage, pCause);
	}
}

