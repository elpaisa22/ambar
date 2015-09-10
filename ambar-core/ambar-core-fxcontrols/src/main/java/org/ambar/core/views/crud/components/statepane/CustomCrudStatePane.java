/**
 * 
 */
package org.ambar.core.views.crud.components.statepane;

import java.text.SimpleDateFormat;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.dto.TrackingableDTO;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * <p>
 * Muestra la informacion de tracking del DTO.
 * </p>
 * @author Sebastian
 *
 */
public class CustomCrudStatePane extends GridPane {

	private Label txtUsuario;
	private Label txtFecModificacion;
	private Label txtFecCreacion;
	private Label txtEstado;

	private Label fldUsuario;
	private Label fldFecModificacion;
	private Label fldFecCreacion;
	private Label fldEstado;
	private SimpleDateFormat dateFormat;

	/**
	 * Constructor por default.
	 * */
	public CustomCrudStatePane() {

		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(20);
		this.setVgap(20);

		this.txtUsuario = new Label("USUARIO:");
		this.txtUsuario.setStyle("-fx-font-weight: bold;");
		this.txtFecModificacion = new Label("FEC. MODIFICACION:");
		this.txtFecModificacion.setStyle("-fx-font-weight: bold;");
		this.txtFecCreacion = new Label("FEC. CREACION:");
		this.txtFecCreacion.setStyle("-fx-font-weight: bold;");
		this.txtEstado = new Label("ESTADO:");
		this.txtEstado.setStyle("-fx-font-weight: bold;");

		this.fldUsuario = new Label();
		this.fldFecModificacion = new Label();
		this.fldFecCreacion = new Label();
		this.fldEstado = new Label();

		this.add(txtUsuario, 0, 0);
		this.add(fldUsuario, 1, 0);
		this.add(txtFecModificacion, 2, 0);
		this.add(fldFecModificacion, 3, 0);
		this.add(txtFecCreacion, 4, 0);
		this.add(fldFecCreacion, 5, 0);
		this.add(txtEstado, 6, 0);
		this.add(fldEstado, 7, 0);

		this.dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	/**
	 * Carga la informacion de Tracking.
	 * @param pDtoObject Objeto DTO
	 * */
	public void loadFromDTO(TrackingableDTO pDtoObject) {
		if (pDtoObject.getTrackInfo() != null) {
			this.fldUsuario.setText(pDtoObject.getTrackInfo().getUsuario());
			if (pDtoObject.getTrackInfo().getFechaModificacion() != null) {
				this.fldFecModificacion.setText(
						dateFormat.format(pDtoObject.getTrackInfo().getFechaModificacion()));
			} else {
				this.fldFecModificacion.setText("--");
			}
			this.fldFecCreacion.setText(dateFormat.format(pDtoObject.getTrackInfo().getFechaCreacion()));
			this.fldEstado.setText(
                    EstadoTracking.fromValue(pDtoObject.getTrackInfo().getEstado()).getDescription());
		} else {
			this.fldUsuario.setText("--");
            this.fldFecModificacion.setText("--");
			this.fldFecCreacion.setText("--");
			this.fldEstado.setText("--");
		}
	}

}
