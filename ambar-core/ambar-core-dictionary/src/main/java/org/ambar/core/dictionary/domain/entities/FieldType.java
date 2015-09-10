/**
 * ambar-core-dictionary [11/03/2012 18:18:15]
 */
package org.ambar.core.dictionary.domain.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Tipos de propiedades para las entidades.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlType(name = "fieldType")
@XmlEnum
public enum FieldType {

	@XmlEnumValue(value = "field")
	FIELD("field"),

	@XmlEnumValue(value = "primary-key")
	PRIMARYKEY("primary-key");

	private String value;

    /**
     * Constructor por default.
     * @param pValue inicializa el valor del tipo de dato.
     */
	FieldType(final String pValue) {
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
     * @return {@link FieldType}
     */
    public static FieldType fromValue(String pValue) {
    	FieldType result = null;

        if (pValue.equals(FieldType.FIELD.getValue())) {
            result = FieldType.FIELD;
        } else if (pValue.equals(FieldType.PRIMARYKEY.getValue())) {
            result = FieldType.PRIMARYKEY;
        }

        return result;
    }

}
