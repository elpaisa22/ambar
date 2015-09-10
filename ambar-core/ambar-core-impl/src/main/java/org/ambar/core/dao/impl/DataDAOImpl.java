/**
 * ambar-core-impl [21/08/2011 19:22:53]
 */
package org.ambar.core.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ambar.core.be.FilteredList;
import org.ambar.core.be.Persistible;
import org.ambar.core.commons.filters.QueryPredicate;
import org.ambar.core.dao.DataDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Implementación de {@link DataDAO} basada en <i>JPA 2</i>.</p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad
 * @param <E> Entidad de negocio que implemente {@link Persistible}
 */
@Transactional(readOnly = true)
public abstract class DataDAOImpl<T, E extends Persistible<T>> implements DataDAO<T, E> {
	private static final Logger LOG = LoggerFactory.getLogger(DataDAOImpl.class);

	private Class<?> entityClass;
	private String queryHeader;
	private String queryCountHeader;

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Constructor por default.
	 */
	public DataDAOImpl() {
		super();

		Type entityRawType = ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[1];
		this.entityClass = (Class<?>) entityRawType;
		this.modifyQueryHeader();
		this.modifyQueryCountHeader();
	}

	/**
	 * @return Retorna el valor del atributo entityManager.
	 */
	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	/**
     * @return Retorna el valor del atributo queryHeader.
     */
    protected String getQueryHeader() {
        return this.queryHeader;
    }

    /**
     * @param pQueryHeader Establece el valor del atributo queryHeader.
     */
    protected void setQueryHeader(final String pQueryHeader) {
        this.queryHeader = pQueryHeader;
    }

	/**
     * Modifica el QueryHeader.
     * */
    protected void modifyQueryHeader() {
        String qry = "SELECT e FROM " + this.entityClass.getSimpleName() + " e";
        this.setQueryHeader(qry);
    }

    /**
     * @return Retorna el valor del atributo queryHeader.
     */
    protected String getQueryCountHeader() {
        return this.queryCountHeader;
    }

    /**
	 * @param pQueryCountHeader Establece el valor del atributo queryCountHeader.
	 */
	public void setQueryCountHeader(String pQueryCountHeader) {
		queryCountHeader = pQueryCountHeader;
	}


	/**
	 * Metodo que retorna la queryCountHeaderPropertyName.
	 * @return Retorna la propiedad
	 * */
    protected String getQueryCountHeaderPropertyName() {
    	return null;
    }

    /**
     * Metodo que modifica el queryCountHeader.
     * */
    protected void modifyQueryCountHeader() {
    	String queryCountHeaderPropertyName = getQueryCountHeaderPropertyName();
    	String queryCountExpression =
    			(queryCountHeaderPropertyName == null) ? "e" : "e." + queryCountHeaderPropertyName;
        String qry = "SELECT Count(" + queryCountExpression + ") FROM " + this.entityClass.getSimpleName() + " e";
        this.setQueryCountHeader(qry);
    }

	/* (non-JSDoc)
	 * @see org.ambar.core.dao.DataDAO#getFilteredList(
	 * 		org.ambar.core.commons.filters.Filter,
	 * 		org.ambar.core.commons.paging.Pager)
	 */
	@Override
	public FilteredList<E> getFilteredList(QueryPredicate pQueryPredicate) {
        LOG.info("Ingreso a \"getFilteredList\"");
        LOG.debug("Parámetro pQueryPredicate: {}", pQueryPredicate);

        pQueryPredicate.appendToWhere(this.getCustomWhereClause());

        StringBuilder qry = new StringBuilder(this.getQueryHeader());
        qry.append(" ");
        qry.append(pQueryPredicate.getQueryCondition());

        Boolean useQueryCountHeader = false;

        Query query = this.entityManager.createQuery(qry.toString());

        if ((pQueryPredicate.getPageNumber() != null) && (pQueryPredicate.getPageSize() != null)) {
            query.setFirstResult(
                    (pQueryPredicate.getPageNumber().intValue() - 1) * pQueryPredicate.getPageSize().intValue());
            query.setMaxResults(pQueryPredicate.getPageSize().intValue());
            useQueryCountHeader = true;
        }
        if (pQueryPredicate.getParams() != null) {
            for (Map.Entry<String, Object> param : pQueryPredicate.getParams().entrySet()) {
            	String key = param.getKey();
                query.setParameter(key, param.getValue());
            }
        }

        query.setHint("org.hibernate.cacheable", true);

		FilteredList<E> list = new FilteredList<E>();

        @SuppressWarnings("unchecked")
        List<E> entities = query.getResultList();

		list.setFilteredList(entities);

		// Si debe generarse el Query de Count se utiliza el getCountHeader
		if (useQueryCountHeader) {
            StringBuilder qryCount =
            	new StringBuilder(this.getQueryCountHeader());
			qryCount.append(" ");
			qryCount.append(pQueryPredicate.getQueryCondition());

            Query queryCount = this.entityManager.createQuery(qryCount.toString());

            if (pQueryPredicate.getParams() != null) {
	            for (Map.Entry<String, Object> param : pQueryPredicate.getParams().entrySet()) {
	            	queryCount.setParameter(param.getKey(), param.getValue());
	            }
	        }

            queryCount.setHint("org.hibernate.cacheable", true);
			list.setRowCount(((Long) queryCount.getSingleResult()).intValue());
		} else {
			list.setRowCount(list.getFilteredList().size());
		}

		return list;
	}


	/**
	 * Metodo que puede ser reemplazado en las subclases que posibilita agregar condiciones al filtro de busqueda.
	 * @return {@link String} Condicion
	 * */
	protected String getCustomWhereClause() {
		return null;
	}

	/**
	 * Método que determina el tipo de dato del parámetro genérico
	 * correspondiente al DTO.
	 *
	 * @return {@link Class}
	 */
	@SuppressWarnings("rawtypes")
	private Class getIDClass() {
		Type entityRawType =
				((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		return (Class) entityRawType;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.dao.DataDAO#getById(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E getById(T pId) {
		LOG.info("Ingreso a \"getById\"");
		LOG.debug("Parámetro pId: {}", pId);

		E entity;
		//Si el tipo del ID es Long, entonces realiza un casteo forzado a Long
		if (getIDClass().getName() == "java.lang.Long") {
			Long longId = ((Number) pId).longValue();
			entity = (E) this.entityManager.find(entityClass, longId);
		} else {
			entity = (E) this.entityManager.find(entityClass, pId);
		}

		LOG.debug("Valor de retorno: {}", entity);

		return entity;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.dao.DataDAO#getAll()
	 */
	@Override
	public List<E> getAll() {
		LOG.info("Ingreso a \"getAll\"");
		Query qry = this.entityManager.createQuery("SELECT e FROM " + this.entityClass.getSimpleName() + " e");

		@SuppressWarnings("unchecked")
		List<E> entities = qry.getResultList();
		LOG.debug("Total entidades retornadas: {}", entities.size());

		return entities;
	}
}

