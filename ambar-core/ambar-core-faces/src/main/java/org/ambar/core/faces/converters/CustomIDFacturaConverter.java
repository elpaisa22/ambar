/**
 * ambar-core-faces [11/6/2015 17:35:11]
 */
package org.ambar.core.faces.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * Custom ID Factura Converter.
 * </p>
 *
 * @author Sebastian
 *
 */
public class CustomIDFacturaConverter implements Serializable, Converter {

	private static final long serialVersionUID = 5836798570901736277L;

	@Override
	public Object getAsObject(FacesContext pContext, UIComponent pComponent, String pValue) {
		if (pValue != null) {
			return Long.parseLong(pValue.replace("-", ""));
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext pContext, UIComponent pComponent, Object pValue) {
		if (pValue != null) {
			String sNumber = pValue.toString();
			sNumber = StringUtils.leftPad(sNumber, 12, "0");
			return sNumber = sNumber.substring(0, 4) + "-" + sNumber.substring(4, 12);
		}
		return "";
	}
}
