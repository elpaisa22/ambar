/**
 * ambar-core-dictionary [18/01/2014 11:54:52]
 */
package org.ambar.core.dictionary.domain.lookups;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa la metadata de la informacion de un lookup.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "lookup")
public class LookupMetaData implements Serializable {

	private static final long serialVersionUID = -5426885005149447591L;

	private String id;
	private String title;
	private String resultAttribute;
	private String descriptionAttribute;
	private String defaultService;
	private List<LookupAttribute> displayAttributes;

	private Map<String, LookupAttribute> attributesMap;

	/**
	 * @return Retorna el valor del atributo id.
	 */
    @XmlAttribute(name = "id")
	public String getId() {
		return id;
	}
	/**
	 * @param pId Establece el valor del atributo id.
	 */
	public void setId(String pId) {
		id = pId;
	}
	/**
	 * @return Retorna el valor del atributo title.
	 */
    @XmlAttribute(name = "title")
	public String getTitle() {
		return title;
	}
	/**
	 * @param pTitle Establece el valor del atributo title.
	 */
	public void setTitle(String pTitle) {
		title = pTitle;
	}
	/**
	 * @return Retorna el valor del atributo resultAttribute.
	 */
    @XmlAttribute(name = "resultAttribute")
	public String getResultAttribute() {
		return resultAttribute;
	}
	/**
	 * @param pResultAttribute Establece el valor del atributo resultAttribute.
	 */
	public void setResultAttribute(String pResultAttribute) {
		resultAttribute = pResultAttribute;
	}

	/**
	 * @return Retorna el valor del atributo descriptionAttribute.
	 */
    @XmlAttribute(name = "descriptionAttribute")
	public String getDescriptionAttribute() {
		return descriptionAttribute;
	}
	/**
	 * @param pDescriptionAttribute Establece el valor del atributo descriptionAttribute.
	 */
	public void setDescriptionAttribute(String pDescriptionAttribute) {
		descriptionAttribute = pDescriptionAttribute;
	}
	/**
	 * @return Retorna el valor del atributo defaultService.
	 */
	 @XmlAttribute(name = "defaultService", required = false)
	public String getDefaultService() {
		return defaultService;
	}
	/**
	 * @param pDefaultService Establece el valor del atributo defaultService.
	 */
	public void setDefaultService(String pDefaultService) {
		defaultService = pDefaultService;
	}
	/**
	 * @return Retorna el valor del atributo displayAttributes.
	 */
    @XmlElements({
        @XmlElement(name = "attribute", type = LookupAttribute.class)
    })
	public List<LookupAttribute> getDisplayAttributes() {
		return displayAttributes;
	}
	/**
	 * @param pDisplayAttributes Establece el valor del atributo displayAttributes.
	 */
	public void setDisplayAttributes(List<LookupAttribute> pDisplayAttributes) {
		displayAttributes = pDisplayAttributes;

		this.attributesMap = new HashMap<String, LookupAttribute>();
		for (LookupAttribute lookupAttribute : pDisplayAttributes) {
			this.attributesMap.put(lookupAttribute.getName(), lookupAttribute);
		}
	}

	/**
	 * Retorna el Atributo del lookup utilizado para retornar como valor.
	 * @return {@link LookupAttribute} Atributo
	 * */
	public LookupAttribute getAttributeResult() {
		return this.attributesMap.get(this.resultAttribute);
	}

	/**
	 * Retorna el Atributo del lookup utilizado para retornar como descripcion.
	 * @return {@link LookupAttribute} Atributo
	 * */
	public LookupAttribute getAttributeDescription() {
		if (this.descriptionAttribute != null) {
			return this.attributesMap.get(this.descriptionAttribute);
		} else {
			return null;
		}
	}

	/**
	 * Retorna el Atributo del lookup con el nombre que se recibe por parametro.
	 * @param pAttributeName Nombre del Atributo
	 * @return {@link LookupAttribute} Atributo
	 * */
	public LookupAttribute getAttribute(String pAttributeName) {
		if (this.attributesMap.containsKey(pAttributeName)) {
			return this.attributesMap.get(pAttributeName);
		} else {
			return null;
		}
	}
}
