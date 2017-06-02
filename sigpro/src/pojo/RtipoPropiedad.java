package pojo;
// Generated Jun 2, 2017 12:28:44 PM by Hibernate Tools 5.2.1.Final

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
 * RtipoPropiedad generated by hbm2java
 */
@Entity
@Table(name = "rtipo_propiedad", catalog = "sipro")
public class RtipoPropiedad implements java.io.Serializable {

	private RtipoPropiedadId id;
	private RiesgoPropiedad riesgoPropiedad;
	private RiesgoTipo riesgoTipo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;

	public RtipoPropiedad() {
	}

	public RtipoPropiedad(RtipoPropiedadId id, RiesgoPropiedad riesgoPropiedad, RiesgoTipo riesgoTipo,
			String usuarioCreo, Date fechaCreacion, int estado) {
		this.id = id;
		this.riesgoPropiedad = riesgoPropiedad;
		this.riesgoTipo = riesgoTipo;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public RtipoPropiedad(RtipoPropiedadId id, RiesgoPropiedad riesgoPropiedad, RiesgoTipo riesgoTipo,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado) {
		this.id = id;
		this.riesgoPropiedad = riesgoPropiedad;
		this.riesgoTipo = riesgoTipo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "riesgoTipoid", column = @Column(name = "riesgo_tipoid", nullable = false)),
			@AttributeOverride(name = "riesgoPropiedadid", column = @Column(name = "riesgo_propiedadid", nullable = false)) })
	public RtipoPropiedadId getId() {
		return this.id;
	}

	public void setId(RtipoPropiedadId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "riesgo_propiedadid", nullable = false, insertable = false, updatable = false)
	public RiesgoPropiedad getRiesgoPropiedad() {
		return this.riesgoPropiedad;
	}

	public void setRiesgoPropiedad(RiesgoPropiedad riesgoPropiedad) {
		this.riesgoPropiedad = riesgoPropiedad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "riesgo_tipoid", nullable = false, insertable = false, updatable = false)
	public RiesgoTipo getRiesgoTipo() {
		return this.riesgoTipo;
	}

	public void setRiesgoTipo(RiesgoTipo riesgoTipo) {
		this.riesgoTipo = riesgoTipo;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false, length = 19)
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

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

}
