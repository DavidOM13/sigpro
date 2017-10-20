package pojo;
// Generated Oct 19, 2017 6:14:38 PM by Hibernate Tools 5.2.3.Final

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
 * RectipoPropiedad generated by hbm2java
 */
@Entity
@Table(name = "rectipo_propiedad", catalog = "sipro")
public class RectipoPropiedad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5684363664859512664L;
	private RectipoPropiedadId id;
	private RecursoPropiedad recursoPropiedad;
	private RecursoTipo recursoTipo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;

	public RectipoPropiedad() {
	}

	public RectipoPropiedad(RectipoPropiedadId id, RecursoPropiedad recursoPropiedad, RecursoTipo recursoTipo,
			String usuarioCreo, Date fechaCreacion, int estado) {
		this.id = id;
		this.recursoPropiedad = recursoPropiedad;
		this.recursoTipo = recursoTipo;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public RectipoPropiedad(RectipoPropiedadId id, RecursoPropiedad recursoPropiedad, RecursoTipo recursoTipo,
			String usuarioCreo, String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado) {
		this.id = id;
		this.recursoPropiedad = recursoPropiedad;
		this.recursoTipo = recursoTipo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "recursoPropiedadid", column = @Column(name = "recurso_propiedadid", nullable = false)),
			@AttributeOverride(name = "recursoTipoid", column = @Column(name = "recurso_tipoid", nullable = false)) })
	public RectipoPropiedadId getId() {
		return this.id;
	}

	public void setId(RectipoPropiedadId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recurso_propiedadid", nullable = false, insertable = false, updatable = false)
	public RecursoPropiedad getRecursoPropiedad() {
		return this.recursoPropiedad;
	}

	public void setRecursoPropiedad(RecursoPropiedad recursoPropiedad) {
		this.recursoPropiedad = recursoPropiedad;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recurso_tipoid", nullable = false, insertable = false, updatable = false)
	public RecursoTipo getRecursoTipo() {
		return this.recursoTipo;
	}

	public void setRecursoTipo(RecursoTipo recursoTipo) {
		this.recursoTipo = recursoTipo;
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
