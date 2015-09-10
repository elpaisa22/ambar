/**
 * ambar-core-impl [28/02/2012 19:42:49]
 */
package org.ambar.core.enums.converters.dozer;

import org.ambar.core.enums.EnumType;
import org.dozer.CustomConverter;

/**
 * <p>
 * Converter de Dozer que permite a prtir de un Enum obtener su descripción.
 * </p>
 * @author Sebastian
 *
 *
 */
public class EnumDescDozerConverter implements CustomConverter {

	/**
     * Convierte de Enum a String tomando la descripcion.
     *
     * @param pExistingDestinationFieldValue destination.
     * @param pSourceFieldValue source.
     * @param pDestinationClass destination class.
     * @param pSourceClass source class.
     * @return Enum o String.
     */
    @Override
    public final Object convert(final Object pExistingDestinationFieldValue,
            final Object pSourceFieldValue,
            final Class<?> pDestinationClass,
            final Class<?> pSourceClass) {

        if ((pDestinationClass.equals(String.class))
        		&& (pSourceFieldValue instanceof EnumType)) {
        	EnumType enumType = (EnumType) pSourceFieldValue;
            return enumType.getDescripcion();
        } else {
        	throw new IllegalArgumentException(
                    "No existe Descripcion en el Enum "
                     + pSourceFieldValue.getClass().getName());
        }
    }
}
