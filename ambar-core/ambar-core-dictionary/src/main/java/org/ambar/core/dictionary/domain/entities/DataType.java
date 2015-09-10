/**
 * ambar-core-dictionary [08/03/2012 21:17:01]
 */
package org.ambar.core.dictionary.domain.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Tipos de datos tal cual están representados en el Diccionario.</p>
 *
 * @author Sebastian
 *
 */
@XmlType(name = "dataType")
@XmlEnum
public enum DataType {
	@XmlEnumValue(value = "Char")
	Char("Char"),

	@XmlEnumValue(value = "String")
    String("String"),

    @XmlEnumValue(value = "Number")
    Number("Number"),

    @XmlEnumValue(value = "Text")
    Text("Text"),

    @XmlEnumValue(value = "Boolean")
	Boolean("Boolean"),

    @XmlEnumValue(value = "Integer")
    Integer("Integer"),

    @XmlEnumValue(value = "DateTime")
    DateTime("DateTime"),

    @XmlEnumValue(value = "Money")
    Money("Money"),

	@XmlEnumValue(value = "Enum")
    Enum("Enum");

    private String value;

    /**
     * Constructor por default.
     * @param pValue inicializa el valor del tipo de dato.
     */
    DataType(final String pValue) {
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
     * @return {@link DataType}
     */
    public static DataType fromValue(String pValue) {
        DataType result = DataType.String;

        if (pValue.equals(DataType.Char.getValue())) {
            result = DataType.Char;
        } else if (pValue.equals(DataType.Number.getValue())) {
            result = DataType.Number;
        } else if (pValue.equals(DataType.Text.getValue())) {
            result = DataType.Text;
        } else if (pValue.equals(DataType.Boolean.getValue())) {
            result = DataType.Boolean;
        } else if (pValue.equals(DataType.Integer.getValue())) {
            result = DataType.Integer;
        } else if (pValue.equals(DataType.DateTime.getValue())) {
            result = DataType.DateTime;
        } else if (pValue.equals(DataType.Money.getValue())) {
            result = DataType.Money;
        } else if (pValue.equals(DataType.Enum.getValue())) {
            result = DataType.Enum;
        }

        return result;
    }
}

