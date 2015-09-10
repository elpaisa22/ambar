/**
 * ambar-core-api [19/08/2011 19:46:55]
 */
package org.ambar.core.commons.filters;

import java.io.Serializable;

import org.ambar.core.commons.filters.helpers.FilterElement;

/**
 * <p>
 * Cabecera de la estructura de clases para obtener registros de la base de
 * datos por condiciones de filtrado.
 * </p>
 *
 * @author Sebastian
 *
 */
public abstract class Filter implements Serializable, FilterElement {
	private static final long serialVersionUID = 8248681374559478915L;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public Filter() {
	}
}
