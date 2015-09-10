/**
 * ambar-core-api [11/10/2011 01:52:25]
 */
package org.ambar.core.commons.filters.criteria;

/**
 * <p>Insertar descripción funcional.</p>
 *
 * @author Sebastian
 *
 */
public abstract class PropertyFilter {
	private String property;

	/**
	 * Constructor por default.
	 * @param pProperty	Operador de la izquierda de la operación
	 */
	public PropertyFilter(final String pProperty) {
		this.property = pProperty;
	}

	/**
	 * @return Retorna el valor del atributo property.
	 */
	protected String getProperty() {
		return this.property;
	}

	/**
	 * @param pProperty Establece el valor del atributo property.
	 */
	protected void setProperty(String pProperty) {
		this.property = pProperty;
	}
}
