/**
 * ambar-core-api [20/08/2011 00:05:49]
 */
package org.ambar.core.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Clase que encapsula la información del seguimiento de cambios en DTOs. Los
 * atributos que definen ese seguimiento son:
 * <ul>
 * <li>Usuario que realiza los cambios</li>
 * <li>Fecha en que realiza los cambios</li>
 * <li>Fecha de creación del registro</li>
 * <li>Estado actual</li>
 * </ul>
 * </p>
 *
 * @author Sebastian
 */
public class TrackInfoDTO implements Serializable {

	private static final long serialVersionUID = 2305684525566114854L;

	private String usuario;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String estado;

	/**
	 * Constructor por default.
	 */
	public TrackInfoDTO() {
		this.usuario = "idUsuario";
		this.fechaCreacion = new Date();
		this.estado = "A";
		
	}

	/**
	 * @return Retorna el valor del atributo usuario.
	 */
	public String getUsuario() {
		return this.usuario;
	}

	/**
	 * @param pUsuario Establece el valor del atributo usuario.
	 */
	public void setUsuario(String pUsuario) {
		this.usuario = pUsuario;
	}

	/**
	 * @return Retorna el valor del atributo fechaCreacion.
	 */
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	/**
	 * @param pFechaCreacion Establece el valor del atributo fechaCreacion.
	 */
	public void setFechaCreacion(Date pFechaCreacion) {
		this.fechaCreacion = pFechaCreacion;
	}

	/**
	 * @return Retorna el valor del atributo fechaModificacion.
	 */
	public Date getFechaModificacion() {
		return this.fechaModificacion;
	}

	/**
	 * @param pFechaModificacion Establece el valor del atributo fechaModificacion.
	 */
	public void setFechaModificacion(Date pFechaModificacion) {
		this.fechaModificacion = pFechaModificacion;
	}

	/**
	 * @return Retorna el valor del atributo estado.
	 */
	public String getEstado() {
		return this.estado;
	}

	/**
	 * @param pEstado Establece el valor del atributo estado.
	 */
	public void setEstado(String pEstado) {
		this.estado = pEstado;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (estado != null) {
			result = prime * result + estado.hashCode();
		}
		if (fechaCreacion != null) {
			result = prime * result + fechaCreacion.hashCode();
		}
		if (fechaModificacion != null) {
			result = prime * result + fechaModificacion.hashCode();
		}
		if (usuario != null) {
			result = prime * result + usuario.hashCode();
		}
		return result;
	}


	/* (non-JSDoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object pObj) {
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (!(pObj instanceof TrackInfoDTO)) {
			return false;
		}
		TrackInfoDTO other = (TrackInfoDTO) pObj;
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		if (fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else if (!fechaCreacion.equals(other.fechaCreacion)) {
			return false;
		}
		if (fechaModificacion == null) {
			if (other.fechaModificacion != null) {
				return false;
			}
		} else if (!fechaModificacion.equals(other.fechaModificacion)) {
			return false;
		}
		if (usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InfoAuditoriaDTO [");
		if (estado != null) {
			builder.append("codigoEstado=").append(estado).append(", ");
		}
		if (fechaCreacion != null) {
			builder.append("fechaCreacion=").append(fechaCreacion).append(", ");
		}
		if (fechaModificacion != null) {
			builder.append("fechaModificacion=").append(fechaModificacion).append(", ");
		}
		if (usuario != null) {
			builder.append("usuario=").append(usuario);
		}
		builder.append("]");
		return builder.toString();
	}
}

