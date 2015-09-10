/*
 * Copyright (c) 2012, Oracle and/or its affiliates. All rights reserved.
 */

package org.ambar.core.views.crud.components.lookup;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.commons.filters.criteria.Criteria;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.lookups.LookupAttribute;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultListDTO;
import org.ambar.core.services.DataServices;
import org.ambar.core.spring.SpringHelper;
import org.ambar.core.views.crud.components.lookup.events.DataChangeEvent;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Sample custom control hosting a text field and a button.
 */
public class LookUp extends HBox {

	private String lookupName;
	private String serviceName;

	private LookupSearchTable lookupSearchTable;
	private LookupMetaData lookupMetaData;

	private RequestContext requestContext;
	private DataServices<Object, DTO<Object>, Object, Persistible<Object>> dataService;
	private DictionaryServices dictionaryServices;

	private boolean hasStyle = false;

    @FXML private TextField textField;

    @FXML private Button button;

    private Label descriptionField;

    /**
     * Constructor default.
     * @param pLookupName Nombre del lookup
     * */
	public LookUp(String pLookupName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/lookup.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            this.lookupName = pLookupName;
            this.serviceName = null;

            this.lookupSearchTable = new LookupSearchTable(this);

            this.textField.textProperty().addListener(new ChangeListener<String>() {

				@Override
                public void changed(ObservableValue<? extends String> pArg0, String pArg1, String pArg2) {
					valueChange();
				}
			});

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

	/**
	 * Constructor default.
	 * @param pLookupName Nombre del lookup.
	 * @param pServiceName Nombre del servicio.
	 * */
	public LookUp(String pLookupName, String pServiceName) {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/lookup.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();

            this.lookupName = pLookupName;
            this.serviceName = pServiceName;

            this.lookupSearchTable = new LookupSearchTable(this);

            this.textField.textProperty().addListener(new ChangeListener<String>() {

				@Override
                public void changed(ObservableValue<? extends String> pArg0, String pArg1, String pArg2) {
					valueChange();
				}
			});

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}

	/**
	 * Retorna el texto del lookup (valor).
	 * @return {@link String} Texto del lookup
	 * */
    public String getText() {
        return textProperty().get();
    }

    /**
     * Setea el texto del lookup (valor).
     * @param pValue Valor
     * */
    public void setText(String pValue) {
        textProperty().set(pValue);
        valueChange();
    }

    /**
     * Deshabilita el lookup.
     * @param pDisabled Boolean indicando si esta deshabilitado
     * */
    public void disable(Boolean pDisabled) {
    	this.textField.setDisable(pDisabled);
    	this.button.setDisable(pDisabled);

    	if (this.descriptionField != null) {
	    	if (pDisabled) {
	    		this.descriptionField.getStyleClass().removeAll("lookup-description-active");
	    		this.descriptionField.getStyleClass().add("lookup-description-inactive");
	    	} else {
	    		this.descriptionField.getStyleClass().removeAll("lookup-description-inactive");
	    		this.descriptionField.getStyleClass().add("lookup-description-active");
	    	}
    	}
    }

    /**
     * Setea el estado de edicion del lookup.
     * @param pEditable Setea el estado de edicion del lookup
     * */
    public void editable(Boolean pEditable) {
    	this.textField.setEditable(pEditable);
    	this.button.setDisable(!pEditable);

    	if (this.descriptionField != null) {
	    	if (!pEditable) {
	    		this.descriptionField.getStyleClass().removeAll("lookup-description-active");
	    		this.descriptionField.getStyleClass().add("lookup-description-inactive");
	    	} else {
	    		this.descriptionField.getStyleClass().removeAll("lookup-description-inactive");
	    		this.descriptionField.getStyleClass().add("lookup-description-active");
	    	}
    	}
    }

    /**
	 * @return Retorna el valor del atributo lookupMetaData.
	 */
	public LookupMetaData getLookupMetaData() {
		return lookupMetaData;
	}

	/**
	 * @return the dataService
	 */
	public DataServices<Object, DTO<Object>, Object, Persistible<Object>> getDataService() {
		return dataService;
	}

	/**
	 * @return the dictionaryServices
	 */
	public DictionaryServices getDictionaryServices() {
		return dictionaryServices;
	}

	/**
	 * @return the requestContext
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * @param pLookupMetaData Establece el valor del atributo lookupMetaData.
	 */
	public void setLookupMetaData(LookupMetaData pLookupMetaData) {
		this.lookupMetaData = pLookupMetaData;
	}

	/**
	 * @param pDescriptionField the descriptionField to set
	 */
	public void setDescriptionField(Label pDescriptionField) {
		descriptionField = pDescriptionField;
	}

	/**
	 * Retorna la propiedad text del edit del lookup.
	 * @return {@link StringProperty} Propiedad de texto
	 * */
	public StringProperty textProperty() {
        return textField.textProperty();
    }


    /**
     * Ejecuta el lookup (la grilla).
     * */
	@FXML
    protected void doExecute() {

    	if (this.lookupMetaData == null) {
    		initMetaData();
    		this.lookupSearchTable.initView();
    	}

    	lookupSearchTable.executeSearch();
    }


    /**
     * Inicializa el lookup con su metadata.
     *
     * */
    @SuppressWarnings("unchecked")
	private void initMetaData() {
      this.dictionaryServices = SpringHelper.getContext().getBean(DictionaryServices.class);
      this.lookupMetaData = dictionaryServices.getLookupById(this.lookupName);

      if (this.serviceName == null) {
    	  this.serviceName = this.lookupMetaData.getDefaultService();
      }

      this.requestContext = SpringHelper.getContext().getBean(RequestContext.class);

   	  this.dataService = (DataServices<Object, DTO<Object>, Object, Persistible<Object>>)
		               SpringHelper.getContext().getBean(this.serviceName);

	}

    /**
     * Metodo que se ejecuta cuando se ha seleccionado un nuevo elemento desde la grilla.
     * @param pSelected Elemento seleccionado (DTO)
     * */
	public void newItemSelected(Object pSelected) {
    	DataChangeEvent dataChangeEvent = new DataChangeEvent(pSelected);
    	this.fireEvent(dataChangeEvent);

    	this.textField.getStyleClass().remove("bad");

    	try {
			String value = BeanUtils.getProperty(pSelected, this.lookupMetaData.getResultAttribute());
			this.textField.setText(value);

			if (this.descriptionField != null && this.lookupMetaData.getDescriptionAttribute() != null) {
				value = BeanUtils.getProperty(pSelected, this.lookupMetaData.getDescriptionAttribute());
				this.descriptionField.setText(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	/**
	 * Metodo que se ejecuta cuando cambia el valor del lookup.
	 * */
	public void valueChange() {

		//Si aun no seteo los estilos css
		if (!this.hasStyle) {
	        //Agrega un Skin
			String cssURL = getClass().getResource("/styles/theme.css").toExternalForm();
			this.getScene().getStylesheets().add(cssURL);

			cssURL = getClass().getResource("/styles/style.css").toExternalForm();
			this.getScene().getStylesheets().add(cssURL);

			this.hasStyle = true;
		}


		if (this.lookupMetaData == null) {
    		initMetaData();
    		this.lookupSearchTable.initView();
    	}

		LookupAttribute attribute =
				this.getLookupMetaData().getAttribute(this.getLookupMetaData().getResultAttribute());

		FilteringContext filteringContext = new FilteringContext();

		Filter filter;
		if (!this.textField.getText().equals("")) {
			filter = this.getFilter(attribute.getName(),
					                this.textField.getText(),
					                attribute.getDataType());
		} else {
			filter = this.getFilter(attribute.getName(),
					                "-1",
					                attribute.getDataType());
		}

		filteringContext.setFilter(filter);

		ResultListDTO<DTO<Object>> resultData = this.getDataService().getFilteredList(filteringContext,
                                                                                      this.getRequestContext());

		if (resultData.getCompleteListSize() > 0) {
			this.newItemSelected(resultData.getResultList().get(0));
			this.textField.getStyleClass().removeAll("bad");

		} else {
			if (this.descriptionField != null) {
				this.descriptionField.setText("");
			}

			if (this.textField.getText().equals("")) {
				this.textField.getStyleClass().removeAll("bad");
			} else {
				this.textField.getStyleClass().add("bad");
			}
		}
	}

	/**
	 * Calcula el filtro para el atributo con el tipo y valor especificado en los parametros.
	 * @param pValue Valor
	 * @param pIdAttribute Id del atributo
	 * @param pDataType Tipo de Dato
	 * @return {@link Filter} Filtro
	 * */
	public Filter getFilter(String pIdAttribute, Object pValue, DataType pDataType) {
		Filter filter = null;

		Object aux = castObject(pValue, pDataType);

		switch (pDataType) {
			case String:
				if (aux != null) {
					filter = Criteria.createBinary().property(pIdAttribute).equalTo(aux);
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
	                value = pValue.toString();
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
}
