/**
 * ambar-core-api [20/08/2011 23:23:34]
 */
package org.ambar.core.validation.results;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que se retorna como resultado de una validación. Encapsula la
 * lista de mensajes de Errores.
 *
 * @author Sebastian
 *
 */
public class ValidationResults {
	private List<String> errorMessages;
	private List<String> warningMessages;

	/**
	 * Constructor por default.
	 */
	public ValidationResults() {
		super();
	}

	/**
	 * @param pWarningMessage Agrega un mensaje a lista de "warnings".
	 */
	public void addWarningMessage(String pWarningMessage) {
		if (this.warningMessages == null) {
			this.warningMessages = new ArrayList<String>();
		}

		errorMessages.add(pWarningMessage);
	}

	/**
	 * @param pWarningMessages Agrega los mensajes recibidos a la lista de "warnings".
	 */
	public void addWarningMessages(List<String> pWarningMessages) {
		if (this.warningMessages == null) {
			this.warningMessages = new ArrayList<String>();
		}

		this.warningMessages.addAll(pWarningMessages);
	}

	/**
	 * @param pErrorMessage Agrega un mensaje a lista de errores.
	 */
	public void addErrorMessage(String pErrorMessage) {
		if (this.errorMessages == null) {
			this.errorMessages = new ArrayList<String>();
		}

		this.errorMessages.add(pErrorMessage);
	}

	/**
	 * @param pErrorMessages Agrega los mensajes recibidos a la lista de errores.
	 */
	public void addErrorMessages(List<String> pErrorMessages) {
		if (this.errorMessages == null) {
			this.errorMessages = new ArrayList<String>();
		}

		this.errorMessages.addAll(pErrorMessages);
	}

	/**
	 * @return Retorna <code>true</code> cuando la lista de mensajes
	 * de error tiene contenido y <code>false</code> en el caso contrario.
	 */
	public boolean hasErrors() {
		return ((this.errorMessages != null) && (!this.errorMessages.isEmpty()));
	}

	/**
	 * @return Retorna <code>true</code> cuando la lista de "warnings"
	 * (o advertencias) tiene contenido y <code>false</code> en el caso contrario.
	 */
	public boolean hasWarnings() {
		return ((this.warningMessages != null) && (!this.warningMessages.isEmpty()));
	}

	/**
	 * @return Retorna el valor del atributo warningMessages.
	 */
	public List<String> getWarningMessages() {
		return this.warningMessages;
	}

	/**
	 * @return Retorna el valor del atributo errorMessages.
	 */
	public List<String> getErrorMessages() {
		return this.errorMessages;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationResults [errorMessages=");
		builder.append(this.errorMessages);
		builder.append(", warningMessages=");
		builder.append(this.warningMessages);
		builder.append("]");
		return builder.toString();
	}
}
