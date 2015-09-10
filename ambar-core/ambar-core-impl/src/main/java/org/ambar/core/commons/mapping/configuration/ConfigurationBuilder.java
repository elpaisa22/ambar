/**
 * ambar-core-impl [11/10/2011 16:34:58]
 */
package org.ambar.core.commons.mapping.configuration;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.transform.stream.StreamSource;

import org.ambar.core.commons.mapping.configuration.impl.MappingConfigurationImpl;
import org.ambar.core.commons.mapping.domain.Field;
import org.ambar.core.commons.mapping.domain.Mapping;
import org.ambar.core.commons.mapping.domain.MappingDirection;
import org.ambar.core.commons.mapping.domain.Mappings;
import org.ambar.core.commons.mapping.exceptions.MappingConfigurationClassNotFoundException;
import org.ambar.core.commons.mapping.exceptions.MappingConfigurationFileIOException;
import org.ambar.core.commons.mapping.exceptions.MappingConfigurationFileNotFoundException;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOption;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

/**
 * <p>Factory de la configuración de mapeos.</p>
 *
 * @author Sebastian
 *
 */
public class ConfigurationBuilder {
	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationBuilder.class);

	private DozerBeanMapper dozerBeanMapper;
	private MappingConfigurationImpl mappingConfigurationImpl;
	private Resource[] mappingFiles;
	private Unmarshaller unMarshaller;

	/**
	 * Constructor por default.
	 */
	public ConfigurationBuilder() {
		super();
	}

	/**
	 * Procesamiento de los archivos de mapeo y configuración.
	 */
	public void init() {
		this.dozerBeanMapper = new DozerBeanMapper();
		this.mappingConfigurationImpl = new MappingConfigurationImpl();

		for (Resource mappingFile : mappingFiles) {
			try {
				Mappings mappings = (Mappings) this.unMarshaller.unmarshal(
						new StreamSource(mappingFile.getInputStream()));
				this.loadDozerConfiguration(mappings);
				this.loadMappingConfiguration(mappings);
			} catch (FileNotFoundException e) {
				LOG.error("Archivo de mapeo no encontrado: {}", mappingFile.getFilename());
				throw new MappingConfigurationFileNotFoundException(
						"Archivo de mapeo no encontrado", e);
			} catch (IOException e) {
				LOG.error("Error en la lectura del archivo: {}", mappingFile.getFilename());
				throw new MappingConfigurationFileIOException(
						"Error en la lectura del archivo de mapeo", e);
			}
		}
	}

	/**
	 * Método que genera la configuración de <i>Dozer</i>.
	 * @param pMappings Mapeos a configurar
	 */
	private void loadDozerConfiguration(final Mappings pMappings) {
		BeanMappingBuilder builder = new BeanMappingBuilder() {

			private void mapFields(TypeMappingBuilder pTypeBuilder, List<Field> pFields) {
				if (pFields != null) {
					for (Field currentField : pFields) {
						List<FieldsMappingOption> options =
								new ArrayList<FieldsMappingOption>();
						if (currentField.getCustomConverter() != null) {
							options.add(
									FieldsMappingOptions
										.customConverter(
											currentField
												.getCustomConverter())
									);
						}
						if (currentField.getCustomConverterId() != null) {
							options.add(
									FieldsMappingOptions
										.customConverterId(
											currentField
												.getCustomConverterId())
									);
						}
						if (currentField.isCopyByReference()) {
							options.add(
									FieldsMappingOptions
										.copyByReference());
						}
						if ((currentField.getMappingDirection() != null)
								&& (currentField.getMappingDirection()
										.equals(MappingDirection.ONE_WAY))) {
							options.add(FieldsMappingOptions.oneWay());
						}
						options.add(FieldsMappingOptions.removeOrphans(
								currentField.isRemoveOrphans()));

						pTypeBuilder.fields(
								currentField.getFieldA(),
								currentField.getFieldB(),
								options.toArray(
									new FieldsMappingOption[options.size()]));
					}
				}
			}

			@Override
			protected void configure() {
				for (Mapping currentMapping : pMappings.getMappings()) {
					try {
						TypeMappingBuilder typeBuilder = mapping(
								Class.forName(currentMapping.getClassA()),
								Class.forName(currentMapping.getClassB()),
								TypeMappingOptions.stopOnErrors(
										currentMapping.isStopOnErrors()),
								TypeMappingOptions.wildcard(
										currentMapping.isWildcard()),
								TypeMappingOptions.dateFormat(
										currentMapping.getDateFormat()),
								TypeMappingOptions.trimStrings(
										currentMapping.isTrimStrings()),
								TypeMappingOptions.mapEmptyString(
										currentMapping.isMapEmptyString()),
								TypeMappingOptions.mapNull(
										currentMapping.isMapNull()));

						mapFields(typeBuilder, currentMapping.getFields());
					} catch (ClassNotFoundException e) {
						LOG.error("Clase no encontrada en configuración: {}",
								currentMapping.toString());
						throw new MappingConfigurationClassNotFoundException(
								"Clase no encontrada en configuración de mapeo", e);
					}
				}
			}
		};
		this.dozerBeanMapper.addMapping(builder);
	}

	/**
	 * Método que genera la estructura de datos con información de
	 * mapeos para resolver objetos asociados y filtros.
	 * @param pMappings Mapeos a configurar
	 */
	private void loadMappingConfiguration(Mappings pMappings) {
		for (Mapping mapping : pMappings.getMappings()) {
			if (mapping.getFields() != null) {
				ClassMapping classMapping = new ClassMapping();
				classMapping.setClassAName(mapping.getClassA());
				classMapping.setClassBName(mapping.getClassB());
				Set<FieldMapping> fieldMappings = new HashSet<FieldMapping>();

				for (Field field : mapping.getFields()) {
					if ((field.isInverseMappingWithJPA())
							|| (field.isComplexAssociation()
							|| (field.isAddWildcards())
							|| (field.isCaseInsensitive())
							|| (field.getQueryAlias() != null))) {
						FieldMapping fieldMapping = new FieldMapping();
						fieldMapping.setFieldA(field.getFieldA());
						fieldMapping.setFieldB(field.getFieldB());
						fieldMapping.setFieldAParent(field.getFieldAParent());
						fieldMapping.setMapWithPersisted(field.isInverseMappingWithJPA());
						fieldMapping.setDetailCollection(field.isDetailCollection());
						fieldMapping.setDetailParent(field.getDetailParent());
						fieldMapping.setComplexAssociation(field.isComplexAssociation());
                        fieldMapping.setQueryAlias(field.getQueryAlias());
                        fieldMapping.setAddWildcards(field.isAddWildcards());
                        fieldMapping.setCaseInsensitive(field.isCaseInsensitive());
						fieldMappings.add(fieldMapping);
					}
				}

				if (!fieldMappings.isEmpty()) {
					this.mappingConfigurationImpl.getMappings().put(classMapping, fieldMappings);
				}
			}
		}
	}

	/**
	 * @return Retorna el valor del atributo mappingFiles.
	 */
	public Resource[ ] getMappingFiles() {
		return this.mappingFiles;
	}

	/**
	 * @param pMappingFiles Establece el valor del atributo mappingFiles.
	 */
	public void setMappingFiles(Resource[ ] pMappingFiles) {
		this.mappingFiles = pMappingFiles;
	}

	/**
	 * @return Retorna el valor del atributo unMarshaller.
	 */
	public Unmarshaller getUnMarshaller() {
		return this.unMarshaller;
	}

	/**
	 * @param pUnMarshaller Establece el valor del atributo unMarshaller.
	 */
	public void setUnMarshaller(Unmarshaller pUnMarshaller) {
		this.unMarshaller = pUnMarshaller;
	}

	/**
	 * Factory de <i>dozer</i>.
	 * @return {@link DozerBeanMapper}
	 */
	public DozerBeanMapper dozerMapperFactory() {
		return this.dozerBeanMapper;
	}

	/**
	 * Factory del <i>bean</i> que encapsula información de mapeo.
	 * @return {@link MappingConfigurationImpl}
	 */
	public MappingConfigurationImpl mappingConfigurationFactory() {
		return this.mappingConfigurationImpl;
	}
}
