/**
 * appl-frontend-javafx [14/01/2014 19:49:41]
 */
package org.ambar.core.views.crud.impl;

import java.util.List;
import java.util.Optional;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.ambar.core.be.Persistible;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.dto.DTO;
import org.ambar.core.dto.results.MessageKind;
import org.ambar.core.dto.results.MessageResult;
import org.ambar.core.dto.results.ResultDTO;
import org.ambar.core.dto.results.ResultObjectDTO;
import org.ambar.core.dto.results.ResultVoidDTO;
import org.ambar.core.services.CrudServices;
import org.ambar.core.views.crud.api.AbstractABM;
import org.ambar.core.views.crud.components.menubar.CustomCrudMenuBar;
import org.ambar.core.views.crud.components.statepane.CustomCrudStatePane;
import org.ambar.core.views.crud.components.toolbar.CustomCrudToolBar;
import org.ambar.core.views.crud.components.toolbar.events.ToolBarActionHandler;
import org.ambar.core.views.crud.components.toolbar.events.ToolBarEvent;
import org.apache.commons.lang3.SerializationUtils;
import org.controlsfx.dialog.Dialogs;

/**
 * <p>
 * Clase abstracta padre de los ABM.
 * </p>
 *
 * @author Sebastian
 *
 */
public abstract class AbstractABMImpl<K, D extends DTO<K>, T, E extends Persistible<T>>
	extends BorderPane
	implements AbstractABM<K, D, T, E> {

	private D dtoObject;
	private D dtoBackUp;
	private CrudServices<K, D, T, E> crudServices;
	private RequestContext requestContext;

	private CustomCrudMenuBar menuBar;
	private CustomCrudToolBar toolbar;
	private CustomCrudStatePane statePane;
	private BorderPane centerPane;
	private Stage stage;


	public CrudServices<K, D, T, E> getCrudServices() {
		return crudServices;
	}

	public void setCrudServices(CrudServices<K, D, T, E> crudServices) {
		this.crudServices = crudServices;
	}

	/**
	 * @return the requestContext
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * @param pRequestContext the requestContext to set
	 */
	public void setRequestContext(RequestContext pRequestContext) {
		requestContext = pRequestContext;
	}

	protected Stage getStage() {
		return stage;
	}

	protected D getDtoObject() {
		return dtoObject;
	}

	/**
	 * @return Retorna el valor del atributo statePane.
	 */
	public CustomCrudStatePane getStatePane() {
		return statePane;
	}

	/**
	 * @param pStatePane Establece el valor del atributo statePane.
	 */
	protected void setStatePane(CustomCrudStatePane pStatePane) {
		statePane = pStatePane;
		this.setBottom(pStatePane);
	}


	public void init() {

		this.menuBar = new CustomCrudMenuBar();

		this.toolbar = new CustomCrudToolBar();
		this.toolbar.addEventHandler(ToolBarEvent.TOOLBAREVENTTYPE, new ToolBarActionHandler() {

			@Override
			public void handle(ToolBarEvent pToolBarEvent) {
				if (pToolBarEvent.getActionType() == 1) {
					nuevoRegistro();
				} else if (pToolBarEvent.getActionType() == 2) {
					editarRegistro();
				} else if (pToolBarEvent.getActionType() == 3) {
					eliminarRegistro();
				} else if (pToolBarEvent.getActionType() == 4) {
					guardarCambios();
				} else if (pToolBarEvent.getActionType() == 5) {
					descartarCambios();
				} else if (pToolBarEvent.getActionType() == 6) {
					imprimirRegistro();
				}
			}
		});

		this.centerPane = new BorderPane();

		this.centerPane.setStyle("-fx-background-color: #DEDEDE;");

		//Carga el TOP
		VBox vBox = new VBox();
		vBox.getChildren().add(this.menuBar);
		vBox.getChildren().add(this.toolbar);
		this.setTop(vBox);

		//Carga el CENTER
		this.setCenter(this.centerPane);

		stage = new Stage();
		Scene scene = new Scene(this, 800, 600);
		scene.setRoot(this);
		stage.setScene(scene);

		this.getStage().setTitle(this.getWindowTitle());
		this.getStage().getIcons().add(this.getWindowIcon());

		 //Agrega un Skin
        String cssURL = getClass().getResource("/skins/cruds.css").toExternalForm();
        scene.getStylesheets().add(cssURL);

		this.cargarComponentesABM(this.centerPane);
	}

	@Override
	public void abrirConculta(D pDTO) {
		this.dtoObject = pDTO;
		this.recargarDTOActual();

		this.asignarVistaDesdeObjeto();
		this.setearControlesModoConsulta();
		this.toolbar.setModoConsulta();
		if (!this.getStage().isShowing()) {
			this.getStage().show();
		} else {
			this.getStage().centerOnScreen();
			this.getStage().toFront();
		}
	}


	@Override
	public void abrirEdicion(D pDTO) {
		this.dtoObject = pDTO;
		this.recargarDTOActual();

		this.asignarVistaDesdeObjeto();
		this.setearControlesModoEdicion();
		this.toolbar.setModoEdicion();
		if (!this.getStage().isShowing()) {
			this.getStage().show();
		} else {
			this.getStage().centerOnScreen();
			this.getStage().toFront();
		}
	}

	@Override
	public void nuevoRegistro() {
		this.dtoObject = this.crearNuevoDTO();
		this.asignarVistaDesdeObjeto();
		this.setearControlesModoNuevo();
		if (!this.getStage().isShowing()) {
			this.getStage().show();
		} else {
			this.getStage().centerOnScreen();
			this.getStage().toFront();
		}
	}

	@Override
	public void editarRegistro() {
		this.setearControlesModoEdicion();
		this.toolbar.setModoEdicion();
	}

	@Override
	public void eliminarRegistro() {

		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Eliminar");
		alert.setHeaderText("Eliminar registro");
		alert.setContentText("¿Está seguro que desea eliminar el registro?");

		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertResult.get() == ButtonType.OK) {
			ResultVoidDTO<D> result = this.crudServices.remove(this.dtoObject, this.requestContext);

			if (result.hasErrors()) {
				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Error");
				errorAlert.setHeaderText("Han ocurrido errores al eliminar el registro");
				errorAlert.setContentText(result.getMessages().toString());
				errorAlert.show();

			} else {
				Alert infoAlert = new Alert(AlertType.INFORMATION);
				infoAlert.setTitle("Registro eliminado correctamente");
				infoAlert.setHeaderText("Operacion exitosa");
				infoAlert.setContentText("El registro se ha eliminado correctamente");
				infoAlert.show();

				this.getStage().hide();
			}
		}

	}

	@Override
	public void guardarCambios() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Guardar");
		alert.setHeaderText("Guardar cambios");
		alert.setContentText("¿Está seguro que desea guardar los cambios?");

		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertResult.get() == ButtonType.OK) {
			this.asignarObjetoDesdeVista();

			ResultDTO result;
			if (this.dtoObject.getId() == null) {
				result = this.crudServices.insert(this.dtoObject, this.requestContext);
			} else {
				result = this.crudServices.update(this.dtoObject, this.requestContext);
			}

			if (result.hasErrors()) {

				String messages = "";
				for (MessageResult mr : result.getMessages()) {
					messages = messages + "\n" + mr.getMessage();
				}

				Alert errorAlert = new Alert(AlertType.ERROR);
				errorAlert.setTitle("Error");
				errorAlert.setHeaderText("Han ocurrido errores al guardar el registro");
				errorAlert.setContentText(messages);
				errorAlert.show();
				

			} else {
				this.setearControlesModoConsulta();
				this.toolbar.setModoConsulta();

				
				Alert infoAlert = new Alert(AlertType.INFORMATION);
				infoAlert.setTitle("Registro guardado correctamente");
				infoAlert.setHeaderText("Operacion exitosa");
				infoAlert.setContentText("El registro se ha guardado correctamente");
				infoAlert.show();

				this.recargarDTOActual();
			}
		}
	}


	@Override
	public void descartarCambios() {
		Alert alert = new Alert(AlertType.CONFIRMATION);

		alert.setTitle("Cancelar");
		alert.setHeaderText("Revertir cambios");
		alert.setContentText("¿Está seguro que desea revertir los cambios?");

		Optional<ButtonType> alertResult = alert.showAndWait();
		if (alertResult.get() == ButtonType.OK) {
			this.dtoObject = this.getDTOCopy(dtoBackUp);
			this.recargarDTOActual();
			this.asignarVistaDesdeObjeto();
			this.setearControlesModoConsulta();
			this.toolbar.setModoConsulta();
		}
	}

	protected void setearControlesModoNuevo() {
		this.setearControlesModoEdicion();
		this.toolbar.setModoEdicion();
	}

	@SuppressWarnings("unchecked")
	protected D getDTOCopy(D pDTO) {
		D result = null;
		byte[] aux = SerializationUtils.serialize(pDTO);
		try {
			result = (D) SerializationUtils.deserialize(aux);
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	private String parseErrors(List<MessageResult> pMessages) {
		String result = "";
		for (MessageResult messageResult : pMessages) {
			if (messageResult.getKind().equals(MessageKind.Error)) {
				result = result + "\n" + messageResult.getMessage();
			}
		}
		return result;
	}

	private void recargarDTOActual() {
		ResultObjectDTO<D> dto = this.getCrudServices().getById(this.dtoObject.getId(),
				                                                this.getRequestContext());
		this.dtoObject = dto.getResult();
		this.dtoBackUp = this.getDTOCopy(dtoObject);
	}

	@Override
	public void imprimirRegistro() {
		// Implementar en la subclase
		Alert warningAlert = new Alert(AlertType.WARNING);
		warningAlert.setTitle("Imprimir registro");
		warningAlert.setHeaderText("Imprimir");
		warningAlert.setContentText("No existe un formato para imprimir el registro.");
		warningAlert.show();
		
	}

	protected abstract String getWindowTitle();

	protected abstract Image getWindowIcon();

	protected abstract D crearNuevoDTO();

	protected abstract void cargarComponentesABM(BorderPane pCenterPane);

	protected abstract void setearControlesModoConsulta();

	protected abstract void setearControlesModoEdicion();

	protected abstract void asignarVistaDesdeObjeto();

	protected abstract void asignarObjetoDesdeVista();
}
