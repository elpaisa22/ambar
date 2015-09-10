/**
 * ambar-core-jsfcomponents [13/05/2012 17:27:04]
 */
package org.ambar.core.faces.components;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;

import org.ambar.core.dictionary.domain.navigation.Item;
import org.ambar.core.dictionary.domain.navigation.Module;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.tabview.Tab;

/**
 * <p>
 * Insertar descripción funcional.
 * </p>
 *
 * @author Sebastian
 *
 */
public class CustomMenuEntity extends AccordionPanel {

	@Override
	public void encodeBegin(FacesContext pContext) throws IOException {

		try {
			this.getChildren().clear();

			@SuppressWarnings("unchecked")
			List<Module> modules = (List<Module>) this.getAttributes().get("value");


			if (modules != null && modules.size() > 0) {
				this.setRendered(true);
				for (Module module : modules) {

					Tab tab = new Tab();
					tab.setTitle(module.getText());

					PanelGrid panelGrid = new PanelGrid();
					panelGrid.setColumns(1);
                    panelGrid.setStyleClass("menu-entity-style");

                    tab.getChildren().add(panelGrid);

					List<Item> items = module.getItems();

					if (items != null && items.size() > 0) {
						tab.setRendered(true);
						for (Item item : items) {

							CommandButton commandButton = new CommandButton();
							commandButton.setValue(item.getText());
							commandButton.setStyleClass("menu-entity-button-style");

							UIParameter entityParameter = new UIParameter();
							entityParameter.setName("target");
                            entityParameter.setValue(item.getId() + "Flow");

                            commandButton.getChildren().add(entityParameter);

                            UIParameter entityName = new UIParameter();
							entityName.setName("entityName");
                            entityName.setValue(item.getId());

                            commandButton.getChildren().add(entityName);

							commandButton.setActionExpression(pContext
									.getApplication()
									.getExpressionFactory()
									.createMethodExpression(pContext.getELContext(),
                                            "redirect",
											null,
											new Class<?>[0]));

							panelGrid.getChildren().add(commandButton);
						}
						this.getChildren().add(tab);
					}

				}
			} else {
				this.setRendered(false);
			}


			super.encodeBegin(pContext);
		} catch (IOException e) {
			FacesContext.getCurrentInstance().addMessage("Error al cargar el accordion",
					new FacesMessage("Error al renderizar el accordion"));
		}
	}

}
