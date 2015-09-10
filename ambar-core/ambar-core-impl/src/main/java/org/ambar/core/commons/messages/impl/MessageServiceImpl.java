/**
 * ambar-core-impl [21/08/2011 19:18:13]
 */
package org.ambar.core.commons.messages.impl;

import java.util.Locale;

import org.ambar.core.commons.messages.MessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * <p>
 * Implementación por default de {@link MessageService} basada en Spring.
 * </p>
 *
 * @author Sebastian
 *
 */
public class MessageServiceImpl implements MessageService, MessageSourceAware {
	private MessageSource messageSource;

	/* (non-JSDoc)
	 * @see org.ambar.core.messages.MessageService#getMessage(
	 * 		java.lang.String, java.lang.Object[], java.util.Locale)
	 */
	@Override
	public String getMessage(String pCode, Object[ ] pArgs, Locale pLocale) {
		return messageSource.getMessage(pCode, pArgs, pLocale);
	}

	/* (non-JSDoc)
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(
	 * 		org.springframework.context.MessageSource)
	 */
	@Override
	public void setMessageSource(MessageSource pMessageSource) {
		this.messageSource = pMessageSource;
	}
}
