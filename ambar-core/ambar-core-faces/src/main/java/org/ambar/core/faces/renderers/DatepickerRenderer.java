/**
 * ambar-core-faces [19/2/2015 21:49:59]
 */
package org.ambar.core.faces.renderers;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.ambar.core.faces.components.Datepicker;

import com.sun.faces.renderkit.html_basic.TextRenderer;

/**
 * <p>
 * Renderer del Datepicker.
 * </p>
 *
 * @author Sebastian
 *
 */
public class DatepickerRenderer extends TextRenderer {
	
	
	@Override
	public void encodeBegin(FacesContext pContext, UIComponent pComponent) throws IOException {
		super.encodeBegin(pContext, pComponent);
	}
	
	@Override
	public void encodeEnd(FacesContext pContext, UIComponent pComponent) throws IOException {
		super.encodeEnd(pContext, pComponent);
		
		ResponseWriter writer = pContext.getResponseWriter();
		Datepicker datepicker = (Datepicker) pComponent;
		
		String clientId = datepicker.getClientId(pContext).replaceAll("\\:", "\\\\\\\\:");
		
		writer.startElement("script", null);
		writer.writeAttribute("type", "text/javascript", null);
	
		writer.append("$(\'#" + clientId + "\').datepicker({language : 'es'});");
		
		writer.endElement("script");
	}
}
