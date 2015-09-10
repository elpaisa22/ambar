/**
 * ambar-core-api [20/08/2011 23:27:32]
 */
package org.ambar.core.validation.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * Anotación a utilizar en la definición de los BO para indicar cuales
 * validaciones se deben ejecutar antes de invocarse los métodos definidos.
 * </p>
 *
 * @author Sebastian
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Validation {
	/**
	 * Nombre de los métodos que se validan. Ejemplo: { "insert", "update"}.
	 */
	String[ ] transactionNames();

	/**
	 * Nombre del bean definido en el contexto de spring que se invoca para
	 * realizar la validación.
	 */
	String[ ] validators();
}
