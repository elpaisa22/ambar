/**
 * ambar-core-impl [21/08/2011 17:07:00]
 */
package org.ambar.core.bo.impl;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.bo.DataBusinessObject;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.dao.DataDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Implementación por default de {@link DataBusinessObject}.</p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad
 * @param <E> Entidad de negocio que implemente {@link Persistible}
 */
@Transactional(readOnly = true)
public abstract class DataBusinessObjectImpl<T, E extends Persistible<T>> implements DataBusinessObject<T, E> {
	private static final Logger LOG = LoggerFactory.getLogger(DataBusinessObjectImpl.class);

	private DataDAO<T, E> dao;

	/**
	 * @return Retorna el valor del atributo dao.
	 */
	protected DataDAO<T, E> getDao() {
		return this.dao;
	}

	/**
	 * @param pDao Establece el valor del atributo dao.
	 */
	public void setDao(DataDAO<T, E> pDao) {
		this.dao = pDao;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.bo.DataBO#getById(java.lang.Object)
	 */
	@Override
	public E getById(T pId) {
		LOG.info("Ingreso a \"getById\"");
		LOG.debug("Parámetro pId: {}", pId);

		E bussinessEntity = (E) dao.getById(pId);
		LOG.debug("Valor de retorno: {}", bussinessEntity);

		return bussinessEntity;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.bo.DataBO#getFilteredList(
	 * 		org.ambar.core.commons.filters.Filter, org.ambar.core.commons.paging.Pager)
	 */
	@Override
	public FilteredList<E> getFilteredList(QueryPredicate pQueryPredicate) {
		LOG.info("Ingreso a \"getById\"");
		LOG.debug("Parámetro pQueryPredicate: {}", pQueryPredicate);

		FilteredList<E> resultList = this.dao.getFilteredList(pQueryPredicate);
		LOG.debug("Valor de retorno: {}", resultList);

		return resultList;
	}
}
