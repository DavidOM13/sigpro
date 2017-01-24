package pojo;
// Generated Jan 24, 2017 1:17:58 PM by Hibernate Tools 5.2.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FormularioTipo generated by hbm2java
 */
@Entity
@Table(name = "formulario_tipo", catalog = "sigpro")
public class FormularioTipo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1934425128368174405L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private String usarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private int formularioid;
	private Set<Formulario> formularios = new HashSet<Formulario>(0);

	public FormularioTipo() {
	}

	public FormularioTipo(String nombre, String usarioCreo, Date fechaCreacion, int estado, int formularioid) {
		this.nombre = nombre;
		this.usarioCreo = usarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
		this.formularioid = formularioid;
	}

	public FormularioTipo(String nombre, String descripcion, String usarioCreo, String usuarioActualizo,
			Date fechaCreacion, Date fechaActualizacion, int estado, int formularioid, Set<Formulario> formularios) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usarioCreo = usarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.formularioid = formularioid;
		this.formularios = formularios;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 1000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 4000)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "usario_creo", nullable = false, length = 30)
	public String getUsarioCreo() {
		return this.usarioCreo;
	}

	public void setUsarioCreo(String usarioCreo) {
		this.usarioCreo = usarioCreo;
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

	@Column(name = "formularioid", nullable = false)
	public int getFormularioid() {
		return this.formularioid;
	}

	public void setFormularioid(int formularioid) {
		this.formularioid = formularioid;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formularioTipo")
	public Set<Formulario> getFormularios() {
		return this.formularios;
	}

	public void setFormularios(Set<Formulario> formularios) {
		this.formularios = formularios;
	}

}
