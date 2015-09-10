/**
 * ambar-core-dictionary [11/03/2012 17:41:49]
 */
package org.ambar.core.dictionary.domain.entities;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Clase raíz de la jerarquía correspondiente en el xml donde
 * se configura información adicional de las entidades.
 * </p>
 *
 * @author Sebastian
 *
 */
@XmlRootElement(name = "entities", namespace = "http://www.ambar.org/entities")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType
public class Entities implements Serializable {


    private static final long serialVersionUID = 6792820234525628592L;

	private List<Entity> entitiesMetaData;

    /**
     * @return Retorna el valor del atributo entitiesMetaData.
     */
    @XmlElements({
        @XmlElement(name = "entity", type = Entity.class, namespace = "http://www.ambar.org/entities")
    })
    public List<Entity> getEntitiesMetaData() {
        return this.entitiesMetaData;
    }

    /**
     * @param pEntitiesMetaData Establece el valor del atributo entitiesMetaData.
     */
    public void setEntitiesMetaData(final List<Entity> pEntitiesMetaData) {
        this.entitiesMetaData = pEntitiesMetaData;
    }
}
