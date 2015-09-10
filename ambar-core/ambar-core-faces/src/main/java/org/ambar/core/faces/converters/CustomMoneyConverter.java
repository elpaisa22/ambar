/**
 * ambar-core-faces [05/03/2013 20:10:10]
 */
package org.ambar.core.faces.converters;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * <p>
 * Custom Money Converter.
 * </p>
 *
 * @author Sebastian
 *
 */
public class CustomMoneyConverter implements Serializable, Converter {

	private static final long serialVersionUID = 2379283052214559963L;

	private String format;
	private DecimalFormatSymbols symbols;
	private NumberFormat formatter;

	/**
	 * Constructo default con parametro.
	 * @param pFormat Formato del Converter.
	 * */
	public CustomMoneyConverter(String pFormat) {
		this.format = pFormat;

		//Crea el formato del decimal converter
		this.symbols = new DecimalFormatSymbols(Locale.getDefault());

		if (this.format == null || "".equals(this.format)) {
			this.formatter = new DecimalFormat();
		} else {
			this.formatter = new DecimalFormat(this.format, this.symbols);
		}
	}

	/**
	 * Constructo default sin parametro.
	 *
	 * */
	public CustomMoneyConverter() {
		//Crea el formato del decimal converter
		this.formatter = new DecimalFormat();

	}

	@Override
	public Object getAsObject(FacesContext pFacesContext,
			UIComponent pUiComponent, String pString) {
		try {
			if (pString == null || pString.equals("")) {
				return null;
			}
			return new Double(pString);

		} catch (Exception e) {
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setDetail("El valor no es un número válido");
			facesMessage.setSummary("El valor no es un número válido");
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(facesMessage);
		}
	}

	@Override
	public String getAsString(FacesContext pFacesContext,
			UIComponent pUiComponent, Object pObject) {
		try {
			if (pObject != null && !"".equals(pObject.toString())) {
				Double number = Double.valueOf(pObject.toString());
				return formatter.format(number);
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}
}
