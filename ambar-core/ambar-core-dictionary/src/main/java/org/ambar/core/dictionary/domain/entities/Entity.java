/**
 * ambar-core-dictionary [11/03/2012 17:41:32]
 */
package org.ambar.core.dictionary.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase que representa la meta data de una entidad.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "entity", namespace = "http://www.ambar.org/entities")
public class Entity implements Serializable {


    private static final long serialVersionUID = 5113324175016623415L;

	private String id;
	private List<Attribute> attributes;

    /**
     * @return Retorna el valor del atributo id.
     */
    @XmlAttribute(name = "id")
    public String getId() {
        return this.id;
    }

    /**
     * @param pId Establece el valor del atributo id.
     */
    public void setId(final String pId) {
        this.id = pId;
    }


    /**
	 * @return Retorna el valor del atributo attributes.
	 */
    @XmlElements({
        @XmlElement(name = "attribute", type = Attribute.class, namespace = "http://www.ambar.org/entities")
    })
	public List<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * @param pAttributes Establece el valor del atributo attributes.
	 */
	public void setAttributes(List<Attribute> pAttributes) {
		attributes = pAttributes;
	}

	@Override
    public boolean equals(final Object pObj) {
        if (this == pObj) {
            return true;
        }
        if ((pObj == null) || (this.getClass() != pObj.getClass())) {
            return false;
        }

        final Entity entity = (Entity) pObj;

        if ((this.id != null) ? (!this.id.equals(entity.id)) : (entity.id != null)) {
            return false;
        }

        return true;
    }

    /* (non-JSDoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return ((this.id != null) ? this.id.hashCode() : 0);
    }

    /* (non-JSDoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Entity");
        sb.append("{id='").append(this.id).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
