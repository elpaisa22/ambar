/**
 * ambar-core-fxcontrols [31/05/2014 19:54:32]
 */
package org.ambar.core.views.main.components.numericfield;

import javafx.scene.control.TextField;

/**
 * <p>Insertar descripción funcional.</p>
 * @author Sebastian
 * @see <<Insertar clases relacionadas>>
 */
public class IntegerField extends TextField {

	@Override
	public void replaceText(int start, int end, String text) {
		if (validate(text)) {
			super.replaceText(start, end, text);
		}
	}

	@Override
	public void replaceSelection(String text) {
		if (validate(text)) {
			super.replaceSelection(text);
		}
	}

	private boolean validate(String text) {
		if (text.matches("[0-9]") || text == "") {
			return true;
		}
		return false;
	}
}
