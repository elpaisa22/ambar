/**
 * ambar-core-impl [21/08/2011 18:55:29]
 */
package org.ambar.core.commons.converters;

import org.ambar.core.be.EstadoTracking;
import org.dozer.DozerConverter;

/**
 * Conversor a ser utilizado por la librería Dozer para poder mapear clases
 * que tengan atributos de tipo {@link EstadoAuditoria} a otras donde el
 * atributo correspondiente es de tipo {@link String}.
 *
 * @author Sebastian
 */
public class EstadoTrackingString extends DozerConverter<EstadoTracking, String> {

	/**
	 * Constructor por default.
	 */
	public EstadoTrackingString() {
		super(EstadoTracking.class, String.class);
	}

	/**
	 * Convierte un valor de tipo {@link String} en un {@link EstadoTracking}.
	 * @param pValue {@link String} a convertir
	 * @param pEnum {@link EstadoAuditoria}
	 * @return {@link EstadoTracking}
	 * @see org.dozer.DozerConverter#convertFrom(java.lang.Object, java.lang.Object)
	 */
	@Override
	public EstadoTracking convertFrom(String pValue, EstadoTracking pEnum) {
		return EstadoTracking.fromValue(pValue);
	}

	/**
	 * Convierte una opción de {@link EstadoTracking} en un {@link String}.
	 * @param pEstadoAuditoria Valor a convertir
	 * @param pString Valor
	 * @return {@link String}
	 * @see org.dozer.DozerConverter#convertTo(java.lang.Object, java.lang.Object)
	 */
	@Override
	public String convertTo(EstadoTracking pEstadoAuditoria, String pString) {
		return pEstadoAuditoria.getValue();
	}
}
