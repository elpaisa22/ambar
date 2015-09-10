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
 * Anotación a utilizar en la definición de los BO que pertime agrupar
 * varias anotaciones {@link Validation}. Ejemplo:
 * <pre>
 * @Validations( level = TransactionConfiguration.ROLLBACK,
 * 			strategy = ValidationStrategy.CONTINUE,  entityValidations = {
 * 	@Validation(methods = { "insert", "update" }, validators = "ovalValidator", profile= {"profile1", "profile2"} ),
 * 	@Validation(methods = { "insert" }, validators = "validator"),
 * 	@Validation(methods = { "insert", "update" }, validators = "otroValidator") }
 *   )
 * </pre>
 * </p>
 *
 * @author Sebastian
 *
 * @see Validation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Validations {
	/**
	 * Arreglo de {@link Validation}.
	 */
	Validation[ ] validations();

	/**
	 * Profiles de cada validator. Ejemplo:
	 * <pre>
	 * @ValidationProfiles( validator = "validator1", profiles = { "org.ambar.prueba.Grupo1" } )
	 * </pre>
	 */
	ValidatorProfiles[ ] profiles() default { };

	/**
	 * Indica si la transaccion debe hacer un commit o rollback cuando la
	 * validación da error.
	 */
	TransactionConfiguration transactionConfiguration() default TransactionConfiguration.ROLLBACK;

	/**
	 * Estrategia de validación. Indica si debe continuar con otras
	 * validaciones cuando se produce un error de validación.
	 */
	ValidationStrategy strategy() default ValidationStrategy.CONTINUE;
}
