package pojo;
// Generated Aug 6, 2017 10:04:13 PM by Hibernate Tools 5.2.3.Final

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
 * Riesgo generated by hbm2java
 */
@Entity
@Table(name = "riesgo", catalog = "sipro")
public class Riesgo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2650041544697269183L;
	private Integer id;
	private Colaborador colaborador;
	private RiesgoTipo riesgoTipo;
	private String nombre;
	private String descripcion;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private String imapctoProyectado;
	private Integer impacto;
	private Integer puntuacionImpacto;
	private Integer probabilidad;
	private String gatillosSintomas;
	private String respuesta;
	private String riesgosSegundarios;
	private Integer ejecutado;
	private Date fechaEjecucion;
	private Set<ObjetoRiesgo> objetoRiesgos = new HashSet<ObjetoRiesgo>(0);
	private Set<RiesgoPropiedadValor> riesgoPropiedadValors = new HashSet<RiesgoPropiedadValor>(0);

	public Riesgo() {
	}

	public Riesgo(RiesgoTipo riesgoTipo, String nombre, String usuarioCreo, Date fechaCreacion, int estado) {
		this.riesgoTipo = riesgoTipo;
		this.nombre = nombre;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public Riesgo(Colaborador colaborador, RiesgoTipo riesgoTipo, String nombre, String descripcion, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion, int estado, String imapctoProyectado,
			Integer impacto, Integer puntuacionImpacto, Integer probabilidad, String gatillosSintomas, String respuesta,
			String riesgosSegundarios, Integer ejecutado, Date fechaEjecucion, Set<ObjetoRiesgo> objetoRiesgos,
			Set<RiesgoPropiedadValor> riesgoPropiedadValors) {
		this.colaborador = colaborador;
		this.riesgoTipo = riesgoTipo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.imapctoProyectado = imapctoProyectado;
		this.impacto = impacto;
		this.puntuacionImpacto = puntuacionImpacto;
		this.probabilidad = probabilidad;
		this.gatillosSintomas = gatillosSintomas;
		this.respuesta = respuesta;
		this.riesgosSegundarios = riesgosSegundarios;
		this.ejecutado = ejecutado;
		this.fechaEjecucion = fechaEjecucion;
		this.objetoRiesgos = objetoRiesgos;
		this.riesgoPropiedadValors = riesgoPropiedadValors;
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
	@JoinColumn(name = "colaboradorid")
	public Colaborador getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "riesgo_tipoid", nullable = false)
	public RiesgoTipo getRiesgoTipo() {
		return this.riesgoTipo;
	}

	public void setRiesgoTipo(RiesgoTipo riesgoTipo) {
		this.riesgoTipo = riesgoTipo;
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

	@Column(name = "imapcto_proyectado", length = 1000)
	public String getImapctoProyectado() {
		return this.imapctoProyectado;
	}

	public void setImapctoProyectado(String imapctoProyectado) {
		this.imapctoProyectado = imapctoProyectado;
	}

	@Column(name = "impacto")
	public Integer getImpacto() {
		return this.impacto;
	}

	public void setImpacto(Integer impacto) {
		this.impacto = impacto;
	}

	@Column(name = "puntuacion_impacto")
	public Integer getPuntuacionImpacto() {
		return this.puntuacionImpacto;
	}

	public void setPuntuacionImpacto(Integer puntuacionImpacto) {
		this.puntuacionImpacto = puntuacionImpacto;
	}

	@Column(name = "probabilidad")
	public Integer getProbabilidad() {
		return this.probabilidad;
	}

	public void setProbabilidad(Integer probabilidad) {
		this.probabilidad = probabilidad;
	}

	@Column(name = "gatillos_sintomas", length = 1000)
	public String getGatillosSintomas() {
		return this.gatillosSintomas;
	}

	public void setGatillosSintomas(String gatillosSintomas) {
		this.gatillosSintomas = gatillosSintomas;
	}

	@Column(name = "respuesta", length = 1000)
	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	@Column(name = "riesgos_segundarios", length = 1000)
	public String getRiesgosSegundarios() {
		return this.riesgosSegundarios;
	}

	public void setRiesgosSegundarios(String riesgosSegundarios) {
		this.riesgosSegundarios = riesgosSegundarios;
	}

	@Column(name = "ejecutado")
	public Integer getEjecutado() {
		return this.ejecutado;
	}

	public void setEjecutado(Integer ejecutado) {
		this.ejecutado = ejecutado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ejecucion", length = 19)
	public Date getFechaEjecucion() {
		return this.fechaEjecucion;
	}

	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "riesgo")
	public Set<ObjetoRiesgo> getObjetoRiesgos() {
		return this.objetoRiesgos;
	}

	public void setObjetoRiesgos(Set<ObjetoRiesgo> objetoRiesgos) {
		this.objetoRiesgos = objetoRiesgos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "riesgo")
	public Set<RiesgoPropiedadValor> getRiesgoPropiedadValors() {
		return this.riesgoPropiedadValors;
	}

	public void setRiesgoPropiedadValors(Set<RiesgoPropiedadValor> riesgoPropiedadValors) {
		this.riesgoPropiedadValors = riesgoPropiedadValors;
	}

}
