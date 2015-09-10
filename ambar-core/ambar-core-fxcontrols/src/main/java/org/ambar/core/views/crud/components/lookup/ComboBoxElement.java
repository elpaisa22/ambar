/**
 * 
 */
package org.ambar.core.views.crud.components.lookup;


/**
 * @author Ecclesia
 *
 */
public class ComboBoxElement {

	private String visibleName;
	private Object value;

	/**
	 * Constructor default.
	 * */
	public ComboBoxElement(Object pValue, String pVisibleName) {
		this.visibleName = pVisibleName;
		this.value = pValue;
	}
	
	/**
	 * @return the visibleName
	 */
	public String getVisibleName() {
		return visibleName;
	}



	/**
	 * @param pVisibleName the visibleName to set
	 */
	public void setVisibleName(String pVisibleName) {
		visibleName = pVisibleName;
	}



	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}



	/**
	 * @param pValue the value to set
	 */
	public void setValue(Object pValue) {
		value = pValue;
	}



	@Override
	public String toString() {
		return this.visibleName;
	}
	
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (!(pObj instanceof ComboBoxElement)) {
			return false;
		}
		ComboBoxElement other = (ComboBoxElement) pObj;
		if (this.value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!this.value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
		return result;
	}
}
