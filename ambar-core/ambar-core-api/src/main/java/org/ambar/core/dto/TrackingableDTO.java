/**
 * ambar-core-api [20/08/2011 00:00:23]
 */
package org.ambar.core.dto;


/**
 * <p>
 * Interfaz que deben implementar todos los DTO cuyas entidades de negocio
 * tengan campos de seguimiento de cambios.
 * </p>
 *
 * @author Sebastian
 *
 * @see org.ambar.core.dto.TrackInfoDTO
 */
public interface TrackingableDTO {

	/**
	 * Devuelve una estructura de información de auditoria.
	 * @return org.ambar.foundation.dto.AuditInfoDTO
	 */
	TrackInfoDTO getTrackInfo();

	/**
	 * @param pTrackInfo Fija el valor de pAuditInfo al campo auditInfo.
	 */
	void setTrackInfo(TrackInfoDTO pTrackInfo);
}
