/**
 * ambar-core-api [20/08/2011 23:26:36]
 */
package org.ambar.core.validation.configuration;

/**
 * <p>
 * Estrategias de ejecución de validaciones.
 * </p>
 *
 * @author Sebastian
 *
 */
public enum ValidationStrategy {
	/*
	 * Detenerse ante el primer validador que da resultado negativo.
	 */
	STOP,
	/*
	 * Ejecutar todos los validadores configurados independientemente
	 * de su resultado.
	 */
	CONTINUE;
}

