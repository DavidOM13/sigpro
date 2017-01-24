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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Proyecto generated by hbm2java
 */
@Entity
@Table(name = "proyecto", catalog = "sigpro")
public class Proyecto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6235549621708983254L;
	private Integer id;
	private Cooperante cooperante;
	private ProyectoTipo proyectoTipo;
	private UnidadEjecutora unidadEjecutora;
	private String nombre;
	private String descripcion;
	private int snip;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private Set<Desembolso> desembolsos = new HashSet<Desembolso>(0);
	private Set<FormularioItemValor> formularioItemValors = new HashSet<FormularioItemValor>(0);
	private Set<Hito> hitos = new HashSet<Hito>(0);
	private Set<Meta> metas = new HashSet<Meta>(0);
	private Set<ProyectoPropedadValor> proyectoPropedadValors = new HashSet<ProyectoPropedadValor>(0);
	private Set<Riesgo> riesgos = new HashSet<Riesgo>(0);
	private Set<Componente> componentes = new HashSet<Componente>(0);
	private Set<ObjetoFormulario> objetoFormularios = new HashSet<ObjetoFormulario>(0);

	public Proyecto() {
	}

	public Proyecto(Cooperante cooperante, ProyectoTipo proyectoTipo, UnidadEjecutora unidadEjecutora, String nombre,
			int snip, String usuarioCreo, Date fechaCreacion, int estado) {
		this.cooperante = cooperante;
		this.proyectoTipo = proyectoTipo;
		this.unidadEjecutora = unidadEjecutora;
		this.nombre = nombre;
		this.snip = snip;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public Proyecto(Cooperante cooperante, ProyectoTipo proyectoTipo, UnidadEjecutora unidadEjecutora, String nombre,
			String descripcion, int snip, String usuarioCreo, String usuarioActualizo, Date fechaCreacion,
			Date fechaActualizacion, int estado, Set<Desembolso> desembolsos,
			Set<FormularioItemValor> formularioItemValors, Set<Hito> hitos, Set<Meta> metas,
			Set<ProyectoPropedadValor> proyectoPropedadValors, Set<Riesgo> riesgos, Set<Componente> componentes,
			Set<ObjetoFormulario> objetoFormularios) {
		this.cooperante = cooperante;
		this.proyectoTipo = proyectoTipo;
		this.unidadEjecutora = unidadEjecutora;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.snip = snip;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.desembolsos = desembolsos;
		this.formularioItemValors = formularioItemValors;
		this.hitos = hitos;
		this.metas = metas;
		this.proyectoPropedadValors = proyectoPropedadValors;
		this.riesgos = riesgos;
		this.componentes = componentes;
		this.objetoFormularios = objetoFormularios;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cooperanteid", nullable = false)
	public Cooperante getCooperante() {
		return this.cooperante;
	}

	public void setCooperante(Cooperante cooperante) {
		this.cooperante = cooperante;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyecto_tipoid", nullable = false)
	public ProyectoTipo getProyectoTipo() {
		return this.proyectoTipo;
	}

	public void setProyectoTipo(ProyectoTipo proyectoTipo) {
		this.proyectoTipo = proyectoTipo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidad_ejecutoraunidad_ejecutora", nullable = false)
	public UnidadEjecutora getUnidadEjecutora() {
		return this.unidadEjecutora;
	}

	public void setUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
		this.unidadEjecutora = unidadEjecutora;
	}

	@Column(name = "nombre", nullable = false, length = 2000)
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

	@Column(name = "snip", nullable = false)
	public int getSnip() {
		return this.snip;
	}

	public void setSnip(int snip) {
		this.snip = snip;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Desembolso> getDesembolsos() {
		return this.desembolsos;
	}

	public void setDesembolsos(Set<Desembolso> desembolsos) {
		this.desembolsos = desembolsos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<FormularioItemValor> getFormularioItemValors() {
		return this.formularioItemValors;
	}

	public void setFormularioItemValors(Set<FormularioItemValor> formularioItemValors) {
		this.formularioItemValors = formularioItemValors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Hito> getHitos() {
		return this.hitos;
	}

	public void setHitos(Set<Hito> hitos) {
		this.hitos = hitos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Meta> getMetas() {
		return this.metas;
	}

	public void setMetas(Set<Meta> metas) {
		this.metas = metas;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ProyectoPropedadValor> getProyectoPropedadValors() {
		return this.proyectoPropedadValors;
	}

	public void setProyectoPropedadValors(Set<ProyectoPropedadValor> proyectoPropedadValors) {
		this.proyectoPropedadValors = proyectoPropedadValors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Riesgo> getRiesgos() {
		return this.riesgos;
	}

	public void setRiesgos(Set<Riesgo> riesgos) {
		this.riesgos = riesgos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "proyecto")
	public Set<ObjetoFormulario> getObjetoFormularios() {
		return this.objetoFormularios;
	}

	public void setObjetoFormularios(Set<ObjetoFormulario> objetoFormularios) {
		this.objetoFormularios = objetoFormularios;
	}

}
