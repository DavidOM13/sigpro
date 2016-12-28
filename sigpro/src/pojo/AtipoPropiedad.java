package pojo;
// Generated Dec 28, 2016 1:25:08 PM by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * AtipoPropiedad generated by hbm2java
 */
@Entity
@Table(name = "atipo_propiedad", catalog = "sigpro")
public class AtipoPropiedad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4928094092548059852L;
	private AtipoPropiedadId id;
	private ActividadPropiedad actividadPropiedad;
	private ActividadTipo actividadTipo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public AtipoPropiedad() {
	}

	public AtipoPropiedad(AtipoPropiedadId id, ActividadPropiedad actividadPropiedad, ActividadTipo actividadTipo) {
		this.id = id;
		this.actividadPropiedad = actividadPropiedad;
		this.actividadTipo = actividadTipo;
	}

	public AtipoPropiedad(AtipoPropiedadId id, ActividadPropiedad actividadPropiedad, ActividadTipo actividadTipo,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion) {
		this.id = id;
		this.actividadPropiedad = actividadPropiedad;
		this.actividadTipo = actividadTipo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "actividadTipoid", column = @Column(name = "actividad_tipoid", nullable = false)),
			@AttributeOverride(name = "actividadPropiedadid", column = @Column(name = "actividad_propiedadid", nullable = false)) })
	public AtipoPropiedadId getId() {
		return this.id;
	}

	public void setId(AtipoPropiedadId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad_propiedadid", nullable = false, insertable = false, updatable = false)
	public ActividadPropiedad getActividadPropiedad() {
		return this.actividadPropiedad;
	}

	public void setActividadPropiedad(ActividadPropiedad actividadPropiedad) {
		this.actividadPropiedad = actividadPropiedad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "actividad_tipoid", nullable = false, insertable = false, updatable = false)
	public ActividadTipo getActividadTipo() {
		return this.actividadTipo;
	}

	public void setActividadTipo(ActividadTipo actividadTipo) {
		this.actividadTipo = actividadTipo;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion", length = 19)
	public Date getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
