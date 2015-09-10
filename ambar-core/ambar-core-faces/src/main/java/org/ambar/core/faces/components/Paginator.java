/**
 * ambar-core-faces [7/4/2015 17:52:03]
 */
package org.ambar.core.faces.components;

import java.io.IOException;

import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;



/**
 * <p>
 * Paginador de la grilla principal.
 * </p>
 *
 * @author Sebastian
 *
 */
public class Paginator extends HtmlPanelGroup {
	
	public static final String COMPONENT_TYPE = "org.ambar.component.Paginator";
	public static final String COMPONENT_FAMILY = "org.ambar.component";
	private static final String DEFAULT_RENDERER = "org.ambar.renderer.PaginatorRenderer";

	protected enum PropertyKeys {

		total,
		rowCount,
		relatedBean,
		handler,
		actualPage;

		String toString;

		PropertyKeys(String toString) {
			this.toString = toString;
		}

		PropertyKeys() {}

		public String toString() {
			return ((this.toString != null) ? this.toString : super.toString());
		}
	}

	public Paginator() {
		setRendererType(DEFAULT_RENDERER);
	}

	public String getFamily() {
		return COMPONENT_FAMILY;
	}

	/**
	 * @return Retorna el valor del atributo total.
	 */
	public Double getTotal() {
		return (java.lang.Double) getStateHelper().eval(PropertyKeys.total, 0);
	}


	/**
	 * @param pTotal Establece el valor del atributo total.
	 */
	public void setTotal(Double pTotal) {
		getStateHelper().put(PropertyKeys.total, pTotal);
	}


	/**
	 * @return Retorna el valor del atributo rowCount.
	 */
	public Double getRowCount() {
		return (java.lang.Double) getStateHelper().eval(PropertyKeys.rowCount, 0D);
	}


	/**
	 * @param pRowCount Establece el valor del atributo rowCount.
	 */
	public void setRowCount(Double pRowCount) {
		getStateHelper().put(PropertyKeys.rowCount, pRowCount);
	}


	/**
	 * @return Retorna el valor del atributo handler.
	 */
	public String getHandler() {
		return (java.lang.String) getStateHelper().eval(PropertyKeys.handler, null);
	}


	/**
	 * @param pHandler Establece el valor del atributo handler.
	 */
	public void setHandler(String pHandler) {
		getStateHelper().put(PropertyKeys.handler, pHandler);
	}


	/**
	 * @return Retorna el valor del atributo actualPage.
	 */
	public Integer getActualPage() {
		return (java.lang.Integer) getStateHelper().eval(PropertyKeys.actualPage, 1);
	}


	/**
	 * @param pActualPage Establece el valor del atributo actualPage.
	 */
	public void setActualPage(Integer pActualPage) {
		getStateHelper().put(PropertyKeys.actualPage, pActualPage);
	}
	
	@Override
	public void encodeBegin(FacesContext pContext) throws IOException {

		this.getChildren().clear();
		
		Integer actualPage = getActualPage();
		if ((null == actualPage ) || (actualPage == 0)) {
			actualPage = 1;
		}
		


		HtmlCommandLink first = new HtmlCommandLink();
		first.setTitle("Ir a la primer página");
		HtmlOutputText firstText = new HtmlOutputText();
		firstText.setValue("<<");

		HtmlCommandLink previous = new HtmlCommandLink();
		previous.setTitle("Ir a la página anterior");
		HtmlOutputText previousText = new HtmlOutputText();
		previousText.setValue("<");

		first.getChildren().add(firstText);
		previous.getChildren().add(previousText);
		this.getChildren().add(first);
		this.getChildren().add(previous);

		if (actualPage > 1) {
			previous.setActionExpression(pContext.getApplication().getExpressionFactory()
					.createMethodExpression(pContext.getELContext(), "#{"
									+ getHandler() + "(" + (actualPage - 1) + "," + getRowCount() + ")}",
							null, new Class<?>[0]));

			first.setActionExpression(pContext.getApplication().getExpressionFactory()
					.createMethodExpression(pContext.getELContext(), "#{"
									+ getHandler() + "(" + 1 + "," + getRowCount() + ")}",
							null, new Class<?>[0]));
		} else {
			first.setStyleClass("disabled-page");
			first.setDisabled(true);
			previous.setStyleClass("disabled-page");
			previous.setDisabled(true);
		}
		
		
		Double division = Math.ceil(this.getTotal() / this.getRowCount());;

		int i = getFirstPosition(actualPage);
		int limit = getLastPosition(actualPage, division);
		
		if (i >= 1) {
			HtmlOutputText moreElementsTextPrevious = new HtmlOutputText();
			moreElementsTextPrevious.setValue("...");

			HtmlCommandLink moreElementsPrevious = new HtmlCommandLink();
			moreElementsPrevious.getChildren().add(moreElementsTextPrevious);
			moreElementsPrevious.setDisabled(true);
			this.getChildren().add(moreElementsPrevious);
		}
		
		
		while (i < limit) {

			HtmlCommandLink page = new HtmlCommandLink();
			

			int pagev = i + 1;

			if (actualPage == (i + 1)) {
				page.setStyleClass("active-page");
			}

			page.setActionExpression(pContext.getApplication().getExpressionFactory()
					.createMethodExpression(pContext.getELContext(), "#{"
									+ this.getHandler() + "(" + pagev + "," + this.getRowCount() + ")}",
							null, new Class<?>[0]));
			page.setValue(String.valueOf(i + 1));
			this.getChildren().add(page);
			
			i++;
		}
		
		if (limit < division) {
			HtmlOutputText moreElementsTextNext = new HtmlOutputText();
			moreElementsTextNext.setValue("...");

			HtmlCommandLink moreElementsNext = new HtmlCommandLink();
			moreElementsNext.getChildren().add(moreElementsTextNext);
			moreElementsNext.setDisabled(true);
			this.getChildren().add(moreElementsNext);
		}
		
		
		HtmlCommandLink last = new HtmlCommandLink();
		last.setTitle("Ir a la última página");
		HtmlOutputText lastText = new HtmlOutputText();
		lastText.setValue(">>");

		HtmlCommandLink next = new HtmlCommandLink();
		next.setTitle("Ir a la página siguiente");
		HtmlOutputText nextText = new HtmlOutputText();
		nextText.setValue(">");

		next.getChildren().add(nextText);
		last.getChildren().add(lastText);
		this.getChildren().add(next);
		this.getChildren().add(last);
		
		if (actualPage < division) {
			next.setActionExpression(pContext.getApplication().getExpressionFactory()
				.createMethodExpression(pContext.getELContext(), "#{"
								+ this.getHandler() + "(" + (actualPage + 1) + "," + this.getRowCount() + ")}",
						null, new Class<?>[0]));

			last.setActionExpression(pContext.getApplication().getExpressionFactory()
				.createMethodExpression(pContext.getELContext(), "#{"
								+ this.getHandler() + "(" + division + "," + this.getRowCount() + ")}",
						null, new Class<?>[0]));
		} else {
			next.setStyleClass("disabled-page");
			next.setDisabled(true);
			last.setStyleClass("disabled-page");
			last.setDisabled(true);
		}

		super.encodeBegin(pContext);
	}
	
	/**
	 * Obtiene el indice inferior a partir del cual se comenzaran a renderear las paginas.
	 * @param pActualPage Pagina Actual
	 * @return Numero de Pagina
	 */
	private int getFirstPosition(Integer pActualPage) {
		int cant = 0;
		int findex = pActualPage;

		while ((cant < 4) && (findex >= 1)) {
			findex--;
			cant++;
		}

		return findex;
	}

	/**
	 * Obtiene el indice superior a partir del cual se comenzaran a renderear las paginas.
	 * @param pActualPage Pagina Actual
	 * @param pDivision Cantidad de Paginas
	 * @return Numero de Pagina
	 */
	private int getLastPosition(Integer pActualPage, Double pDivision) {
		int cant = 0;
		int lindex = pActualPage;

		while ((cant < 3) && (lindex < pDivision)) {
			lindex++;
			cant++;
		}

		return lindex;
	}
}
