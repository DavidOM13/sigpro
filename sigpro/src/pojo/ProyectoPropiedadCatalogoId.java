package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProyectoPropiedadCatalogoId generated by hbm2java
 */
@Embeddable
public class ProyectoPropiedadCatalogoId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -37200592702202319L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer estado;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int proyectoPropiedadid;

	public ProyectoPropiedadCatalogoId() {
	}

	public ProyectoPropiedadCatalogoId(int proyectoPropiedadid) {
		this.proyectoPropiedadid = proyectoPropiedadid;
	}

	public ProyectoPropiedadCatalogoId(Integer id, String nombre, String descripcion, Integer estado,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion,
			int proyectoPropiedadid) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.proyectoPropiedadid = proyectoPropiedadid;
	}

	@Column(name = "id")
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", length = 200)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 1000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estado")
	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	@Column(name = "usuario_creo", length = 30)
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

	@Column(name = "fecha_creacion", length = 19)
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

	@Column(name = "proyecto_propiedadid", nullable = false)
	public int getProyectoPropiedadid() {
		return this.proyectoPropiedadid;
	}

	public void setProyectoPropiedadid(int proyectoPropiedadid) {
		this.proyectoPropiedadid = proyectoPropiedadid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProyectoPropiedadCatalogoId))
			return false;
		ProyectoPropiedadCatalogoId castOther = (ProyectoPropiedadCatalogoId) other;

		return ((this.getId() == castOther.getId())
				|| (this.getId() != null && castOther.getId() != null && this.getId().equals(castOther.getId())))
				&& ((this.getNombre() == castOther.getNombre()) || (this.getNombre() != null
						&& castOther.getNombre() != null && this.getNombre().equals(castOther.getNombre())))
				&& ((this.getDescripcion() == castOther.getDescripcion())
						|| (this.getDescripcion() != null && castOther.getDescripcion() != null
								&& this.getDescripcion().equals(castOther.getDescripcion())))
				&& ((this.getEstado() == castOther.getEstado()) || (this.getEstado() != null
						&& castOther.getEstado() != null && this.getEstado().equals(castOther.getEstado())))
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
				&& (this.getProyectoPropiedadid() == castOther.getProyectoPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result + (getNombre() == null ? 0 : this.getNombre().hashCode());
		result = 37 * result + (getDescripcion() == null ? 0 : this.getDescripcion().hashCode());
		result = 37 * result + (getEstado() == null ? 0 : this.getEstado().hashCode());
		result = 37 * result + (getUsuarioCreo() == null ? 0 : this.getUsuarioCreo().hashCode());
		result = 37 * result + (getUsuarioActualizo() == null ? 0 : this.getUsuarioActualizo().hashCode());
		result = 37 * result + (getFechaCreacion() == null ? 0 : this.getFechaCreacion().hashCode());
		result = 37 * result + (getFechaActualizacion() == null ? 0 : this.getFechaActualizacion().hashCode());
		result = 37 * result + this.getProyectoPropiedadid();
		return result;
	}

}
