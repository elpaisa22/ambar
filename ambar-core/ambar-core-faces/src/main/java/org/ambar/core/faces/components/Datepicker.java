/**
 * ambar-core-faces [19/2/2015 21:25:29]
 */
package org.ambar.core.faces.components;

import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.html.HtmlInputText;

/**
 * <p>
 * Bootstrap datepicker.
 * </p>
 *
 * @author Sebastian
 *
 */
@ResourceDependencies({
	@ResourceDependency(library = "css", name = "bootstrap.min.css"),
	@ResourceDependency(library = "js", name = "bootstrap-datepicker.js")
})
public class Datepicker extends HtmlInputText {
	
	public static final String COMPONENT_TYPE = "org.ambar.component.Datepicker";
	public static final String COMPONENT_FAMILY = "org.ambar.component";
	private static final String DEFAULT_RENDERER = "org.ambar.renderer.DatepickerRenderer";
	
	protected enum PropertyKeys {

		format
		,language;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}
	
	public Datepicker() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}
	
	public java.lang.String getFormat() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.format, "dd/MM/yyyy");
	}
	public void setFormat(java.lang.String _format) {
		getStateHelper().put(PropertyKeys.format, _format);
	}
	
	public java.lang.String getLanguage() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.language, "es");
	}
	public void setLanguage(java.lang.String _language) {
		getStateHelper().put(PropertyKeys.language, _language);
	}
}
