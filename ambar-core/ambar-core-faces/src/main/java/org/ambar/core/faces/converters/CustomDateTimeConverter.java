/**
 * ambar-core-faces [17/09/2012 17:52:46]
 */
package org.ambar.core.faces.converters;

import java.io.Serializable;

import javax.faces.convert.DateTimeConverter;

/**
 * <p>
 * Custom Date Converter.
 * </p>
 * @author Sebastian
 *
 */
public class CustomDateTimeConverter extends DateTimeConverter implements Serializable {

	private static final long serialVersionUID = -3209908594657894420L;

	/**
	 * Default constructor.
	 * */
	public CustomDateTimeConverter() {
		super();
		setPattern("dd/MM/yyyy");
	}

}
