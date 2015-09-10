/**
 * appl-frontend-desktop [24/04/2012 17:48:17]
 */
package org.ambar.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * <p>
 * Insertar descripción funcional.
 * </p>
 *
 * @author Sebastian
 *
 */
public abstract class SpringHelper {

	private static ApplicationContext context = null;

	/**
	 * Retorna el contexto de Spring de la aplicacion.
	 * @return {@link ApplicationContext} contexto
	 * */
	public static ApplicationContext getContext() {
		if (context == null) {
			context = new ClassPathXmlApplicationContext("spring/application-context.xml");
		}
		return context;
	}

}
