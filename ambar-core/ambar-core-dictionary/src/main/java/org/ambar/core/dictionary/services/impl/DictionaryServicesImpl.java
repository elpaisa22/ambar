/**
 * ambar-core-dictionary [08/03/2012 21:13:30]
 */
package org.ambar.core.dictionary.services.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.GroupFilter;
import org.ambar.core.commons.filters.GroupOperator;
import org.ambar.core.commons.filters.criteria.Criteria;
import org.ambar.core.dictionary.domain.entities.Attribute;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.domain.navigation.Item;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.ambar.core.dictionary.domain.navigation.SubEntity;
import org.ambar.core.dictionary.expressions.ExpressionToFilter;
import org.ambar.core.dictionary.services.DictionaryServices;



/**
 * <p>
 * Implementación por default de {@link DictionaryServices}.
 * </p>
 *
 * @author Sebastian
 *
 */
public class DictionaryServicesImpl implements DictionaryServices, Serializable {

    private static final long serialVersionUID = -6205038220905169339L;

	private Map<String, Entity> entities;
    private Map<String, EntityInfo> entitiesInfo;
    private List<Module> modules;
    private Map<String, LookupMetaData> lookups;



    /**
     * Constructor por default.
     */
    public DictionaryServicesImpl() {
        super();
        this.entities = new HashMap<String, Entity>();
        this.entitiesInfo = new HashMap<String, EntityInfo>();
        this.modules = new ArrayList<Module>();
        this.lookups = new HashMap<String, LookupMetaData>();
    }


    /**
	 * @return Retorna el valor del atributo entities.
	 */
	public Map<String, Entity> getEntities() {
		return entities;
	}


	@Override
	public Attribute getAttributeForEntity(String pIdEntity, String pIdAttribute) {
		if (entities.get(pIdEntity) != null) {
			List<Attribute> attributes = entities.get(pIdEntity).getAttributes();
			Iterator<Attribute> attIterator = attributes.iterator();
			while (attIterator.hasNext()) {
				Attribute attribute = attIterator.next();
				if (attribute.getId().equalsIgnoreCase(pIdAttribute)) {
					return attribute;
				}
			}
		}
		return null;
	}


	/**
	 * @return Retorna el valor del atributo entitiesInfo.
	 */
	public Map<String, EntityInfo> getEntitiesInfo() {
		return entitiesInfo;
	}


	/**
	 * @return Retorna el valor del atributo modules.
	 */
	public List<Module> getModules() {
		return modules;
	}


	/**
	 * @return Retorna el valor del atributo lookups.
	 */
	public Map<String, LookupMetaData> getLookups() {
		return lookups;
	}


	/**
     * {@inheritDoc}
     */
	@Override
	public List<Module> getAllModules() {
		List<Module> list = modules;

		return list;
	}


	/**
     * {@inheritDoc}
     */
    @Override
    public Entity getEntityMetaDataById(final String pId) {
        return this.entities.get(pId);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Filter expressionToFilter(final String pExpression,
                                     final Object pSourceValueEntityMetaData) {

        return ExpressionToFilter.getInstance().convertExpression(
                pExpression, pSourceValueEntityMetaData);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String processParametersInTitle(final String pBrowseTitle, final Object pSourceValueEntityMetaData) {
        String result = pBrowseTitle;
        if ((pBrowseTitle != null) && (!pBrowseTitle.trim().isEmpty()) && (pBrowseTitle.contains(":"))) {
            result = ExpressionToFilter.getInstance().processParameterInExpression(
                    pBrowseTitle, pSourceValueEntityMetaData);
        }
        return result;
    }


    /**
     * {@inheritDoc}
     */
	@Override
	public EntityInfo getNavigationEntityInfoById(String pId) {
		return this.getEntitiesInfo().get(pId);
	}


	/**
     * {@inheritDoc}
     */
	@Override
	public List<SubEntity> getSubEntitiesForEntity(String pEntityId) {
		return this.entitiesInfo.get(pEntityId).getSubentities();
	}


	@Override
	public Module getModuleById(String pId) {
		Module module = buscarModulo(modules, pId);
		return module;
	}


	/**
	 * Busca un modulo con el ID pID.
	 * @param pModules Modulos
	 * @param pId ID del Modulo
	 * @return {@link Module} Modulo.
	 * */
	private Module buscarModulo(List<Module> pModules, String pId) {
		for (Module module : pModules) {
			if (pId.equals(module.getId())) {
				return module;
			}
		}
		return null;
	}


	@Override
	public List<Item> getItemsForModule(String pIdModule) {
		Module module = buscarModulo(modules, pIdModule);
		if (module != null) {
			return module.getItems();
		}
		return null;
	}


	@Override
	public Filter getGlobalFilter(String pIdEntity, Object pValue) {

		if (pValue == null || pValue.toString().equals("")) {
			return null;
		}

		Entity entity = this.entities.get(pIdEntity);
		GroupFilter groupFilter = new GroupFilter(GroupOperator.Or);

		for (Attribute attribute : entity.getAttributes()) {
			if (attribute.getSearchEnabled()) {
				Filter filter = getFilter(attribute.getId(), pValue, attribute.getDataType());
				if (filter != null) {
					groupFilter.getInnerFilters().add(filter);
				}
			}
		}

		return groupFilter;
	}

	@Override
	public Filter getFilter(String pIdAttribute, Object pValue, DataType pDataType) {
		Filter filter = null;

		Object aux = castObject(pValue, pDataType);

		switch (pDataType) {
			case String:
				if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).like(aux);
				}
				break;
			case Char:
				if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
				}
				break;
			case Number:
				if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
				}
				break;
			case Money:
				if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
				}
				break;
			case Text:
                if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).like(aux);
				}
				break;
			case Integer:
                if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
				}
				break;
			case Enum:
                if (aux != null) {
                    filter = Criteria.createBinary().property(pIdAttribute + "Persistent").like(aux);
				}
				break;
			case DateTime:
                if (aux != null) {
                    filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
				}
				break;
			default:
				break;
		}

		return filter;
	}

	/**
	 * Hace un "cast" del valor al tipo de datos especificado.
	 * @param pValue Valor
	 * @param pDataType Tipo de dato
	 * @return {@link Object} Valor resultado
	 * */
	private Object castObject(Object pValue, DataType pDataType) {
		Object value = null;
		try {
			switch (pDataType) {
				case String:
	                value = "%" + pValue.toString() + "%";
					break;
				case Char:
					value = pValue.toString();
					break;
				case Number:
					if (isNumber(pValue.toString())) {
						value = new Long(pValue.toString());
					}
					break;
				case Money:
					if (isNumber(pValue.toString())) {
						value = new BigDecimal(pValue.toString());
					}
					break;
				case Text:
					value = "%" + pValue.toString() + "%";
					break;
				case Integer:
					if (isInteger(pValue.toString())) {
						value = new Integer(pValue.toString());
					}
					break;
				case Enum:
	                value = "%" + pValue.toString() + "%";
					break;
				case DateTime:
					if (isDateTime(pValue.toString())) {
						SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
						value = format.parse(pValue.toString());
					}
					break;
				default:
					value = pValue;
					break;
			}
		} catch (Exception e) {
			value = null;
		}

		return value;
	}


	@Override
	public Filter getPropertyFilter(String pIdEntity, String pIdAttribute, Object pValue) {
		Attribute attribute = getAttributeForEntity(pIdEntity, pIdAttribute);
		return getFilter(attribute.getId(), pValue, attribute.getDataType());
	}

	/**
	 * Verifica si el pInput puede ser convertido a un Integer.
	 * @param pInput Input
	 * @return {@link Boolean} Resultado
	 **/
	public boolean isInteger(String pInput) {
	   try {
	      Integer.parseInt(pInput);
	      return true;
	   } catch (Exception e) {
	      return false;
	   }
	}

	/**
	 * Verifica si el pInput puede ser convertido a un Numero.
	 * @param pInput Input
	 * @return {@link Boolean} Resultado
	 **/
	public boolean isNumber(String pInput) {
	   try {
		  Double.parseDouble(pInput);
	      return true;
	   } catch (Exception e) {
	      return false;
	   }
	}

	/**
	 * Verifica si el pInput puede ser convertido a un DateTime.
	 * @param pDate Input
	 * @return {@link Boolean} Resultado
	 **/
	private boolean isDateTime(String pDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			format.parse(pDate);
			return true;
		} catch (ParseException ex) {
			return false;
		}

	}


	@Override
	public LookupMetaData getLookupById(String pId) {
		return this.lookups.get(pId);
	}


}

