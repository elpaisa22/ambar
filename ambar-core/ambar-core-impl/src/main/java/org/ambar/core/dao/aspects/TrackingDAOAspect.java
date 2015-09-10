/**
 * ambar-core-impl [03/06/2013 18:45:18]
 */
package org.ambar.core.dao.aspects;

import java.util.Date;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.be.TrackInfo;
import org.ambar.core.be.Trackingable;
import org.aspectj.lang.JoinPoint;

/**
 * <p>
 * Acpecto encargado de cargar la información de Tracking en las operaciones de los DAO.
 * </p>
 *
 * @author Sebastian
 *
 */
public class TrackingDAOAspect {

	/**
	 * Aspecto invocado al momento de hacer un insert sobre los Dao crud.
	 * Sirve para verificar si hay informacion de Tracking. Si no es asi, se crea
	 * @param pJoinPoint Join Point
	 * @param pEntity Entidad a insertar
	 * @throws Throwable Excepcion
	 * */
	public void trackingInfoInsertBefore(JoinPoint pJoinPoint,
			                             Object pEntity) throws Throwable {

		if (pEntity instanceof Trackingable) {
			Trackingable trackingable = (Trackingable) pEntity;
			if (trackingable.getTrackInfo() == null) {
				TrackInfo trackInfo = new TrackInfo();
				trackInfo.setEstado(EstadoTracking.Activo);
				trackInfo.setFechaCreacion(new Date());
				trackInfo.setUsuario("EmptyUser");
				trackingable.setTrackInfo(trackInfo);
			}
		}
	}
}
