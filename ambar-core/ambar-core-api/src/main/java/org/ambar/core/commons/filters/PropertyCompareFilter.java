/**
 * ambar-core-api [25/08/2011 21:14:02]
 */
package org.ambar.core.commons.filters;

import java.io.Serializable;

/**
 * <p>
 * Propiedad de la clase que formará parte de las condiciones de filtrado.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.commons.filters.Filter
 */
public abstract class PropertyCompareFilter extends Filter implements Serializable {

	private static final long serialVersionUID = 1011328523881268474L;
	private String propertyName;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public PropertyCompareFilter() {
	}

	/**
	 * @return Retorna el valor del atributo propertyName.
	 */
	public String getPropertyName() {
		return this.propertyName;
	}

	/**
	 * @param pPropertyName Establece el valor del atributo propertyName.
	 */
	public void setPropertyName(String pPropertyName) {
		this.propertyName = pPropertyName;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		if (this.propertyName != null) {
			result = (prime * result) + this.propertyName.hashCode();
		}
		return result;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (!super.equals(pObj)) {
			return false;
		}
		if (!(pObj instanceof PropertyCompareFilter)) {
			return false;
		}
		final PropertyCompareFilter other = (PropertyCompareFilter) pObj;
		if (this.propertyName == null) {
			if (other.propertyName != null) {
				return false;
			}
		} else if (!this.propertyName.equals(other.propertyName)) {
			return false;
		}
		return true;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.filters.Filter#toString()
	 */
	@Override
	public String toString() {
		if (this.propertyName != null) {
			return "PropertyCompareFilter [" + this.propertyName + "]";
		} else {
			return "PropertyCompareFilter [propertyName=]";
		}
	}
}
