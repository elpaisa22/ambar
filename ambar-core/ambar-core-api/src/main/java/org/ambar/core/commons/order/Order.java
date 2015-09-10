/**
 * ambar-core-api [20/08/2011 23:37:38]
 */
package org.ambar.core.commons.order;

import java.io.Serializable;

/**
 * <p>
 * Clase para los parámetros de ordenamiento.
 * </p>
 *
 * @author Sebastian
 */
public class Order implements Serializable {
	private static final long serialVersionUID = -8362790998983744069L;
	private String propertyName;
	private OrderType orderType;

	/**
	 * Constructor con parámetro tipo de ordenamiento.
     * @param pPropertyName Propiedad a ordenar
	 * @param pOrderType Tipo de ordenamiento
	 */
	public Order(final String pPropertyName, final String pOrderType) {
        this.setPropertyName(pPropertyName);
		this.setOrderType(OrderType.fromString(pOrderType));
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

	/**
	 * @return Retorna el valor del atributo orderType.
	 */
	public OrderType getOrderType() {
		return this.orderType;
	}

	/**
	 * @param pOrderType Establece el valor del atributo orderType.
	 */
	public void setOrderType(OrderType pOrderType) {
		this.orderType = pOrderType;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.propertyName != null) {
			result = (prime * result) + this.propertyName.hashCode();
		}
		if (this.orderType != null) {
			result = (prime * result) + this.orderType.hashCode();
		}
		return result;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (!(pObj instanceof Order)) {
			return false;
		}
		final Order other = (Order) pObj;
		if (this.orderType == null) {
			if (other.orderType != null) {
				return false;
			}
		} else if (!this.orderType.equals(other.orderType)) {
			return false;
		}
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Order [");
		if (this.propertyName != null) {
			builder.append("property=");
			builder.append(this.propertyName);
			builder.append(", ");
		}
		if (this.orderType != null) {
			builder.append("orderType=");
			builder.append(this.orderType);
		}
		builder.append("]");
		return builder.toString();
	}
}
