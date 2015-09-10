/**
 * ambar-core-impl [03/06/2013 17:36:41]
 */
package org.ambar.core.services.aspects;

import java.util.Date;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.commons.context.RequestContext;
import org.ambar.core.dto.TrackInfoDTO;
import org.ambar.core.dto.TrackingableDTO;
import org.aspectj.lang.JoinPoint;

/**
 * <p>
 * Acpecto encargado de cargar la información de Tracking.
 * </p>
 *
 * @author Sebastian
 *
 */
public class TrackingServicesAspect {

	/**
	 * Aspecto invocado al momento de hacer un insert sobre los servicios crud.
	 * Sirve para verificar si hay informacion de Tracking. Si no es asi, se crea
	 * @param pJoinPoint Join Point
	 * @param pEntity Entidad a insertar
	 * @param pRequestContext Contexto
	 * @throws Throwable Excepcion
	 * */
	public void trackingInfoBeforeInsert(JoinPoint pJoinPoint,
			                             Object pEntity,
			                             RequestContext pRequestContext) throws Throwable {
		if (pEntity instanceof TrackingableDTO) {
			TrackingableDTO trackingable = (TrackingableDTO) pEntity;
			if (trackingable.getTrackInfo() == null) {
				TrackInfoDTO trackInfo = new TrackInfoDTO();
				trackingable.setTrackInfo(trackInfo);
			}
			trackingable.getTrackInfo().setEstado(EstadoTracking.Activo.getValue());
			trackingable.getTrackInfo().setFechaCreacion(new Date());
			trackingable.getTrackInfo().setUsuario(pRequestContext.getUser());
		}
	}

	/**
	 * Aspecto invocado al momento de hacer un update sobre los servicios crud.
	 * Sirve para verificar si hay informacion de Tracking. Si no es asi, se crea
	 * @param pJoinPoint Join Point
	 * @param pEntity Entidad a editar
	 * @param pRequestContext Contexto
	 * @throws Throwable Excepcion
	 * */
	public void trackingInfoBeforeUpdate(JoinPoint pJoinPoint,
			                             Object pEntity,
			                             RequestContext pRequestContext) throws Throwable {
		if (pEntity instanceof TrackingableDTO) {
			TrackingableDTO trackingable = (TrackingableDTO) pEntity;
			if (trackingable.getTrackInfo() == null) {
				TrackInfoDTO trackInfo = new TrackInfoDTO();
				trackingable.setTrackInfo(trackInfo);
			}
			trackingable.getTrackInfo().setEstado(EstadoTracking.Modificado.getValue());
			trackingable.getTrackInfo().setFechaModificacion(new Date());
			trackingable.getTrackInfo().setUsuario(pRequestContext.getUser());
		}
	}

}
