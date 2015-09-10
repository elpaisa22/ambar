/**
 *
 */
package org.ambar.core.views.crud.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import jidefx.scene.control.field.NumberField;

import org.ambar.core.be.Persistible;
import org.ambar.core.dictionary.domain.entities.Attribute;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.entities.FieldType;
import org.ambar.core.dictionary.domain.lookups.LookupMetaData;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.TrackingableDTO;
import org.ambar.core.enums.EnumType;
import org.ambar.core.spring.SpringHelper;
import org.ambar.core.views.crud.components.lookup.ComboBoxElement;
import org.ambar.core.views.crud.components.lookup.LookUp;
import org.ambar.core.views.crud.components.statepane.CustomCrudStatePane;
import org.ambar.core.views.main.components.numericfield.IntegerField;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Sebastian
 *
 */
public class AutomaticABMImpl extends AbstractABMImpl<Object, DTO<Object>, Object, Persistible<Object>> {

	private String entityName;
	private EntityInfo entityInfo;
	private Class<Object> dtoClass;

	private Map<String, Node> components;
	private Map<String, Attribute> attributes;
	private Map<Integer, List<Attribute>> componentslayout;

	/**
	 * @return the entityName
	 */
	public String getEntityName() {
		return entityName;
	}

	/**
	 * @param pEntityName the entityName to set
	 */
	public void setEntityName(String pEntityName) {
		entityName = pEntityName;
	}

	/**
	 * @return the dtoClass
	 */
	public Class<Object> getDtoClass() {
		return dtoClass;
	}

	/**
	 * @param pDtoClass the dtoClass to set
	 */
	public void setDtoClass(Class<Object> pDtoClass) {
		dtoClass = pDtoClass;
	}

	@Override
	public void init() {

		this.components = new HashMap<String, Node>();
		this.attributes = new HashMap<String, Attribute>();
		this.componentslayout = new HashMap<Integer, List<Attribute>>();

		this.entityInfo =
				SpringHelper.getContext().getBean(DictionaryServices.class)
				                         .getNavigationEntityInfoById(this.entityName);

		Entity entity = SpringHelper.getContext().getBean(DictionaryServices.class)
				                                 .getEntityMetaDataById(this.entityName);

		for (Attribute attribute : entity.getAttributes()) {
			this.attributes.put(attribute.getId(), attribute);
		}

		this.createComponentsLayout(this.attributes.values());

		super.init();

		//Si la entidad tiene campos de tracking carga el
		if (this.crearNuevoDTO() instanceof TrackingableDTO) {
			CustomCrudStatePane statePane = new CustomCrudStatePane();
			this.setStatePane(statePane);
		}
	}

	private void createComponentsLayout(Collection<Attribute> pValues) {
		for (Attribute attribute : pValues) {
			Integer order = attribute.getOrder();
			if (order != null) {
				if (this.componentslayout.containsKey(order)) {
					this.componentslayout.get(order).add(attribute);
				} else {
					ArrayList<Attribute> list = new ArrayList<Attribute>();
					list.add(attribute);
					this.componentslayout.put(order, list);
				}
			}
		}
	}

	@Override
	protected String getWindowTitle() {
		return this.entityInfo.getName();
	}

	@Override
	protected Image getWindowIcon() {
		return new Image(this.getClass().getResourceAsStream("/images/" + this.entityInfo.getLargeImage()));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected DTO<Object> crearNuevoDTO() {
		try {
			return (DTO<Object>) dtoClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void cargarComponentesABM(BorderPane pCenterPane) {
		Iterator<Integer> orderIterator = this.componentslayout.keySet().iterator();

		GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 10, 10, 30));
        gridPane.setHgap(30);
        gridPane.setVgap(10);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setFillWidth(true);
        column1.setMaxWidth(130);
        column1.setMinWidth(130);
        column1.setPrefWidth(130);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setFillWidth(true);
        column2.setPrefWidth(170);
        column2.setMinWidth(170);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setFillWidth(true);
        column3.setPrefWidth(170);
        column3.setMinWidth(170);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setFillWidth(true);
        column4.setPrefWidth(170);
        column4.setMinWidth(170);

        gridPane.getColumnConstraints().add(column1);
        gridPane.getColumnConstraints().add(column2);
        gridPane.getColumnConstraints().add(column3);
        gridPane.getColumnConstraints().add(column4);

        int rowIndex = 0;

		while (orderIterator.hasNext()) {

			Integer order = orderIterator.next();
			List<Attribute> elements = this.componentslayout.get(order);

			if (elements.size() == 1) {
				Attribute attribute = elements.get(0);
				Label label = new Label(attribute.getVisibleName());
				label.setStyle("-fx-font-weight: bold;");

				gridPane.add(label, 0, rowIndex, 1, 1);

				Node component = this.getEditComponent(attribute);

				if (component instanceof LookUp) {

					gridPane.add(component, 1, rowIndex, 1, 1);

					LookUp lookup = (LookUp) component;
                    LookupMetaData lkpMetadata = SpringHelper.getContext().getBean(DictionaryServices.class)
                                                                          .getLookupById(attribute.getLookupName());

					if (lkpMetadata.getAttributeDescription() != null) {
						Label descLabel = new Label(attribute.getVisibleName());
						descLabel.setText("--");
						descLabel.setStyle("-fx-font-weight: bold;");

						lookup.setDescriptionField(descLabel);
						gridPane.add(descLabel, 2, rowIndex, 1, 1);
					}
				} else if (component instanceof DatePicker
						|| component instanceof ComboBox
						|| component instanceof IntegerField
						|| DataType.Number.equals(attribute.getDataType())
						|| DataType.Integer.equals(attribute.getDataType())) {
					gridPane.add(component, 1, rowIndex, 1, 1);
				} else {
					gridPane.add(component, 1, rowIndex, 3, 1);
				}

				this.components.put(attribute.getId(), component);

				rowIndex++;

			} else {
				int colIndex = 0;
				for (Attribute attribute : elements) {

					Label label = new Label(attribute.getVisibleName());
					label.setStyle("-fx-font-weight: bold;");

					Node component = this.getEditComponent(attribute);

					gridPane.add(label, colIndex, rowIndex, 1, 1);
					colIndex++;
					gridPane.add(component, colIndex, rowIndex, 1, 1);
					colIndex++;

					this.components.put(attribute.getId(), component);

					if (colIndex == 4) {
						colIndex = 0;
						rowIndex++;
					}
				}

				if (colIndex == 2) {
					rowIndex++;
				}
			}
		}
		pCenterPane.setCenter(gridPane);
	}

	@SuppressWarnings("rawtypes")
	private Node getEditComponent(Attribute pAttribute) {
		Node result = null;

		if (pAttribute.getLookupName() != null) {
			LookUp lookup = new LookUp(pAttribute.getLookupName());
			result = lookup;
			if (!pAttribute.getEditable()) {
				lookup.disable(true);
			}
		} else {
			switch (pAttribute.getDataType()) {
				case DateTime:
					result = new DatePicker();
					break;
				case Integer:
					IntegerField intComponent = new IntegerField();
					if (pAttribute.getFieldType().equals(FieldType.PRIMARYKEY)) {
						intComponent.setEditable(false);
					}
					result = intComponent;
					break;
				case Enum:
					Class type = pAttribute.getEnumType();
					if (type == null) {
						result = new TextField();
					} else {
						ComboBox<ComboBoxElement> comboBox = new ComboBox<ComboBoxElement>();
						Object[] enumList = type.getEnumConstants();
						for (Object value : enumList) {
							EnumType enumValue = (EnumType) value;
							ComboBoxElement elem = new ComboBoxElement(enumValue.getValor(),
                                                                       enumValue.getDescripcion());
							comboBox.getItems().add(elem);
						}
						result = comboBox;
					}
					break;
				case String:
					TextField strComponent = new TextField();
					if (pAttribute.getFieldType().equals(FieldType.PRIMARYKEY)) {
						strComponent.setEditable(false);
					}
					result = strComponent;
					break;
				case Number:
					IntegerField numComponent = new IntegerField();
					if (pAttribute.getFieldType().equals(FieldType.PRIMARYKEY)) {
						numComponent.setEditable(false);
					}
					result = numComponent;
					break;
				case Money:
					NumberField numberField = new NumberField();
					Locale locale = Locale.getDefault();
                    DecimalFormat currencyFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);
                    currencyFormat.setMinimumIntegerDigits(1);
                    currencyFormat.setMaximumIntegerDigits(9);
                    currencyFormat.setMinimumFractionDigits(2);
                    currencyFormat.setMaximumFractionDigits(2);

                    numberField.setDecimalFormat(currencyFormat);

					result = numberField;
					break;
				case Boolean:
					result = new CheckBox();
					break;
				default:
					break;
			}

		}

		return result;
	}

	@Override
	protected void setearControlesModoConsulta() {

		Set<String> keys = this.components.keySet();
		for (String idComp : keys) {
			Node component = (Node) this.components.get(idComp);
			if (component instanceof LookUp) {
				LookUp lookUp = (LookUp) component;
				lookUp.disable(true);
			} else {
				component.setDisable(true);
			}
		}
	}

	@Override
	protected void setearControlesModoEdicion() {

		Set<String> keys = this.components.keySet();
		for (String idComp : keys) {
			Node component = (Node) this.components.get(idComp);
			if (component instanceof LookUp) {
				Attribute attribute = this.attributes.get(idComp);
				LookUp lookUp = (LookUp) component;
				if (attribute.getEditable()) {
					lookUp.disable(false);
				}
			} else {
				component.setDisable(false);
			}
		}
	}

	@Override
	protected void asignarVistaDesdeObjeto() {

		Set<String> keys = this.components.keySet();
		for (String idComp : keys) {
			Node component = (Node) this.components.get(idComp);
			Object value = this.getObjectValue(idComp, this.getDtoObject());
			this.setComponentValue(component, value, this.attributes.get(idComp));
		}

		if (this.getDtoObject() != null && this.getDtoObject() instanceof TrackingableDTO) {
			this.getStatePane().loadFromDTO((TrackingableDTO) this.getDtoObject());
		}
	}

	private Object getObjectValue(String pProperty, DTO<Object> pDtoObject) {
			try {
				return PropertyUtils.getSimpleProperty(pDtoObject, pProperty);
			} catch (Exception e) {
			}

			return null;
	}

	@Override
	protected void asignarObjetoDesdeVista() {

		Set<String> keys = this.components.keySet();
		for (String idComp : keys) {
			Node component = (Node) this.components.get(idComp);

			Object value = this.getComponentValue(component, this.attributes.get(idComp).getDataType());
			this.setObjectValue(this.getDtoObject(), idComp, value);
		}

	}

	@SuppressWarnings("unchecked")
	private Object getComponentValue(Node pComponent, DataType pDataType) {
		Object result = null;

		if (pComponent instanceof DatePicker) {

			if (((DatePicker) pComponent).getValue() != null) {
				//Convierte un LocalDate a un Date
				LocalDate localDate = ((DatePicker) pComponent).getValue();
				Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				result = Date.from(instant);
			}

		} else if (pComponent instanceof NumberField) {

			Object value = ((NumberField) pComponent).getValue();
			if (value != null) {
				if (value instanceof Long) {
					result = new BigDecimal((Long) value);
				} else if (value instanceof BigDecimal) {
					result = value;
				} else if (value instanceof Double) {
					result = new BigDecimal((Double) value);
				}
			} else {
				result = null;
			}

		} else if (pComponent instanceof TextField) {
			TextField textField = (TextField) pComponent;

			if (!textField.getText().equals("")) {
				switch (pDataType) {
					case Integer:
						result = Integer.parseInt(textField.getText());
						break;
					case String:
						result = textField.getText();
						break;
					case Number:
						result = Long.parseLong(textField.getText());
						break;

					default:
						break;
				}
			}
		} else if (pComponent instanceof ComboBox) {
			ComboBox<ComboBoxElement> comboBox = (ComboBox<ComboBoxElement>) pComponent;
			if (comboBox.getValue() != null) {
				result = comboBox.getValue().getValue();
			}
		} else if (pComponent instanceof LookUp) {
			String text = ((LookUp) pComponent).getText();
			if (!text.equals("")) {
				switch (pDataType) {
					case Integer:
						result = Integer.parseInt(text);
						break;
					case String:
						result = text;
						break;
					case Number:
						result = Long.parseLong(text);
						break;

					default:
						break;
				}
			}
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private void setComponentValue(Node pComponent, Object pValue, Attribute pAttribute) {

			if (pComponent instanceof DatePicker) {
				if (pValue != null) {

					//Castea de Date a LocalDate
					Date date = (Date) pValue;
					Instant instant = Instant.ofEpochMilli(date.getTime());
                    LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();

					((DatePicker) pComponent).setValue(res);
				} else {
					((DatePicker) pComponent).setValue(null);
				}

			} else if (pComponent instanceof NumberField) {
				if (pValue != null) {
					((NumberField) pComponent).setValue((BigDecimal) pValue);
				} else {
					((NumberField) pComponent).setText(null);
				}

			} else if (pComponent instanceof TextField) {
				TextField textField = (TextField) pComponent;
				if (pValue != null) {
					textField.setText(pValue.toString());
				} else {
					textField.setText("");
				}

			} else if (pComponent instanceof ComboBox) {
				ComboBox<ComboBoxElement> comboBox = (ComboBox<ComboBoxElement>) pComponent;
				if (pValue != null) {
					EnumType enumVal = (EnumType) Enum.valueOf(pAttribute.getEnumType(),
                                                               pValue.toString().trim().toUpperCase());
					ComboBoxElement comboBoxElement = new ComboBoxElement(enumVal.getValor(),
                                                                          enumVal.getDescripcion());

					comboBox.setValue(comboBoxElement);
				} else {
					comboBox.setValue(null);
				}
			} else if (pComponent instanceof LookUp) {
				LookUp lookUp = (LookUp) pComponent;
				if (pValue != null) {
					lookUp.setText(pValue.toString());
				} else {
					lookUp.setText("");
				}
			}

	}

	private void setObjectValue(Object pObject, String pProperty, Object pValue) {

		try {
			PropertyUtils.setProperty(pObject, pProperty, pValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
