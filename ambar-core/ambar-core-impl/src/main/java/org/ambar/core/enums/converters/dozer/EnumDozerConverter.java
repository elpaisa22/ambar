/**
 * ambar-core-impl [28/02/2012 18:32:29]
 */
package org.ambar.core.enums.converters.dozer;

import org.apache.commons.beanutils.BeanUtils;
import org.dozer.CustomConverter;

/**
 * <p>
 * Converter de Dozer que permite pasar de un String a un Enum.
 * </p>
 *
 * @author Sebastian
 */
public class EnumDozerConverter implements CustomConverter {

    /**
     * Convierte de String a Enum o alrevés.
     *
     * @param pExistingDestinationFieldValue destination.
     * @param pSourceFieldValue source.
     * @param pDestinationClass destination class.
     * @param pSourceClass source class.
     * @return Enum o String.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public final Object convert(final Object pExistingDestinationFieldValue,
            final Object pSourceFieldValue,
            final Class<?> pDestinationClass,
            final Class<?> pSourceClass) {

        if (pDestinationClass.equals(String.class)) {
            String val = null;
        	try {
              val = BeanUtils.getProperty(pSourceFieldValue, "valor");
			} catch (Exception e) {
				return null;
			}
        	return val;
        } else {
            return EnumConverter.getEnumFromString(
               (Class<? extends Enum>) pDestinationClass,
                   (String) pSourceFieldValue);
        }
    }
}
