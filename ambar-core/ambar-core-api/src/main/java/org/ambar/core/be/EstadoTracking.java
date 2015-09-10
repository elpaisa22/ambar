/**
 * ambar-core-api [10/08/2011 22:53:57]
 */
package org.ambar.core.be;

/**
 * <p>Enumeración con todos los tipos de datos definidos para
 *  los estados de los objetos que implementan {@link Trackingable}.
 * </p>
 *
 * @author Sebastian
 *
 */
public enum EstadoTracking {
	Baja("B", "Baja"),
    Modificado("M", "Modificado"),
    Activo("A", "Nuevo");

    private String value;
    private String description;

    /**
     * Constructor por default.
     * @param pValue inicializa el valor del tipo de dato.
     * @param pDescription Descripcion
     */
    EstadoTracking(String pValue, String pDescription) {
    	this.value = pValue;
    	this.description = pDescription;
    }

    /**
     * Retorna el valor del Estado.
     * @return valor del Estado.
     */
    public String getValue() {
    	return value;
    }

    /**
	 * @return Retorna el valor del atributo description.
	 */
	public String getDescription() {
		return description;
	}

	/**
     * Retorna el enum a partir del código.
     * @param pValue Código del enum
     * @return {@link EstadoTracking}
     */
    public static EstadoTracking fromValue(String pValue) {
    	EstadoTracking result = Activo;

    	if (pValue.equals(Activo.getValue())) {
    		result = Activo;
    	} else if (pValue.equals(Baja.getValue())) {
    		result = Baja;
    	} else if (pValue.equals(Modificado.getValue())) {
    		result = Modificado;
    	}

    	return result;
    }
}
