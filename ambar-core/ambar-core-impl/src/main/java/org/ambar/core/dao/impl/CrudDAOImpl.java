/**
 * ambar-core-impl [21/08/2011 19:25:35]
 */
package org.ambar.core.dao.impl;

import org.ambar.core.be.Persistible;
import org.ambar.core.dao.CrudDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * Implementación de {@link TransactionalDAO} basada en <i>JPA 2</i>.
 * </p>
 *
 * @author Sebastian
 *
 * @param <T> Clave primaria de la entidad
 * @param <E> Entidad de negocio que implemente {@link Persistible}
 */
@Transactional
public abstract class CrudDAOImpl<T, E extends Persistible<T>> extends DataDAOImpl<T, E> implements CrudDAO<T, E> {
	private static final Logger LOG = LoggerFactory.getLogger(CrudDAOImpl.class);

	/*
	 * (non-JSDoc)
	 * @see
	 * org.ambar.core.dao.TransactionalDAO#update(org.ambar.core.be.Persistible)
	 */
	@Override
	public void update(E pEntity) {
		LOG.info("Ingreso a \"update\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		this.getEntityManager().merge(pEntity);
	}

	/*
	 * (non-JSDoc)
	 * @see
	 * org.ambar.core.dao.TransactionalDAO#insert(org.ambar.core.be.Persistible)
	 */
	@Override
	public void insert(E pEntity) {
		LOG.info("Ingreso a \"insert\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		this.getEntityManager().persist(pEntity);
	}

	/*
	 * (non-JSDoc)
	 * @see
	 * org.ambar.core.dao.TransactionalDAO#delete(org.ambar.core.be.Persistible)
	 */
	@Override
	public void remove(E pEntity) {
		LOG.info("Ingreso a \"remove\"");
		LOG.debug("Parámetro pEntity: {}", pEntity);
		final E entityToDelete = super.getById(pEntity.getId());
		this.getEntityManager().remove(entityToDelete);
	}
}
