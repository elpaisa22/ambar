/**
 *
 */
package org.ambar.core.views.crud.components.menubar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * @author Sebastian
 *
 */
public class CustomCrudMenuBar extends MenuBar {

	private Menu menuArchivo;
	private Menu menuEdicion;

	private MenuItem menuItemGuardar;
	private MenuItem menuItemSalir;

	/**
	 * Constructor default.
	 * */
	public CustomCrudMenuBar() {

		menuArchivo = new Menu("Archivo");
		menuItemGuardar = new MenuItem("Guardar");
		menuItemSalir = new MenuItem("Salir");
		menuArchivo.getItems().add(menuItemGuardar);
		menuArchivo.getItems().add(menuItemSalir);
		this.getMenus().add(menuArchivo);

		menuEdicion = new Menu("Edicion");
		this.getMenus().add(menuEdicion);
	}

}
