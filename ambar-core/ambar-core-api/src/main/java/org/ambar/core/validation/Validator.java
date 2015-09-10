/**
 * ambar-core-api [20/08/2011 23:21:34]
 */
package org.ambar.core.validation;

import java.util.Locale;

import org.ambar.core.validation.results.ValidationResults;

/**
 * <p>
 * Interfaz a implementar por validadores tanto genéricos como los creados
 * para resolver validaciones puntuales en cada módulo.
 * </p>
 *
 * @author Sebastian
 */
public interface Validator {
	/**
	 * Validación de una entidad de negocio.
	 * @param pEntity Entidad a validar
	 * @param pProfiles Profiles o grupos de validación
	 * @param pLocale Locale para resolver idioma de los mensajes
	 * @return {@link ValidationResults} Objeto que encapsula el resultado
	 *         de la validación y el mensaje de error
	 */
	ValidationResults validate(Object pEntity, String[] pProfiles, Locale pLocale);
}
