/**
 * ambar-core-api [20/08/2011 00:30:06]
 */
package org.ambar.core.dto.results;

/**
 * <p>
 * Enumeracion de los tipos de mensaje a retornar.
 * </p>
 *
 * @author Sebastian
 *
 */
public enum MessageKind {
	/*
	 * Mensaje de error Fatal
	 */
	Fatal,
	/*
	 * Mensaje de Error
	 */
	Error,
	/*
	 * Mensaje de Informacion
	 */
	Info,
	/*
	 * Mensaje de Alerta
	 */
	Warning,
	/*
	 * Mensaje de Debug
	 */
	Debug,
	/*
	 * Mensaje para otro tipo de error
	 */
	Other,
}
