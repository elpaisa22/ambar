/**
 * ambar-core-impl [11/10/2011 16:32:51]
 */
package org.ambar.core.commons.mapping.configuration;

import java.util.Set;

/**
 * <p>
 * Operaciones de acceso a la configuración de mapeo entre dos clases.
 * </p>
 *
 * @author Sebastian
 *
 */
public interface MappingConfiguration {
	/**
	 * Método que retorna la lista de propiedades con mapeos configurados
	 * a partir del par de clases correspondiente.
	 * @param pClassAName Clase A del mapeo
	 * @param pClassBName Clase B del mapeo
	 * @return {@link Set} Lista de campos
	 */
	Set<FieldMapping> fieldsFromClassMapping(String pClassAName, String pClassBName);
}
