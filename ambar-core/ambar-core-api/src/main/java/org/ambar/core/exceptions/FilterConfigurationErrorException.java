/**
 * ambar-core-api [11/10/2011 03:07:53]
 */
package org.ambar.core.exceptions;

/**
 * <p>
 * Excepción que se lanza cuando se detectan errores de configuración
 * en los filtros.
 * </p>
 *
 * @author Sebastian
 *
 */
public class FilterConfigurationErrorException extends SystemException {

    private static final long serialVersionUID = -6394948980444906416L;

	/**
     * Constructor por default.
     */
    public FilterConfigurationErrorException() {
    }

    /**
     * @param pCause Orígen de la excepción
     */
    public FilterConfigurationErrorException(final Throwable pCause) {
        super(pCause);
    }

    /**
     * @param pMessage	Mensaje de error
     */
    public FilterConfigurationErrorException(final String pMessage) {
        super(pMessage);
    }

    /**
     * @param pMessage	Mensaje de error
     * @param pCause	Origen de la excepción
     */
    public FilterConfigurationErrorException(final String pMessage, final Throwable pCause) {
        super(pMessage, pCause);
    }
}
