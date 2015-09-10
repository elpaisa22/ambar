/**
 * ambar-core-impl [22/08/2011 14:11:16]
 */
package org.ambar.core.validation.factories;

import javax.validation.ValidatorFactory;

import org.ambar.core.validation.impl.ValidatorHibernateImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author Sebastian
 *
 */
public class HibernateValidatorFactory extends LocalValidatorFactoryBean {
	private static final Logger LOG = LoggerFactory.getLogger(HibernateValidatorFactory.class);

    private ValidatorFactory factory;


	/**
	 * @return Retorna el valor del atributo factory.
	 */
	public ValidatorFactory getFactory() {
		return factory;
	}

	/**
	 * @param pFactory Establece el valor del atributo factory.
	 */
	public void setFactory(ValidatorFactory pFactory) {
		this.factory = pFactory;
	}

	/**
	 * Constructor por default.
	 */
	public HibernateValidatorFactory() {
		super();
	}

    /**
	 * Método que crea la instancia del validator basado en <i>Hibernate Validators</i>.
	 * @return {@link org.ambar.core.validation.Validator}
	 */
	public org.ambar.core.validation.Validator validatorFactory() {
        LOG.debug("Ingreso a \"validatorFactory\"");
        org.ambar.core.validation.Validator ambarHibernateValidatorImpl = new ValidatorHibernateImpl();
		((ValidatorHibernateImpl) ambarHibernateValidatorImpl).setValidator(super.getValidator());

		return ambarHibernateValidatorImpl;
	}

}
