/**
 * ambar-core-jsfcomponents [07/05/2012 21:43:16]
 */
package org.ambar.core.faces.components;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 * <p>
 * Componente de Navigation Bar.
 * </p>
 *
 * @author Sebastian
 *
 */
@ResourceDependencies({
	@ResourceDependency(library = "css", name = "bootstrap.css"),
	@ResourceDependency(library = "js", name = "bootstrap.js") })
public class NavigationBar extends UIComponentBase  {

	@Override
	public void encodeBegin(FacesContext pContext) throws IOException {
		ResponseWriter writer = pContext.getResponseWriter();
		   String hellomsg = (String) getAttributes().get("hellomsg");

		   writer.startElement("h3", this);
		   if (hellomsg != null) {
			   writer.writeText(hellomsg, "hellomsg");
		   } else {
		     writer.writeText("Hello from a custom JSF UI Component!", null);
		   }
		   writer.endElement("h3");
		   writer.startElement("p", this);
		   writer.writeText(" Today is: " + new Date(), null);
		   writer.endElement("p");

	}

	@Override
	public String getFamily() {
		return null;
	}


}
