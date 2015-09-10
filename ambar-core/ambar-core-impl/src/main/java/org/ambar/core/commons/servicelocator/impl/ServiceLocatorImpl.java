/**
 * ambar-core-impl [21/08/2011 19:21:07]
 */
package org.ambar.core.commons.servicelocator.impl;

import java.io.Serializable;

import org.ambar.core.commons.servicelocator.ServiceLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <p>Implementación basada en Spring de {@link ServiceLocator}.</p>
 *
 * @author Sebastian
 *
 */
public class ServiceLocatorImpl implements ApplicationContextAware, ServiceLocator, Serializable {
	private static final long serialVersionUID = 6995852948438360116L;

	private ApplicationContext applicationContext;

	/* (non-JSDoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(
	 * 		org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext pApplicationContext) {
		this.applicationContext = pApplicationContext;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.servicelocator.ServiceLocator#getService(java.lang.String)
	 */
	@Override
	public Object getService(String pServiceName) {
		return applicationContext.getBean(pServiceName);
	}
}
