/**
 *
 */
package org.ambar.core.views.main.components.grid;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.dictionary.domain.entities.Attribute;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.entities.Entity;
import org.ambar.core.dictionary.domain.navigation.EntityInfo;
import org.ambar.core.dictionary.services.DictionaryServices;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultListDTO;
import org.ambar.core.enums.EnumType;
import org.ambar.core.services.DataServices;
import org.ambar.core.spring.SpringHelper;
import org.ambar.core.views.main.navigation.NavigationManager;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Sebastian
 *
 */
public class GridLoaderImpl implements GridLoader {

	private static final double PREFWITH = 100;

	private NavigationManager navigationManager;
	private DictionaryServices dictionaryServices;
	private TableView<Object> tableView;

	/**
	 * @return the navigationManager
	 */
	public NavigationManager getNavigationManager() {
		return navigationManager;
	}

	/**
	 * @param pNavigationManager the navigationManager to set
	 */
	public void setNavigationManager(NavigationManager pNavigationManager) {
		navigationManager = pNavigationManager;
	}

	/**
	 * @return the dictionaryServices
	 */
	public DictionaryServices getDictionaryServices() {
		return dictionaryServices;
	}

	/**
	 * @param pDictionaryServices the dictionaryServices to set
	 */
	public void setDictionaryServices(DictionaryServices pDictionaryServices) {
		dictionaryServices = pDictionaryServices;
	}

	/**
	 * @return the tableView
	 */
	public TableView<Object> getTableView() {
		return tableView;
	}

	/**
	 * @param pTableView the tableView to set
	 */
	public void setTableView(TableView<Object> pTableView) {
		tableView = pTableView;
	}

	@Override
	public Boolean loadTableView(String pEntityId, Filter pFilter) {
		Boolean hasData = false;

		//Carga las columnas visibles
		Entity metadata = dictionaryServices.getEntityMetaDataById(pEntityId);

		tableView.getColumns().clear();

		for (final Attribute attribute : metadata.getAttributes()) {

			if (DataType.DateTime.equals(attribute.getDataType())) {
                TableColumn<Object, Date> column = new TableColumn<Object, Date>(attribute.getVisibleName());
				column.setCellValueFactory(getDateCellValueFactory(attribute));

				if (attribute.getWidth() != null) {
					column.setPrefWidth(attribute.getWidth());
				} else {
					column.setPrefWidth(PREFWITH);
				}

				column.setCellFactory(getDateCellFactory());

				column.setResizable(false);

				tableView.getColumns().add(column);

			} else if (DataType.Enum.equals(attribute.getDataType())) {

                TableColumn<Object, String> column = new TableColumn<Object, String>(attribute.getVisibleName());
				column.setCellValueFactory(getEnumCellValueFactory(attribute));

				if (attribute.getWidth() != null) {
					column.setPrefWidth(attribute.getWidth());
				} else {
					column.setPrefWidth(PREFWITH);
				}

				column.setResizable(false);

				tableView.getColumns().add(column);

			} else {
                TableColumn<Object, String> column = new TableColumn<Object, String>(attribute.getVisibleName());
				column.setCellValueFactory(getDefaultCellValueFactory(attribute));

				if (attribute.getWidth() != null) {
					column.setPrefWidth(attribute.getWidth());
				} else {
					column.setPrefWidth(PREFWITH);
				}

				column.setResizable(false);

				tableView.getColumns().add(column);
			}

		}

		//Carga los datos de la entidad
		EntityInfo navInfo = dictionaryServices.getNavigationEntityInfoById(pEntityId);
		String serviceName = navInfo.getService();

		@SuppressWarnings("unchecked")
		DataServices<Object, DTO<Object>, Object, Persistible<Object>> service =
				(DataServices<Object, DTO<Object>, Object, Persistible<Object>>)
							SpringHelper.getContext().getBean(serviceName);

		RequestContext context = SpringHelper.getContext().getBean(RequestContext.class);

		FilteringContext filteringContext = new FilteringContext(pFilter, null);

		ResultListDTO<DTO<Object>> resultData = service.getFilteredList(filteringContext, context);

		tableView.getItems().clear();
		if (resultData.getResultList() != null && resultData.getResultList().size() > 0) {
			tableView.getItems().addAll(resultData.getResultList());
			tableView.getSelectionModel().selectFirst();
			hasData = true;
		}


		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent pMouseEvent) {
		        if (pMouseEvent.getButton().equals(MouseButton.PRIMARY)) {
		            if (pMouseEvent.getClickCount() == 2) {
		                navigationManager.openCrud(tableView.getSelectionModel().getSelectedItem());
		            }
		        }
		    }
		});


		tableView.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent pEvent) {
				if (pEvent.getCode().equals(KeyCode.ENTER)) {
					navigationManager.openCrud(tableView.getSelectionModel().getSelectedItem());
				}
			}
		});

		return hasData;
	}

	/**
	 * Crea una celda para los tipos de datos Date o fecha.
	 * @return {@link Callback} callback de resultado
	 * */
	private Callback<TableColumn<Object, Date>, TableCell<Object, Date>> getDateCellFactory() {
		return 	new Callback<TableColumn<Object, Date>, TableCell<Object, Date>>() {
            @Override
			public TableCell<Object, Date> call(TableColumn<Object, Date> pParam) {
			      return new TableCell<Object, Date>() {

			          @Override
			          protected void updateItem(Date pItem, boolean pIsEmpty) {
			              super.updateItem(pItem, pIsEmpty);


			              if (!pIsEmpty) {
			                // Use a SimpleDateFormat or similar in the format method
			            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			                setText(dateFormat.format(pItem));

			              } else {
			                setText(null);
			              }
			          }
			      };
			  }
			};
	}

	/**
	 * Obtiene el valor de una celda con valor "comun" para el atributo que recibe por parametro.
	 * @param pAttribute Atributo de la clase
	 * @return {@link Callback} callback de resultado
	 * */
	private Callback<CellDataFeatures<Object, String>, ObservableValue<String>> getDefaultCellValueFactory(
			final Attribute pAttribute) {

		return new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
                public ObservableValue<String> call(CellDataFeatures<Object, String> pCellDataFeatures) {
			    	try {
                        return new ReadOnlyObjectWrapper(PropertyUtils.getProperty(pCellDataFeatures.getValue(),
                                                                                   pAttribute.getId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
			    		return null;
			    }
			};
	}

	/**
	 * Obtiene el valor de una celda con valor Date para el atributo que recibe por parametro.
	 * @param pAttribute Atributo de la clase
	 * @return {@link Callback} callback de resultado
	 * */
	private Callback<CellDataFeatures<Object, Date>, ObservableValue<Date>> getDateCellValueFactory(
			final Attribute pAttribute) {

		return new Callback<CellDataFeatures<Object, Date>, ObservableValue<Date>>() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public ObservableValue<Date> call(CellDataFeatures<Object, Date> pCellDataFeatures) {
			    	try {
                        return new ReadOnlyObjectWrapper(PropertyUtils.getProperty(pCellDataFeatures.getValue(),
                                                                                   pAttribute.getId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
			    		return null;
			    }
			};
	}

	/**
	 * Obtiene el valor de una celda con valor Emun para el atributo que recibe por parametro.
	 * @param pAttribute Atributo de la clase
	 * @return {@link Callback} callback de resultado
	 * */
	private Callback<CellDataFeatures<Object, String>, ObservableValue<String>> getEnumCellValueFactory(
			final Attribute pAttribute) {

		return new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public ObservableValue<String> call(CellDataFeatures<Object, String> pParam) {
			    	try {
                        String strValue = (String) BeanUtils.getProperty(pParam.getValue(), pAttribute.getId());

			    		Object value;
			    		if (pAttribute.getEnumType() != null) {
                            EnumType enumValue = (EnumType) Enum.valueOf(pAttribute.getEnumType(), strValue);
				    		value = enumValue.getDescripcion();
			    		} else {
                            value = PropertyUtils.getProperty(pParam.getValue(), pAttribute.getId());
			    		}
						return new ReadOnlyObjectWrapper(value);
					} catch (Exception e) {
						e.printStackTrace();
					}

			    	return null;
			    }
			};
	}

}
