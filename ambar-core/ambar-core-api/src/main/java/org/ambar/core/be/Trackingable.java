/**
 * ambar-core-api [10/08/2011 22:46:56]
 */
package org.ambar.core.be;

/**
 * Interfaz que deben implementar todas las entidades de negocio en las que se deba hacer un
 * seguimiento de cambios.
 *
 * @author Sebastian
 *
 */
public interface Trackingable {
	/**
	 * Getter de la propiedad InfoAuditoria que contiene los campos de tracking
	 * de la entidad.
	 * @return com.ambar.core.be.InfoAuditoria
	 */
	TrackInfo getTrackInfo();

	/**
	 * Getter de la propiedad InfoAuditoria que contiene los campos de tracking
	 * de la entidad.
	 * @param pTrackInfo Valor de la propiedad
	 */
	void setTrackInfo(TrackInfo pTrackInfo);
}
