/**
 * ambar-core-api [19/08/2011 19:53:17]
 */
package org.ambar.core.commons.filters;

import java.util.ArrayList;
import java.util.List;

import org.ambar.core.commons.order.Order;



/**
 * <p>
 * Clase para los parámetros de paginación.
 * </p>
 *
 * @author Sebastian
 *
 */
public class Pager {

	private Integer page;
	private Integer pageSize;
	private List<Order> orderList;

	/**
	 * <p>
	 * Constructor por default.
	 * </p>
	 */
	public Pager() {
		this.orderList = new ArrayList<Order>();
	}

	/**
	 * @return Retorna el valor del atributo page.
	 */
	public Integer getPage() {
		return this.page;
	}

	/**
	 * @param pPage Establece el valor del atributo page.
	 */
	public void setPage(Integer pPage) {
		this.page = pPage;
	}

	/**
	 * @return Retorna el valor del atributo pageSize.
	 */
	public Integer getPageSize() {
		return this.pageSize;
	}

	/**
	 * @param pPageSize Establece el valor del atributo pageSize.
	 */
	public void setPageSize(Integer pPageSize) {
		this.pageSize = pPageSize;
	}

	/**
	 * @return Retorna el valor del atributo orderList.
	 */
	public List<Order> getOrderList() {
		return this.orderList;
	}

	/**
	 * @param pOrderList Establece el valor del atributo orderList.
	 */
	public void setOrderList(List<Order> pOrderList) {
		this.orderList = pOrderList;
	}

	/**
	 * @param pOrder orden a agregar a la lista
	 */
	public void addOrder(final Order pOrder) {
		if (this.orderList == null) {
			this.orderList = new ArrayList<Order>();
		}
		this.orderList.add(pOrder);
	}
}
