package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ObjetoRiesgoId generated by hbm2java
 */
@Embeddable
public class ObjetoRiesgoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3569135277699994170L;
	private int riesgoid;
	private int proyectoid;
	private int componenteid;
	private int productoid;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;

	public ObjetoRiesgoId() {
	}

	public ObjetoRiesgoId(int riesgoid, int proyectoid, int componenteid, int productoid, String usuarioCreo,
			Date fechaCreacion, int estado) {
		this.riesgoid = riesgoid;
		this.proyectoid = proyectoid;
		this.componenteid = componenteid;
		this.productoid = productoid;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public ObjetoRiesgoId(int riesgoid, int proyectoid, int componenteid, int productoid, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado) {
		this.riesgoid = riesgoid;
		this.proyectoid = proyectoid;
		this.componenteid = componenteid;
		this.productoid = productoid;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	@Column(name = "riesgoid", nullable = false)
	public int getRiesgoid() {
		return this.riesgoid;
	}

	public void setRiesgoid(int riesgoid) {
		this.riesgoid = riesgoid;
	}

	@Column(name = "proyectoid", nullable = false)
	public int getProyectoid() {
		return this.proyectoid;
	}

	public void setProyectoid(int proyectoid) {
		this.proyectoid = proyectoid;
	}

	@Column(name = "componenteid", nullable = false)
	public int getComponenteid() {
		return this.componenteid;
	}

	public void setComponenteid(int componenteid) {
		this.componenteid = componenteid;
	}

	@Column(name = "productoid", nullable = false)
	public int getProductoid() {
		return this.productoid;
	}

	public void setProductoid(int productoid) {
		this.productoid = productoid;
	}

	@Column(name = "usuario_creo", nullable = false, length = 30)
	public String getUsuarioCreo() {
		return this.usuarioCreo;
	}

	public void setUsuarioCreo(String usuarioCreo) {
		this.usuarioCreo = usuarioCreo;
	}

	@Column(name = "usuario_actualizo", length = 30)
	public String getUsuarioActualizo() {
		return this.usuarioActualizo;
	}

	public void setUsuarioActualizo(String usuarioActualizo) {
		this.usuarioActualizo = usuarioActualizo;
	}

	@Column(name = "fecha_creacion", nullable = false, length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Column(name = "fecha_actualizacion", length = 19)
	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ObjetoRiesgoId))
			return false;
		ObjetoRiesgoId castOther = (ObjetoRiesgoId) other;

		return (this.getRiesgoid() == castOther.getRiesgoid()) && (this.getProyectoid() == castOther.getProyectoid())
				&& (this.getComponenteid() == castOther.getComponenteid())
				&& (this.getProductoid() == castOther.getProductoid())
				&& ((this.getUsuarioCreo() == castOther.getUsuarioCreo())
						|| (this.getUsuarioCreo() != null && castOther.getUsuarioCreo() != null
								&& this.getUsuarioCreo().equals(castOther.getUsuarioCreo())))
				&& ((this.getUsuarioActualizo() == castOther.getUsuarioActualizo())
						|| (this.getUsuarioActualizo() != null && castOther.getUsuarioActualizo() != null
								&& this.getUsuarioActualizo().equals(castOther.getUsuarioActualizo())))
				&& ((this.getFechaCreacion() == castOther.getFechaCreacion())
						|| (this.getFechaCreacion() != null && castOther.getFechaCreacion() != null
								&& this.getFechaCreacion().equals(castOther.getFechaCreacion())))
				&& ((this.getFechaActualizacion() == castOther.getFechaActualizacion())
						|| (this.getFechaActualizacion() != null && castOther.getFechaActualizacion() != null
								&& this.getFechaActualizacion().equals(castOther.getFechaActualizacion())))
				&& (this.getEstado() == castOther.getEstado());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRiesgoid();
		result = 37 * result + this.getProyectoid();
		result = 37 * result + this.getComponenteid();
		result = 37 * result + this.getProductoid();
		result = 37 * result + (getUsuarioCreo() == null ? 0 : this.getUsuarioCreo().hashCode());
		result = 37 * result + (getUsuarioActualizo() == null ? 0 : this.getUsuarioActualizo().hashCode());
		result = 37 * result + (getFechaCreacion() == null ? 0 : this.getFechaCreacion().hashCode());
		result = 37 * result + (getFechaActualizacion() == null ? 0 : this.getFechaActualizacion().hashCode());
		result = 37 * result + this.getEstado();
		return result;
	}

}
