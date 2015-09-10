/**
 *
 */
package org.ambar.core.views.crud.components.toolbar;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.ambar.core.views.crud.components.toolbar.events.ToolBarEvent;

/**
 * @author Sebastian
 *
 */
public class CustomCrudToolBar extends ToolBar {

	private Button btnNuevo;
	private Button btnEdicion;
	private Button btnEliminar;
	private Button btnGuardar;
	private Button btnCancelar;
	private Button btnImprimir;

	/**
	 * Constructor default.
	 * @param pABM ABM de la entidad
	 * */
	public CustomCrudToolBar() {

		this.agregarBotonNuevo();
		this.agregarBotonEdicion();
		this.agregarBotonEliminar();
		this.getItems().add(new Separator());
		this.agregarBotonGuardar();
		this.agregarBotonCancelar();
		this.getItems().add(new Separator());
		this.agregarBotonImprimir();

		this.setModoConsulta();
	}

	private void agregarBotonCancelar() {
		this.btnCancelar = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/undo.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnCancelar.setGraphic(imgViewNuevo);
		this.btnCancelar.setTooltip(new Tooltip("Cancelar"));
		this.btnCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				cancelarAction(pActionEvent);
			}
		});
		this.getItems().add(btnCancelar);
	}

	private void agregarBotonGuardar() {
		this.btnGuardar = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/save.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnGuardar.setGraphic(imgViewNuevo);
		this.btnGuardar.setTooltip(new Tooltip("Guardar"));
		this.btnGuardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				guardarAction(pActionEvent);
			}
		});
		this.getItems().add(btnGuardar);
	}

	private void agregarBotonEliminar() {
		this.btnEliminar = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/trash_can.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnEliminar.setGraphic(imgViewNuevo);
		this.btnEliminar.setTooltip(new Tooltip("Eliminar"));
		this.btnEliminar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				eliminarAction(pActionEvent);
			}
		});
		this.getItems().add(btnEliminar);

	}

	private void agregarBotonEdicion() {
		this.btnEdicion = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/edit.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnEdicion.setGraphic(imgViewNuevo);
		this.btnEdicion.setTooltip(new Tooltip("Editar"));
		this.btnEdicion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				edicionAction(pActionEvent);
			}
		});
		this.getItems().add(btnEdicion);

	}

	private void agregarBotonNuevo() {
		this.btnNuevo = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/new_page.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnNuevo.setGraphic(imgViewNuevo);
		this.btnNuevo.setTooltip(new Tooltip("Nuevo"));
		this.btnNuevo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				nuevoAction(pActionEvent);
			}
		});
		this.getItems().add(btnNuevo);
	}

	private void agregarBotonImprimir() {
		this.btnImprimir = new Button();
		Image imgNuevo = new Image(getClass().getResourceAsStream("/images/toolbar/printer.png"));
		ImageView imgViewNuevo = new ImageView(imgNuevo);
		this.btnImprimir.setGraphic(imgViewNuevo);
		this.btnImprimir.setTooltip(new Tooltip("Imprimir"));
		this.btnImprimir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent pActionEvent) {
				imprimirAction(pActionEvent);
			}
		});
		this.getItems().add(btnImprimir);
	}

	public void setModoEdicion(){
		this.btnNuevo.setDisable(true);
		this.btnEdicion.setDisable(true);
		this.btnEliminar.setDisable(true);
		this.btnGuardar.setDisable(false);
		this.btnCancelar.setDisable(false);
		this.btnImprimir.setDisable(true);
	}

	public void setModoConsulta(){
		this.btnNuevo.setDisable(false);
		this.btnEdicion.setDisable(false);
		this.btnEliminar.setDisable(false);
		this.btnGuardar.setDisable(true);
		this.btnCancelar.setDisable(true);
		this.btnImprimir.setDisable(false);
	}

	protected void nuevoAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONNEW);
		this.fireEvent(toolBarEvent);
	}

	protected void edicionAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONEDIT);
		this.fireEvent(toolBarEvent);
	}

	protected void eliminarAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONDELETE);
		this.fireEvent(toolBarEvent);
	}

	protected void guardarAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONSAVE);
		this.fireEvent(toolBarEvent);
	}

	protected void cancelarAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONCANCEL);
		this.fireEvent(toolBarEvent);
	}

	protected void imprimirAction(ActionEvent event){
		ToolBarEvent toolBarEvent = new ToolBarEvent(ToolBarEvent.BUTTONPRINT);
		this.fireEvent(toolBarEvent);
	}

}
