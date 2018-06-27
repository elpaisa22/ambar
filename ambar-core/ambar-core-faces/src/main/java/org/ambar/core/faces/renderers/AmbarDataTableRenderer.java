/**
 * ambar-core-faces [17/6/2015 19:06:10]
 */
package org.ambar.core.faces.renderers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.FacesException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.component.api.UIColumn;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.datatable.DataTableRenderer;
import org.primefaces.model.SortMeta;
import org.primefaces.util.HTML;

/**
 * <p>
 * Custom Renderer de la grilla principal.
 * </p>
 *
 * @deprecated Al parecer el error esta solucionado en la nueva version de Primefaces 6.2
 * @author Sebastian
 *
 */
@Deprecated
public class AmbarDataTableRenderer extends DataTableRenderer {


	@Override
	protected String resolveDefaultSortIcon(DataTable pTable, UIColumn pColumn, String pSortOrder) {
		ValueExpression tableSortByVE = pTable.getValueExpression("sortBy");
        ValueExpression columnSortByVE = pColumn.getValueExpression("sortBy");
        String columnSortByExpression = columnSortByVE.getExpressionString();
        String tableSortByExpression = tableSortByVE.getExpressionString();
        String field = pColumn.getField();
        //String sortField = pTable.resolveSortField();
        Class clazz = pTable.getClass();

        // Fails at the next line:
        Method resolveSortField = null;
		try {
			resolveSortField = clazz.getDeclaredMethod("resolveSortField");
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        resolveSortField.setAccessible(true);

        // How to invoke the method and return List<String> items?
        // tried this but it fails?
        String sortField = null;
		try {
			sortField = (String) resolveSortField.invoke(pTable);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String sortIcon = null;

        if((sortField != null && field != null && sortField.equals(field)) ) {
            if(pSortOrder.equalsIgnoreCase("ASCENDING"))
                sortIcon = DataTable.SORTABLE_COLUMN_ASCENDING_ICON_CLASS;
            else if(pSortOrder.equalsIgnoreCase("DESCENDING"))
                sortIcon = DataTable.SORTABLE_COLUMN_DESCENDING_ICON_CLASS;
        }
        
        return sortIcon;
	}

	@Override
	public void encodeColumnHeader(FacesContext context, DataTable table, UIColumn column) throws IOException {
        if (!column.isRendered()) {
            return;
        }

        ResponseWriter writer = context.getResponseWriter();
        String clientId = column.getContainerClientId(context);

        ValueExpression columnSortByVE = column.getValueExpression("sortBy");
        boolean sortable = (columnSortByVE != null && column.isSortable());
        boolean filterable = (column.getValueExpression("filterBy") != null && column.isFilterable());
        String selectionMode = column.getSelectionMode();
        String sortIcon = null;
        boolean resizable = table.isResizableColumns() && column.isResizable();
        int priority = column.getPriority();

        String columnClass = sortable ? DataTable.COLUMN_HEADER_CLASS + " " + DataTable.SORTABLE_COLUMN_CLASS : DataTable.COLUMN_HEADER_CLASS;
        columnClass = filterable ? columnClass + " " + DataTable.FILTER_COLUMN_CLASS : columnClass;
        columnClass = selectionMode != null ? columnClass + " " + DataTable.SELECTION_COLUMN_CLASS : columnClass;
        columnClass = resizable ? columnClass + " " + DataTable.RESIZABLE_COLUMN_CLASS : columnClass;
        columnClass = !column.isToggleable() ? columnClass + " " + DataTable.STATIC_COLUMN_CLASS : columnClass;
        columnClass = !column.isVisible()? columnClass + " " + DataTable.HIDDEN_COLUMN_CLASS : columnClass;
        columnClass = column.getStyleClass() != null ? columnClass + " " + column.getStyleClass() : columnClass;

        if (priority > 0) {
            columnClass = columnClass + " ui-column-p-" + priority;
        }

        String columnHeaderText = column.getHeaderText();
        if (sortable) {
            ValueExpression tableSortByVE = table.getValueExpression("sortBy");
            Object tableSortBy = table.getSortBy();
            boolean defaultSorted = (tableSortByVE != null || tableSortBy != null);

            if (defaultSorted) {
                if (table.isMultiSort()) {
                    List<SortMeta> sortMeta = table.getMultiSortMeta();

                    if (sortMeta != null) {
                        for (SortMeta meta : sortMeta) {
                            sortIcon = resolveDefaultSortIcon(column, meta);

                            if (sortIcon != null) {
                                break;
                            }
                        }
                    }
                } else {
                    sortIcon = resolveDefaultSortIcon(table, column, table.getSortOrder());
                }
            }

            if(sortIcon == null)
                sortIcon = DataTable.SORTABLE_COLUMN_ICON_CLASS;
            else
                columnClass += " ui-state-active";
        }
        
        String style = column.getStyle();
        String width = column.getWidth();
        if(width != null) {
            String unit = width.endsWith("%") ? "" : "px";
            if(style != null)
                style = style + ";width:" + width + unit;
            else
                style = "width:" + width + unit;
        }
        
        writer.startElement("th", null);
        writer.writeAttribute("id", clientId, null);
        writer.writeAttribute("class", columnClass, null);
        writer.writeAttribute("role", "columnheader", null);
        
        if(style != null) writer.writeAttribute("style", style, null);
        if(column.getRowspan() != 1) writer.writeAttribute("rowspan", column.getRowspan(), null);
        if(column.getColspan() != 1) writer.writeAttribute("colspan", column.getColspan(), null);
        
        if(filterable) {
            table.enableFiltering();

            String filterPosition = column.getFilterPosition();

            if(filterPosition.equals("bottom")) {
                encodeColumnHeaderContent(context, column, sortIcon);
                encodeFilter(context, table, column);
            }
            else if(filterPosition.equals("top")) {
                encodeFilter(context, table, column);
                encodeColumnHeaderContent(context, column, sortIcon);
            } 
            else {
                throw new FacesException(filterPosition + " is an invalid option for filterPosition, valid values are 'bottom' or 'top'.");
            }
        }
        else {
            encodeColumnHeaderContent(context, column, columnHeaderText, sortIcon);
        }
        
        if(selectionMode != null && selectionMode.equalsIgnoreCase("multiple")) {
            encodeCheckbox(context, table, false, false, HTML.CHECKBOX_ALL_CLASS, true);
        }
        
        writer.endElement("th");
    }
	
	protected void encodeColumnHeaderContent(FacesContext context, UIColumn column, String pText, String sortIcon) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
                        
        writer.startElement("span", null);
        writer.writeAttribute("class", DataTable.COLUMN_TITLE_CLASS, null);
        
        if (pText != null) {
        	writer.write(pText);
        } else {
        	UIComponent header = column.getFacet("header");
            String headerText = column.getHeaderText();
            if(header != null)
                header.encodeAll(context);
            else if(headerText != null)
                writer.write(headerText);
        }

        writer.endElement("span");

        if(sortIcon != null) {
            writer.startElement("span", null);
            writer.writeAttribute("class", sortIcon, null);
            writer.endElement("span");
        }
    }
}
