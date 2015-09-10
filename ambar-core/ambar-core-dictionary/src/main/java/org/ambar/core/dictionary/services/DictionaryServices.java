/**
 * ambar-core-dictionary [08/03/2012 21:11:58]
 */
package org.ambar.core.dictionary.services;

import java.util.List;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.dictionary.domain.entities.Attribute;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.Item;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.ambar.core.dictionary.domain.navigation.SubEntity;



/**
 * <p>
 * Servicios que exponen la metadata del menú, la configuración de las vistas
 * y la navegabilidad de la aplicación.
 * </p>
 *
 * @author Sebastian
 */
public interface DictionaryServices {

    /**
     * Método que retorna una entidad a partir de su identificador.
     * @param pId   Identificador de la entidad
     * @return      {@link Entity}
     */
    Entity getEntityMetaDataById(String pId);

    /**
     * Método que retorna un atributo a partir de su identificador y el de la entidad.
     * @param 	pIdEntity  Identificador de la entidad.
     * @param 	pIdAttribute  Identificador del atributo.
     * @return      {@link Entity}
     */
    Attribute getAttributeForEntity(String pIdEntity, String pIdAttribute);

    /**
     * Método que retorna la informacion de navegacion de una entidad
     *  a partir de su identificador.
     * @param pId   Identificador de la entidad
     * @return      {@link EntityInfo}
     */
    EntityInfo getNavigationEntityInfoById(String pId);


    /**
     * Metodo que retorna todas las subentidades para una entidad.
     * @param	pEntityId ID de la Entidad
     * @return  {@link List<SubEntity>} Lista de SubEntidades
     * */
    List<SubEntity> getSubEntitiesForEntity(String pEntityId);

    /**
     * Retorna la configuracion del lookup para el ID
     * que se recibe por parametro.
     * @param pId ID del lookup
     * @return {@link LookupMetaData} Lookup
     * */
    LookupMetaData getLookupById(String pId);

    /**
     * Metodo que retorna todos los modulos de navegacion.
     * @return      {@link Module}
     * */
    List<Module> getAllModules();

    /**
     * Método que retorna la informacion de un modulo
     *  a partir de su identificador.
     * @param pId   Identificador del modulo
     * @return      {@link Module}
     */
    Module getModuleById(String pId);

    /**
     * Método que retorna la coleccion de items de un modulo
     *  a partir de su identificador.
     * @param pIdModule Identificador del modulo
     * @return      {List<Item>} Lista de Items
     */
    List<Item> getItemsForModule(String pIdModule);


    /**
     * Método que resuelve expresiones SQL y retorna el objeto {@link Filter} configurado.
     * @param pExpression           Expresión a analizar
     * @param pSourceValueEntityMetaData    Objeto con los valores para el filtro
     * @return {@link Filter}
     */
    Filter expressionToFilter(String pExpression, Object pSourceValueEntityMetaData);

    /**
     * Recibe un ID de una entidad y un valor, y retorna un filtro para
     *  buscar en todos los campos posibles ese valor.
     * @param 	pIdEntity  Identificador de la entidad.
     * @param pValue  Valor
     * @return {@link Filter}
     */
    Filter getGlobalFilter(String pIdEntity, Object pValue);

    /**
     * Recibe un ID de una entidad, un ID de una propiedad y un valor;
     *  y retorna un filtro para esa propiedad.
     * @param pIdEntity  Identificador de la entidad.
     * @param pIdAttribute Atributo.
     * @param pValue  Valor
     * @return {@link Filter}
     */
    Filter getPropertyFilter(String pIdEntity, String pIdAttribute, Object pValue);

    /**
     * Método que resuelve la expresión que se coloca en los títulos de las entidades utilizando
     * la navegabilidad.
     * @param pBrowseTitle          Expresión a resolver
     * @param pSourceValueEntityMetaData    Entidad con valores a utlizar en la expresión
     * @return {@link String}       Expresión resuelta
     */
    String processParametersInTitle(String pBrowseTitle, Object pSourceValueEntityMetaData);

    /**
	 * Calcula el filtro para el atributo con el tipo y valor especificado en los parametros.
	 * @param pValue Valor
	 * @param pIdAttribute Id del atributo
	 * @param pDataType Tipo de Dato
	 * @return {@link Filter} Filtro
	 * */
	Filter getFilter(String pIdAttribute, Object pValue, DataType pDataType);
}

