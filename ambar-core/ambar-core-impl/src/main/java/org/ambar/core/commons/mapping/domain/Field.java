/**
 * ambar-core-impl [12/10/2011 08:18:19]
 */
package org.ambar.core.commons.mapping.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * <p>Entidad usada para configurar los servicios de <i>mapping</i> a nivel de propiedad
 * de clase.</p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "field", namespace = "http://www.ambar.org/mapper")
public class Field implements Serializable {

	private static final long serialVersionUID = -4422343626914551272L;

	private String fieldA;
	private String fieldB;
	private String customConverter;
	private String customConverterId;
	private boolean useForFilter;
	private boolean inverseMappingWithJPA;
	private boolean copyByReference;
	private boolean removeOrphans;
	private String fieldAParent;
	private MappingDirection mappingDirection;
	private boolean detailCollection;
	private String detailParent;
	private boolean complexAssociation;
    private String queryAlias;
    private boolean caseInsensitive;
    private boolean addWildcards;

	/**
	 * Constructor por default.
	 */
	public Field() {
		super();
	}

	/**
	 * @return Retorna el valor del atributo fieldA.
	 */
	@XmlElement(name = "a", namespace = "http://www.ambar.org/mapper")
	public String getFieldA() {
		return this.fieldA;
	}

	/**
	 * @param pFieldA Establece el valor del atributo fieldA.
	 */
	public void setFieldA(String pFieldA) {
		this.fieldA = pFieldA;
	}

	/**
	 * @return Retorna el valor del atributo fieldB.
	 */
	@XmlElement(name = "b", namespace = "http://www.ambar.org/mapper")
	public String getFieldB() {
		return this.fieldB;
	}

	/**
	 * @param pFieldB Establece el valor del atributo fieldB.
	 */
	public void setFieldB(String pFieldB) {
		this.fieldB = pFieldB;
	}

	/**
	 * @return Retorna el valor del atributo customConverter.
	 */
	@XmlAttribute(name = "custom-converter")
	public String getCustomConverter() {
		return this.customConverter;
	}

	/**
	 * @param pCustomConverter Establece el valor del atributo customConverter.
	 */
	public void setCustomConverter(String pCustomConverter) {
		this.customConverter = pCustomConverter;
	}

	/**
	 * @return Retorna el valor del atributo customConverterId.
	 */
	@XmlAttribute(name = "custom-converter-id")
	public String getCustomConverterId() {
		return customConverterId;
	}

	/**
	 * @param pCustomConverterId Establece el valor del atributo customConverterId.
	 */
	public void setCustomConverterId(String pCustomConverterId) {
		customConverterId = pCustomConverterId;
	}

	/**
	 * @return Retorna el valor del atributo useForFilter.
	 */
	@XmlAttribute(name = "use-for-filter")
	public boolean isUseForFilter() {
		return this.useForFilter;
	}

	/**
	 * @param pUseForFilter Establece el valor del atributo useForFilter.
	 */
	public void setUseForFilter(boolean pUseForFilter) {
		this.useForFilter = pUseForFilter;
	}

	/**
	 * @return Retorna el valor del atributo inverseMappingWithJPA.
	 */
	@XmlAttribute(name = "inverse-with-jpa")
	public boolean isInverseMappingWithJPA() {
		return this.inverseMappingWithJPA;
	}

	/**
	 * @param pInverseMappingWithJPA Establece el valor del atributo inverseMappingWithJPA.
	 */
	public void setInverseMappingWithJPA(boolean pInverseMappingWithJPA) {
		this.inverseMappingWithJPA = pInverseMappingWithJPA;
	}

	/**
	 * @return Retorna el valor del atributo copyByReference.
	 */
	@XmlAttribute(name = "copy-by-reference")
	public boolean isCopyByReference() {
		return this.copyByReference;
	}

	/**
	 * @param pCopyByReference Establece el valor del atributo copyByReference.
	 */
	public void setCopyByReference(boolean pCopyByReference) {
		this.copyByReference = pCopyByReference;
	}

	/**
	 * @return Retorna el valor del atributo removeOrphans.
	 */
	@XmlAttribute(name = "remove-orphans")
	public boolean isRemoveOrphans() {
		return this.removeOrphans;
	}

	/**
	 * @param pRemoveOrphans Establece el valor del atributo removeOrphans.
	 */
	public void setRemoveOrphans(boolean pRemoveOrphans) {
		this.removeOrphans = pRemoveOrphans;
	}

	/**
	 * @return Retorna el valor del atributo fieldAParent.
	 */
	@XmlAttribute(name = "field-a-parent")
	public String getFieldAParent() {
		return this.fieldAParent;
	}

	/**
	 * @param pFieldAParent Establece el valor del atributo fieldAParent.
	 */
	public void setFieldAParent(String pFieldAParent) {
		this.fieldAParent = pFieldAParent;
	}

	/**
	 * @return Retorna el valor del atributo mappingDirection.
	 */
	@XmlAttribute(name = "type")
	public MappingDirection getMappingDirection() {
		return this.mappingDirection;
	}

	/**
	 * @param pMappingDirection Establece el valor del atributo mappingDirection.
	 */
	public void setMappingDirection(MappingDirection pMappingDirection) {
		this.mappingDirection = pMappingDirection;
	}

	/**
	 * @return Retorna el valor del atributo detailCollection.
	 */
	@XmlAttribute(name = "detail-collection")
	public boolean isDetailCollection() {
		return this.detailCollection;
	}

	/**
	 * @param pDetailCollection Establece el valor del atributo detailCollection.
	 */
	public void setDetailCollection(boolean pDetailCollection) {
		this.detailCollection = pDetailCollection;
	}

	/**
	 * @return Retorna el valor del atributo detailParent.
	 */
	@XmlAttribute(name = "detail-parent")
	public String getDetailParent() {
		return this.detailParent;
	}

	/**
	 * @param pDetailParent Establece el valor del atributo detailParent.
	 */
	public void setDetailParent(String pDetailParent) {
		this.detailParent = pDetailParent;
	}

	/**
	 * @return Retorna el valor del atributo complexAssociation.
	 */
	@XmlAttribute(name = "complex-association")
	public boolean isComplexAssociation() {
		return this.complexAssociation;
	}

	/**
	 * @param pComplexAssociation Establece el valor del atributo complexAssociation.
	 */
	public void setComplexAssociation(boolean pComplexAssociation) {
		this.complexAssociation = pComplexAssociation;
	}

    /**
     * @return Retorna el valor del atributo queryAlias.
     */
    @XmlAttribute(name = "query-alias")
    public String getQueryAlias() {
        return this.queryAlias;
    }

    /**
     * @param pQueryAlias Establece el valor del atributo queryAlias.
     */
    public void setQueryAlias(final String pQueryAlias) {
        this.queryAlias = pQueryAlias;
    }

    /**
     * @return Retorna el valor del atributo caseInsensitive.
     */
    @XmlAttribute(name = "case-insensitive")
    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    /**
     * @param pCaseInsensitive Establece el valor del atributo caseInsensitive.
     */
    public void setCaseInsensitive(final boolean pCaseInsensitive) {
        caseInsensitive = pCaseInsensitive;
    }

    /**
     * @return Retorna el valor del atributo addWildcards.
     */
    @XmlAttribute(name = "add-wildcards")
    public boolean isAddWildcards() {
        return addWildcards;
    }

    /**
     * @param pAddWildcards Establece el valor del atributo addWildcards.
     */
    public void setAddWildcards(final boolean pAddWildcards) {
        addWildcards = pAddWildcards;
    }

	/* (non-JSDoc)
      * @see java.lang.Object#toString()
      */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Field");
        sb.append("{fieldA='").append(fieldA).append('\'');
        sb.append(", fieldB='").append(fieldB).append('\'');
        sb.append(", customConverter='").append(customConverter).append('\'');
        sb.append(", useForFilter=").append(useForFilter);
        sb.append(", inverseMappingWithJPA=").append(inverseMappingWithJPA);
        sb.append(", copyByReference=").append(copyByReference);
        sb.append(", removeOrphans=").append(removeOrphans);
        sb.append(", fieldAParent='").append(fieldAParent).append('\'');
        sb.append(", mappingDirection=").append(mappingDirection);
        sb.append(", detailCollection=").append(detailCollection);
        sb.append(", detailParent='").append(detailParent).append('\'');
        sb.append(", complexAssociation=").append(complexAssociation);
        sb.append(", queryAlias='").append(queryAlias).append('\'');
        sb.append(", caseInsensitive=").append(caseInsensitive);
        sb.append(", addWildcards=").append(addWildcards);
        sb.append('}');
        return sb.toString();
    }
}
