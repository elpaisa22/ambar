/**
 * ambar-core-impl [12/10/2011 08:21:24]
 */
package org.ambar.core.commons.mapping.domain;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Enumeración con las posibles direcciones de mapeo.</p>
 *
 * @author Sebastian
 *
 */
@XmlType(name = "type")
@XmlEnum
public enum MappingDirection {

	/**
	 * Mapeos bi-direccionales.
	 */
	@XmlEnumValue(value = "bi-directional")
	BI_DIRECTIONAL("bi-directional"),

	/**
	 * Mapeos exclusivamente del atributo de la clase A a la B.
	 */
	@XmlEnumValue(value = "one-way")
	ONE_WAY("one-way");

	private String value;

    /**
     * Constructor por default.
     * @param pValue inicializa el valor del tipo de dato.
     */
	private MappingDirection(String pValue) {
		this.value = pValue;
	}

	/**
     * Retorna el valor del Estado.
     * @return valor del Estado.
     */
    public String getValue() {
    	return value;
    }

    /**
     * Retorna el enum a partir del código.
     * @param pValue Código del enum
     * @return {@link MappingDirection}
     */
    public static MappingDirection fromValue(String pValue) {
    	MappingDirection result = MappingDirection.BI_DIRECTIONAL;

    	if (pValue.equals(MappingDirection.ONE_WAY.getValue())) {
    		result = MappingDirection.ONE_WAY;
    	}

    	return result;
    }
}

