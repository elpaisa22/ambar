/**
 * ambar-core-impl [11/2/2015 17:15:21]
 */
package org.ambar.core.commons.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.ambar.core.be.EstadoTracking;
import org.ambar.core.be.TrackInfo;
import org.ambar.core.be.Trackingable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Listener de eventos de auditoría JPA.
 * Clase que permite cargar la información de auditoría de una entidad.</p>
 *
 * @author Sebastian
 *
 */
public class TrackingEventListener implements Serializable {

	private static final long serialVersionUID = -2808750097078991040L;
	
	private static final Logger LOG = LoggerFactory.getLogger(TrackingEventListener.class);

	/**
     * Agrega los datos de auditoría antes de persistir la entity.
     * @param entity Entidad a insertar.
     */
    @PrePersist
    final void onPrePersist(final Object entity) {
    	LOG.info("Ingreso a \"onPrePersist\"");

    	// Si la entity no es Trackingable, no debe hacer nada
    	if (!(entity instanceof Trackingable)) {
    		return;
    	}

    	Trackingable trackingableEntity = (Trackingable) entity;

    	TrackInfo trackInfo = trackingableEntity.getTrackInfo();

    	// La info de tracking siempre debe existir
    	if (trackInfo == null) {
    		trackInfo = new TrackInfo();
    		//TODO : Agregar usuario correcto
        	trackInfo.setUsuario("LogTrackUser");
    	}

    	// Seteo de los datos
    	trackInfo.setFechaCreacion(new Date());
    	trackInfo.setEstado(EstadoTracking.Activo);
    	trackingableEntity.setTrackInfo(trackInfo);
    }
    
    
    /**
     * Agrega los datos de tracking antes de actualizar la entity.
     * @param entity Entidad que se actualizará
     */
    @PreUpdate
    final void onPreUpdate(final Object entity) {
    	LOG.info("Ingreso a \"onPreUpdate\"");
		// Si la entity no es auditable, no debe hacer nada
    	if (!(entity instanceof Trackingable)) {
    		return;
    	}

    	Trackingable trackingableEntity = (Trackingable) entity;

    	TrackInfo trackInfo = trackingableEntity.getTrackInfo();
    	// La auditoría siempre debe existir
    	if (trackInfo == null) {
    		trackInfo = new TrackInfo();
    		//TODO : Agregar usuario correcto
        	trackInfo.setUsuario("LogTrackUser");
    	}

    	if(trackInfo.getFechaCreacion() == null){
    		trackInfo.setFechaCreacion(new Date());
    	}

    	// Seteo de los datos
    	trackInfo.setFechaModificacion(new Date());
    	if (EstadoTracking.Baja.equals(trackInfo.getEstado())) {
    		trackInfo.setEstado(EstadoTracking.Baja);
    	} else {
    		trackInfo.setEstado(EstadoTracking.Modificado);
    	}

    	trackingableEntity.setTrackInfo(trackInfo);

    }

}
