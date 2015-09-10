/**
 * ambar-core-api [20/08/2011 00:10:59]
 */
package org.ambar.core.commons.messages;

import java.util.Locale;

/**
 * <p>Interfaz a implementar por los servicios que resuelvan
 * la obtención de mensajes "localizados".</p>
 *
 * @author Sebastian
 *
 */
public interface MessageService {

	/**
	 * Método que retorna el mensaje correspondiente al código y {@link Locale}
	 * recibidos por parámetro.
	 * @param pCode		Código del mensaje
	 * @param pArgs		Argumentos adicionales
	 * @param pLocale	Información de "localización"
	 * @return {@link String}
	 */
	String getMessage(String pCode, Object[] pArgs, Locale pLocale);
}
