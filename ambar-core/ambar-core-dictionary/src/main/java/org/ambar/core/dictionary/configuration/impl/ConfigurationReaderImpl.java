/**
 * ambar-core-dictionary [11/03/2012 18:56:20]
 */
package org.ambar.core.dictionary.configuration.impl;

import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.ambar.core.dictionary.configuration.ConfigurationReader;
import org.ambar.core.dictionary.domain.entities.Entities;
import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.domain.lookups.Lookups;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.ambar.core.dictionary.domain.navigation.NavigationManager;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.dictionary.services.impl.DictionaryServicesImpl;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;

/**
 * <p>
 * Implementacion default de {@link ConfigurationReader}.
 * </p>
 *
 * @author Sebastian
 *
 *
 */
public class ConfigurationReaderImpl implements ConfigurationReader {


	private Resource entityMetaDataFile;
	private Unmarshaller entityMetaDataUnMarshaller;
	private Resource navigationFile;
	private Unmarshaller navigationUnMarshaller;
	private Resource lookupFile;
	private Unmarshaller lookupUnMarshaller;
	private DictionaryServicesImpl dictionaryServices;
	private Entities entities;
	private Lookups lookups;
	private NavigationManager navigationManager;


	/**
	 * @return Retorna el valor del atributo entityMetaDataFile.
	 */
	public Resource getEntityMetaDataFile() {
		return entityMetaDataFile;
	}

	/**
	 * @param pEntityMetaDataFile Establece el valor del atributo entityMetaDataFile.
	 */
	public void setEntityMetaDataFile(Resource pEntityMetaDataFile) {
		entityMetaDataFile = pEntityMetaDataFile;
	}

	/**
	 * @return Retorna el valor del atributo entityMetaDataUnMarshaller.
	 */
	public Unmarshaller getEntityMetaDataUnMarshaller() {
		return entityMetaDataUnMarshaller;
	}

	/**
	 * @param pEntityMetaDataUnMarshaller Establece el valor del atributo entityMetaDataUnMarshaller.
	 */
	public void setEntityMetaDataUnMarshaller(Unmarshaller pEntityMetaDataUnMarshaller) {
		entityMetaDataUnMarshaller = pEntityMetaDataUnMarshaller;
	}

	/**
	 * @return Retorna el valor del atributo navigationFile.
	 */
	public Resource getNavigationFile() {
		return navigationFile;
	}

	/**
	 * @param pNavigationFile Establece el valor del atributo navigationFile.
	 */
	public void setNavigationFile(Resource pNavigationFile) {
		navigationFile = pNavigationFile;
	}

	/**
	 * @return Retorna el valor del atributo navigationUnMarshaller.
	 */
	public Unmarshaller getNavigationUnMarshaller() {
		return navigationUnMarshaller;
	}

	/**
	 * @param pNavigationUnMarshaller Establece el valor del atributo navigationUnMarshaller.
	 */
	public void setNavigationUnMarshaller(Unmarshaller pNavigationUnMarshaller) {
		navigationUnMarshaller = pNavigationUnMarshaller;
	}

	/**
	 * @return Retorna el valor del atributo lookupFile.
	 */
	public Resource getLookupFile() {
		return lookupFile;
	}

	/**
	 * @param pLookupFile Establece el valor del atributo lookupFile.
	 */
	public void setLookupFile(Resource pLookupFile) {
		this.lookupFile = pLookupFile;
	}

	/**
	 * @return Retorna el valor del atributo lookupUnMarshaller.
	 */
	public Unmarshaller getLookupUnMarshaller() {
		return lookupUnMarshaller;
	}

	/**
	 * @param pLookupUnMarshaller Establece el valor del atributo lookupUnMarshaller.
	 */
	public void setLookupUnMarshaller(Unmarshaller pLookupUnMarshaller) {
		this.lookupUnMarshaller = pLookupUnMarshaller;
	}

	/**
	 * @return Retorna el valor del atributo navigationManager.
	 */
	public NavigationManager getNavigationManager() {
		return navigationManager;
	}

	@Override
	public DictionaryServices getDictionaryServices() {
		return this.dictionaryServices;
	}

	@Override
	public void configure() {
		this.dictionaryServices = new DictionaryServicesImpl();

		try {
			//Lee las entidades y las carga en el diccionario
			this.entities = (Entities) this.entityMetaDataUnMarshaller.unmarshal(
			        new StreamSource(this.entityMetaDataFile.getInputStream()));
			assert (this.entities != null)
            : "El archivo EntitiesMetaData.xml no pudo ser leído";
			assert (this.entities.getEntitiesMetaData() != null)
            : "No se leyeron 'entities' en el archivo EntitiesMetaData.xml";


			for (Entity entity : entities.getEntitiesMetaData()) {
				this.dictionaryServices.getEntities().put(entity.getId(), entity);
			}


			//Lee la info de la navegacion
			this.navigationManager = (NavigationManager) this.navigationUnMarshaller.unmarshal(
			        new StreamSource(this.navigationFile.getInputStream()));
			assert (this.navigationManager != null)
            : "El archivo NavigationManager.xml no pudo ser leído";
			assert (this.navigationManager.getEntitiesInformation() != null)
            : "No se leyó 'entitiesInformation' en el archivo NavigationManager.xml";

			//Carga los modulos en el servicio de diccionario
			for (Module module : navigationManager.getNavigation().getModules()) {
				this.dictionaryServices.getModules().add(module);
			}

			//Carga la informacion de navegacion de las entidades en el servicio de diccionario
			for (EntityInfo entityInfo : navigationManager.getEntitiesInformation().getEntities()) {
				this.dictionaryServices.getEntitiesInfo().put(entityInfo.getId(), entityInfo);
			}

			//Lee los lookups y los carga en el diccionario
			if (this.lookupUnMarshaller != null) {
				this.lookups = (Lookups) this.lookupUnMarshaller.unmarshal(
				        new StreamSource(this.lookupFile.getInputStream()));
				assert (this.lookups != null)
	            : "El archivo EntitiesMetaData.xml no pudo ser leído";
				assert (this.lookups.getLookups() != null)
	            : "No se leyeron 'lookups' en el archivo EntitiesMetaData.xml";

				for (LookupMetaData lookup : lookups.getLookups()) {
					this.dictionaryServices.getLookups().put(lookup.getId(), lookup);
				}
			}

		} catch (XmlMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
