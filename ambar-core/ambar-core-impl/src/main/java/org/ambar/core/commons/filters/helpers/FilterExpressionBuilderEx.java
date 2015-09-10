/**
 * ambar-core-impl [11/10/2011 02:59:35]
 */
package org.ambar.core.commons.filters.helpers;

import java.util.Set;

import org.ambar.core.commons.mapping.configuration.FieldMapping;

/**
 * <p>Interfaz que extiende a {@link FilterExpressionBuilder} para poder
 * proveer una estructura de datos con la configuración del mapeo de las
 * propiedades de los objetos a filtrar.</p>
 *
 * @author Sebastian
 *
 */
public interface FilterExpressionBuilderEx extends FilterExpressionBuilder {
    /**
     * Setter que permitirá establecer la colección de mapeos configurados.
     * @param pMappings Valor a setear
     */
    void setMappings(final Set<FieldMapping> pMappings);
}

