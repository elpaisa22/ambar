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
 * Esta anotación permite establecer "profiles" sobre un validator.
 * </p>
 *
 * @author Sebastian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ValidatorProfiles {

	/**
	 * Transacción para la cual se establecen "profiles"
	 * o grupos de validación.
	 */
	String transactionName();
	/**
	 * Nombre del "validator" al cual se le establecen "profiles"
	 * o grupos de validación.
	 */
	String validator();

	/**
	 * Grupos de validación del validator.
	 * @return
	 */
	String[ ] profiles();

}

