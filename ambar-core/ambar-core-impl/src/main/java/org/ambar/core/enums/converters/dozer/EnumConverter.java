package org.ambar.core.enums.converters.dozer;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 * <p>
 * Permite convertir un Enum a un String.
 * </p>
 *
 * @author Sebastian
 *
 */
public final class EnumConverter {
    /**
     * Constructor por default.
     */
    private EnumConverter() {
       super();
    }

    /**
     * A common method for all enums since they can't have another base class.
     * @param <T> Enum type
     * @param pEnumeration enum type. All enums must be all caps.
     * @param pName case insensitive
     * @param pThrowIfNotFound true si debe dar una excepción al no
     * encontrar el valor.
     * @return corresponding enum, or null
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T getEnumFromString(
            final Class<T> pEnumeration,
            final String pName,
            final Boolean pThrowIfNotFound) {
        for (Enum<T> enumValue : pEnumeration.getEnumConstants()) {
        	String val = null;
        	try {
              val = BeanUtils.getProperty(enumValue, "valor");
			} catch (Exception e) {
			}
            if (enumValue.name().equalsIgnoreCase(pName)
            		|| (val != null && val.equalsIgnoreCase(pName))) {
                return (T) enumValue;
            }
            if (enumValue.toString().equalsIgnoreCase(pName)) {
                return (T) enumValue;
            }
        }
        if (pThrowIfNotFound) {
            throw new IllegalArgumentException(
                   "No existe el Valor '"
                    + pName
                    + " en el Enum "
                    + pEnumeration.getClass().getName());
        }
        return null;
    }

    /**
     * A common method for all enums since they can't have another base class.
     * @param <T> Enum type
     * @param pEnumeration enum type. All enums must be all caps.
     * @param pName case insensitive
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(
            final Class<T> pEnumeration, final String pName) {
        return getEnumFromString(pEnumeration, pName, false);
    }
}
