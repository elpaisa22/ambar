/**
 * appl-frontend-web [10 de feb. de 2017 5:37:12 p. m.]
 */
package org.ambar.core.parameters.impl;

import java.util.Properties;

import org.ambar.core.parameters.ParameterHelper;

/**
 * <p>
 * Permite Cargar Parametros de la aplicacion.
 * </p>
 *
 * @author Sebastian
 *
 */
public class ParameterHelperImpl implements ParameterHelper {

	private Properties paramProperties;

	/**
	 * @param pParamProperties Establece el valor del atributo paramProperties.
	 */
	public void setParamProperties(Properties pParamProperties) {
		paramProperties = pParamProperties;
	}

	@Override
	public boolean getBoolean(String pProperty, boolean pDefaultValue) {
		if (paramProperties.containsKey(pProperty)) {
			String value = paramProperties.getProperty(pProperty);
			return "S".equalsIgnoreCase(value);
		} else {
			return pDefaultValue;
		}
	}

	@Override
	public String getDirectory(String pProperty, String pDefaultValue) {
		if (paramProperties.containsKey(pProperty)) {
			String value = paramProperties.getProperty(pProperty);
			return value;
		} else {
			return pDefaultValue;
		}
	}

}
