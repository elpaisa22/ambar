/**
 * ambar-core-impl [12/10/2011 08:10:44]
 */
package org.ambar.core.commons.mapping.configuration.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.ambar.core.commons.mapping.configuration.ClassMapping;
import org.ambar.core.commons.mapping.configuration.FieldMapping;
import org.ambar.core.commons.mapping.configuration.MappingConfiguration;

/**
 * <p>
 * Implementación por default de {@link MappingConfiguration}.
 * </p>
 *
 * @author Sebastian
 *
 */
public class MappingConfigurationImpl implements MappingConfiguration {
	private Map<ClassMapping, Set<FieldMapping>> mappings;

	/**
	 * Constructor por default.
	 */
	public MappingConfigurationImpl() {
		super();
		this.mappings = new HashMap<ClassMapping, Set<FieldMapping>>();
	}

	/**
	 * @return Retorna el valor del atributo mappings.
	 */
	public Map<ClassMapping, Set<FieldMapping>> getMappings() {
		return this.mappings;
	}

	/**
	 * @param pMappings Establece el valor del atributo mappings.
	 */
	public void setMappings(Map<ClassMapping, Set<FieldMapping>> pMappings) {
		this.mappings = pMappings;
	}

	/* (non-JSDoc)
	 * @see org.ambar.core.commons.mapping.configuration.MappingConfiguration#fieldsFromClassMapping(
	 * 		java.lang.String, java.lang.String)
	 */
	@Override
	public Set<FieldMapping> fieldsFromClassMapping(String pClassAName, String pClassBName) {
		ClassMapping classMapping = new ClassMapping(pClassAName, pClassBName);
		Set<FieldMapping> fields = this.mappings.get(classMapping);
		return fields;
	}
}

