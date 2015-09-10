/**
 * 
 */
package org.ambar.core.views.crud.components.lookup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.ambar.core.commons.filters.Filter;
import org.ambar.core.commons.filters.FilteringContext;
import org.ambar.core.dictionary.domain.entities.DataType;
import org.ambar.core.dictionary.domain.lookups.LookupAttribute;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.ResultListDTO;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Ecclesia
 *
 */
public class LookupSearchTable extends Stage {

	TextField searchText;
	ChoiceBox<ComboBoxElement> fieldFilter;
	
    private TableView<Object> tableView;
    
    private LookUp lookup;

    /**
     * Constructor default.
     * */
    public LookupSearchTable(LookUp pLookup) {
    	this.lookup = pLookup;
	}
    
    
    private void fillSearchAttributes() {
    	this.fieldFilter = new ChoiceBox<ComboBoxElement>();        

    	List<LookupAttribute> displayColumns = this.lookup.getLookupMetaData().getDisplayAttributes();

		for (LookupAttribute attribute : displayColumns) {
			ComboBoxElement filterAttribute = new ComboBoxElement(attribute.getName(), attribute.getDescription());
			this.fieldFilter.getItems().add(filterAttribute);
		}
	}


	private void fillTableView() {
		
		this.tableView = new TableView<Object>();

		List<LookupAttribute> displayColumns = this.lookup.getLookupMetaData().getDisplayAttributes();

		for (LookupAttribute attribute : displayColumns) {

			if (DataType.DateTime.equals(attribute.getDataType())) {
				TableColumn<Object, Date> column = new TableColumn<Object, Date>(attribute.getDescription());
				column.setCellValueFactory(getDateCellValueFactory(attribute));
				
				column.setCellFactory(getDateCellFactory());

				tableView.getColumns().add(column);

			} else {
				TableColumn<Object, String> column = new TableColumn<Object, String>(attribute.getDescription());
				column.setCellValueFactory(getDefaultCellValueFactory(attribute));
		
				if (attribute.getWidth() != 0) {
					column.setPrefWidth(attribute.getWidth());
				} else {
					column.setPrefWidth(200);
				}

				tableView.getColumns().add(column);
			}
		}
		
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                returnSelectedValue();
		            }
		        }
		    }
		});
		
		
		tableView.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent pEvent) {
				if (pEvent.getCode().equals(KeyCode.ENTER)) {
					returnSelectedValue();
				}
				
			}
		});

	}
	
	private void filterTableView() {
		ComboBoxElement filterAttribute = this.fieldFilter.getValue();
		
		FilteringContext filteringContext = new FilteringContext();

		if (filterAttribute != null) {

			LookupAttribute attribute = this.lookup.getLookupMetaData().getAttribute(filterAttribute.getValue().toString());

			Filter filter;
			if (!this.searchText.getText().equals("")) {
				filter = this.lookup.getDictionaryServices().getFilter(attribute.getName(), this.searchText.getText(), attribute.getDataType());
			} else {
				filter = this.lookup.getDictionaryServices().getFilter(attribute.getName(), "-1", attribute.getDataType());
			}
			
			filteringContext.setFilter(filter);

			ResultListDTO<DTO<Object>> resultData = this.lookup.getDataService().getFilteredList(filteringContext,
					                                                                             this.lookup.getRequestContext());
			
			this.tableView.getItems().clear();
			
			if (resultData.getCompleteListSize() > 0) {
				this.tableView.getItems().addAll(resultData.getResultList());
			}

		} else {
			ResultListDTO<DTO<Object>> resultData = this.lookup.getDataService().getFilteredList(filteringContext,
					                                                                             this.lookup.getRequestContext());
			
			this.tableView.getItems().clear();
			if (resultData.getResultList() != null && resultData.getResultList().size() > 0) {
				this.tableView.getItems().addAll(resultData.getResultList());
				this.tableView.getSelectionModel().selectFirst();
			}
		}
		
	}


	private void returnSelectedValue() {
		if (!this.tableView.getSelectionModel().isEmpty()) {
			this.lookup.newItemSelected(this.tableView.getSelectionModel().getSelectedItem());
		}
		this.hide();		
	}
	
	private void closeWindow() {
		this.hide();
	}
	
	public void executeSearch() {
		
    	if (!this.isShowing()) {
    		this.show();
    	}

    }


	public void initView() {

		this.setTitle(this.lookup.getLookupMetaData().getTitle());

    	this.searchText = new TextField();
    	
        VBox vb = new VBox();
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setSpacing(10);

        Scene scene = new Scene(vb, 700, 400, Color.WHITE);
        
        //Agrega un Skin
	    String cssURL = getClass().getResource("/styles/theme.css").toExternalForm();
	    scene.getStylesheets().add(cssURL);
	
	    cssURL = getClass().getResource("/styles/style.css").toExternalForm();
	    scene.getStylesheets().add(cssURL);

	    this.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/lookup/search.png")));

        Label searchLabel = new Label("Valor:");

        HBox searchBox = new HBox();
        searchBox.setSpacing(5);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        searchBox.getChildren().addAll(searchLabel, searchText);


        this.fillSearchAttributes();

        Label criteriaLabel = new Label("Atributo:");

        HBox criteriaBox = new HBox();
        criteriaBox.setSpacing(5);
        criteriaBox.setAlignment(Pos.CENTER_LEFT);

        criteriaBox.getChildren().addAll(criteriaLabel, fieldFilter);

        Button searchButton = new Button();
        searchButton.setText("Buscar");

        Button btnClear = new Button();
        btnClear.setText("Limpiar");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                tableView.getItems().clear();
                fieldFilter.getSelectionModel().clearSelection();
            }

        });

        HBox hbButtonBox = new HBox();
        hbButtonBox.setPadding(new Insets(5, 0, 0, 0));

        hbButtonBox.setSpacing(10);

        hbButtonBox.getChildren().addAll(searchBox, criteriaBox, searchButton, btnClear);

        searchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pEvent) {
				filterTableView();
			}

        });

        this.setScene(scene);
        this.initModality(Modality.APPLICATION_MODAL);
        
        
        this.fillTableView();
        
        Button selectButton = new Button();
        selectButton.setText("Aceptar");
        selectButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pEvent) {
				returnSelectedValue();
			}

        });

        Button cancelButton = new Button();
        cancelButton.setText("Cancelar");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pEvent) {
				closeWindow();
			}

        });
        
        HBox lkpButtonsBox = new HBox();
        lkpButtonsBox.setSpacing(5);
        lkpButtonsBox.setAlignment(Pos.CENTER_RIGHT);
        
        lkpButtonsBox.getChildren().add(selectButton);
        lkpButtonsBox.getChildren().add(cancelButton);

        vb.getChildren().addAll(hbButtonBox, tableView, lkpButtonsBox);
		
	}


	private Callback<TableColumn<Object, Date>, TableCell<Object, Date>> getDateCellFactory() {
		return 	new Callback<TableColumn<Object, Date>, TableCell<Object, Date>>() {
            @Override
			public TableCell<Object, Date> call(TableColumn<Object, Date> param) {
			      return new TableCell<Object, Date>() {
			
			          @Override
			          protected void updateItem(Date item, boolean empty) {
			              super.updateItem(item, empty);
			              
	              
			              if (!empty) {
			                // Use a SimpleDateFormat or similar in the format method
			            	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			            	 
			                setText(dateFormat.format(item));
		
			              } else {
			                setText(null);
			              }
			          }
			      };
			  }
			};
	}

	private Callback<CellDataFeatures<Object, String>, ObservableValue<String>> getDefaultCellValueFactory(final LookupAttribute pAttribute) {
		return new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {
			
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public ObservableValue<String> call(CellDataFeatures<Object, String> p) {
			    	try {
			    		return new ReadOnlyObjectWrapper(PropertyUtils.getProperty(p.getValue(), pAttribute.getName()));
					} catch (Exception e) {						
					}
			    		return null;
			    }
			};
	}

	private Callback<CellDataFeatures<Object, Date>, ObservableValue<Date>> getDateCellValueFactory(final LookupAttribute pAttribute) {
		return new Callback<CellDataFeatures<Object, Date>, ObservableValue<Date>>() {
			
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public ObservableValue<Date> call(CellDataFeatures<Object, Date> p) {
			    	try {
			    		return new ReadOnlyObjectWrapper(PropertyUtils.getProperty(p.getValue(), pAttribute.getName()));
					} catch (Exception e) {						
					}
			    		return null;
			    }
			};
	}

}
