/**
 * ambar-core-faces [7/4/2015 19:16:18]
 */
package org.ambar.core.faces.renderers;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.Attribute;
import com.sun.faces.renderkit.AttributeManager;
import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.GroupRenderer;

/**
 * <p>
 * Renderer del Paginator.
 * </p>
 *
 * @author Sebastian
 *
 */
public class PaginatorRenderer extends GroupRenderer {
	
	private static final Attribute[] ATTRIBUTES =
	          AttributeManager.getAttributes(AttributeManager.Key.PANELGROUP);
	
	@Override
	public void encodeBegin(FacesContext pContext, UIComponent pComponent) throws IOException {
		ResponseWriter writer = pContext.getResponseWriter();

		writer.startElement("nav", pComponent);
		writer.startElement("ul", pComponent);
		writer.writeAttribute("class", "pagination", null);

		encodeParent(pContext, pComponent);
	}

	private void encodeParent(FacesContext pContext, UIComponent pComponent) throws IOException {
		rendererParamsNotNull(pContext, pComponent);

        if (!shouldEncode(pComponent)) {
            return;
        }

        ResponseWriter writer = pContext.getResponseWriter();

        RenderKitUtils.renderPassThruAttributes(pContext,
                                                writer,
                                                pComponent,
                                                ATTRIBUTES);
	}

	@Override
	public void encodeEnd(FacesContext pContext, UIComponent pComponent) throws IOException {
		ResponseWriter writer = pContext.getResponseWriter();
		writer.endElement("ul");
		writer.endElement("nav");

		super.encodeEnd(pContext, pComponent);
	}

	@Override
	public void encodeChildren(FacesContext pContext, UIComponent pComponent) throws IOException {
		ResponseWriter writer = pContext.getResponseWriter();
		
        rendererParamsNotNull(pContext, pComponent);

        if (!shouldEncodeChildren(pComponent)) {
            return;
        }

        // Render our children recursively
        Iterator<UIComponent> kids = getChildren(pComponent);
        while (kids.hasNext()) {
        	UIComponent kid = kids.next();
        	writer.startElement("li", pComponent);
        	if (kid instanceof HtmlCommandLink) {
        		HtmlCommandLink command = (HtmlCommandLink) kid;
        		if (command.getStyleClass() != null && command.getStyleClass().contains("active-page")) {
        			writer.writeAttribute("class", "active", null);
        		} else if (command.getStyleClass() != null && command.getStyleClass().contains("disabled-page")) {
        			writer.writeAttribute("class", "disabled", null);
        		}
        	}
            encodeRecursive(pContext, kid);
            writer.endElement("li");
        }
		
	}

}
