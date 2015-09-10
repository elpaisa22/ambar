/**
 * ambar-core-api [19/08/2011 19:20:56]
 */
package org.ambar.core.commons.context;

import java.io.Serializable;
import java.util.Locale;

/**
 *
 * <p>Esta clase tiene como objetivo encapsular todos aquellos datos
 * que hagan al contexto de una petición y no formen parte de la definición
 * funcional de los servicios involucrados.
 *
 * Por ejemplo en una petición donde se desea actualizar una entidad X,
 * se debe registrar el usuario que realiza la petición en los campos de
 * tracking pero ese dato, el usuario, no forma parte de la entidad a
 * actualizar ni existe en variables de entorno de ningún tipo dado
 * que los servicios no tienen estado.
 *
 * La clase encapsula los siguientes atributos:
 * <ol>
 * 	<li>Usuario</li>
 * 	<li>Locale del cliente que realiza la petición</li>
 * 	<li>Idioma para el contenido de la documentación multiidioma</li>
 * </ol>
 * </p>
 *
 * @author Sebastian
 *
 */
public class RequestContext implements Serializable {

	private static final long serialVersionUID = -5660411301513440457L;

	private String user;
	private String locale;
	private String documentationLanguage;

	/**
	 * Constructor por default.
	 */
	public RequestContext() {
	}

	/**
	 * Constructor que recibe los objetos a encpasular por parámetros.
	 * @param pUser Usuario
	 * @param pLocale Locale del cliente
	 * @param pDocumentationLanguage Idioma de la documentación
	 */
	public RequestContext(String pUser, Locale pLocale, String pDocumentationLanguage) {
		this.user = pUser;
		this.locale = pLocale.toString();
		this.documentationLanguage = pDocumentationLanguage;
	}

	/**
	 * @return Devuelve el atributo user.
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param pUser Fija el valor de pUser al campo user
	 */
	public void setUser(String pUser) {
		user = pUser;
	}

	/**
	 * @return Devuelve el atributo locale como un {@link Locale}.
	 */
	public Locale getLocaleData() {
		int medio = locale.indexOf("_");
		String language = locale.substring(0, medio);
		String country = locale.substring(medio + 1);
		return new Locale(language, country);
	}

	/**
	 * @return Devuelve el atributo locale.
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param pLocale Fija el valor de pLocale al campo locale
	 */
	public void setLocale(String pLocale) {
		locale = pLocale;
	}

	/**
	 * @return Devuelve el atributo documentationLanguage.
	 */
	public String getDocumentationLanguage() {
		return documentationLanguage;
	}

	/**
	 * @param pDocumentationLanguage Fija el valor de pDocumentationLanguage
	 * al campo documentationLanguage.
	 */
	public void setDocumentationLanguage(String pDocumentationLanguage) {
		documentationLanguage = pDocumentationLanguage;
	}

	/**
	 * Reimplementación del método hashCode.
	 * @return int
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (documentationLanguage != null) {
			result = prime * result + documentationLanguage.hashCode();
		}
		if (locale != null) {
			result = prime * result + locale.hashCode();
		}
		if (user != null) {
			result = prime * result + user.hashCode();
		}
		return result;
	}

	/**
	 * Reimplementación del método <i>equals</i>.
	 * @param pObj Objeto a comparar
	 * @return boolean
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (getClass() != pObj.getClass()) {
			return false;
		}
		RequestContext other = (RequestContext) pObj;
		if (documentationLanguage == null) {
			if (other.documentationLanguage != null) {
				return false;
			}
		} else if (!documentationLanguage.equals(other.documentationLanguage)) {
			return false;
		}
		if (locale == null) {
			if (other.locale != null) {
				return false;
			}
		} else if (!locale.equals(other.locale)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	/**
	 * Reimplementación del método <i>toString</i>.
	 * @return {@link String}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RequestContext [documentationLanguage="
				+ documentationLanguage + ", locale=" + locale + ", user=" + user + "]";
	}
}
