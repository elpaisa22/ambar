/**
 * ambar-core-api [10/08/2011 22:50:43]
 */
package org.ambar.core.be;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * <p>
 * Clase que encapsula la información del seguimiento de cambios en
 * entidades de negocio. Los atributos que definen ese seguimiento son:
 * <ul>
 * <li>Usuario que realiza los cambios</li>
 * <li>Fecha en que realiza los cambios</li>
 * <li>Fecha de creación del registro</li>
 * <li>Estado actual</li>
 * </ul>
 * </p>
 *
 * @author Sebastian
 *
 */
@Embeddable
public class TrackInfo {
	private String usuario;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private EstadoTracking estado;

	/**
	 * @return Retorna el valor del atributo usuario.
	 */
    @Column(name = "USUARIO")
    @Basic(optional = false)
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
    @Column(name = "FECALTA")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
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
    @Column(name = "FECMODIFICACION")
    @Basic(optional = true)
    @Temporal(TemporalType.TIMESTAMP)
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
	@Transient
	public EstadoTracking getEstado() {
		return this.estado;
	}

	/**
	 * @param pEstado Establece el valor del atributo estado.
	 */
	public void setEstado(EstadoTracking pEstado) {
		this.estado = pEstado;
	}

	/**
	 * @param pEstadoDB Establece el valor del atributo estado a partir
	 * de un {@link String}.
	 */
	@SuppressWarnings("unused")
	private void setEstadoPersistent(String pEstadoDB) {
		this.estado = EstadoTracking.fromValue(pEstadoDB);
	}

	/**
	 * @return Devuelve un {@link String} con el valor del atributo
	 * estado convertido.
	 */
	@Column(name = "ESTADO")
    @Basic(optional = false)
	private String getEstadoPersistent() {
		if (this.estado != null) {
			return this.estado.getValue();
		} else {
			return null;
		}
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		if (this.estado != null) {
			result = prime * result + this.estado.hashCode();
		}
		if (this.fechaCreacion != null) {
			result = prime * result + this.fechaCreacion.hashCode();
		}
		if (this.fechaModificacion != null) {
			result = prime * result + this.fechaModificacion.hashCode();
		}
		if (this.usuario != null) {
			result = prime * result + this.usuario.hashCode();
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
		if (!(pObj instanceof TrackInfo)) {
			return false;
		}
		final TrackInfo other = (TrackInfo) pObj;
		if (this.estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!this.estado.equals(other.estado)) {
			return false;
		}
		if (this.fechaCreacion == null) {
			if (other.fechaCreacion != null) {
				return false;
			}
		} else if (!this.fechaCreacion.equals(other.fechaCreacion)) {
			return false;
		}
		if (this.fechaModificacion == null) {
			if (other.fechaModificacion != null) {
				return false;
			}
		} else if (!this.fechaModificacion.equals(other.fechaModificacion)) {
			return false;
		}
		if (this.usuario == null) {
			if (other.usuario != null) {
				return false;
			}
		} else if (!this.usuario.equals(other.usuario)) {
			return false;
		}
		return true;
	}

	/* (non-JSDoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("InfoAuditoria [");
		if (this.estado != null) {
			builder.append("estado=").append(this.estado).append(", ");
		}
		if (this.fechaCreacion != null) {
			builder.append("fechaCreacion=").append(this.fechaCreacion).append(", ");
		}
		if (this.fechaModificacion != null) {
			builder.append("fechaModificacion=").append(this.fechaModificacion).append(", ");
		}
		if (this.usuario != null) {
			builder.append("usuario=").append(this.usuario);
		}
		builder.append("]");
		return builder.toString();
	}
}
