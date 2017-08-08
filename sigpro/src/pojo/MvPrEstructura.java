package pojo;
// Generated Aug 6, 2017 10:04:13 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MvPrEstructura generated by hbm2java
 */
@Entity
@Table(name = "mv_pr_estructura", catalog = "sipro")
public class MvPrEstructura implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9107978885989862821L;
	private Integer id;
	private Integer objetoId;
	private Integer objetoTipo;
	private String nombre;
	private Integer fuente;
	private Integer organismo;
	private Integer correlativo;
	private Integer programa;
	private Integer subprograma;
	private Integer proyecto;
	private Integer actividad;
	private Integer obra;
	private Integer objetoIdPadre;
	private Integer objetoTipoPadre;

	public MvPrEstructura() {
	}

	public MvPrEstructura(Integer objetoId, Integer objetoTipo, String nombre, Integer fuente, Integer organismo,
			Integer correlativo, Integer programa, Integer subprograma, Integer proyecto, Integer actividad,
			Integer obra, Integer objetoIdPadre, Integer objetoTipoPadre) {
		this.objetoId = objetoId;
		this.objetoTipo = objetoTipo;
		this.nombre = nombre;
		this.fuente = fuente;
		this.organismo = organismo;
		this.correlativo = correlativo;
		this.programa = programa;
		this.subprograma = subprograma;
		this.proyecto = proyecto;
		this.actividad = actividad;
		this.obra = obra;
		this.objetoIdPadre = objetoIdPadre;
		this.objetoTipoPadre = objetoTipoPadre;
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

	@Column(name = "objeto_id")
	public Integer getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(Integer objetoId) {
		this.objetoId = objetoId;
	}

	@Column(name = "objeto_tipo")
	public Integer getObjetoTipo() {
		return this.objetoTipo;
	}

	public void setObjetoTipo(Integer objetoTipo) {
		this.objetoTipo = objetoTipo;
	}

	@Column(name = "nombre", length = 300)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "fuente")
	public Integer getFuente() {
		return this.fuente;
	}

	public void setFuente(Integer fuente) {
		this.fuente = fuente;
	}

	@Column(name = "organismo")
	public Integer getOrganismo() {
		return this.organismo;
	}

	public void setOrganismo(Integer organismo) {
		this.organismo = organismo;
	}

	@Column(name = "correlativo")
	public Integer getCorrelativo() {
		return this.correlativo;
	}

	public void setCorrelativo(Integer correlativo) {
		this.correlativo = correlativo;
	}

	@Column(name = "programa")
	public Integer getPrograma() {
		return this.programa;
	}

	public void setPrograma(Integer programa) {
		this.programa = programa;
	}

	@Column(name = "subprograma")
	public Integer getSubprograma() {
		return this.subprograma;
	}

	public void setSubprograma(Integer subprograma) {
		this.subprograma = subprograma;
	}

	@Column(name = "proyecto")
	public Integer getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Integer proyecto) {
		this.proyecto = proyecto;
	}

	@Column(name = "actividad")
	public Integer getActividad() {
		return this.actividad;
	}

	public void setActividad(Integer actividad) {
		this.actividad = actividad;
	}

	@Column(name = "obra")
	public Integer getObra() {
		return this.obra;
	}

	public void setObra(Integer obra) {
		this.obra = obra;
	}

	@Column(name = "objeto_id_padre")
	public Integer getObjetoIdPadre() {
		return this.objetoIdPadre;
	}

	public void setObjetoIdPadre(Integer objetoIdPadre) {
		this.objetoIdPadre = objetoIdPadre;
	}

	@Column(name = "objeto_tipo_padre")
	public Integer getObjetoTipoPadre() {
		return this.objetoTipoPadre;
	}

	public void setObjetoTipoPadre(Integer objetoTipoPadre) {
		this.objetoTipoPadre = objetoTipoPadre;
	}

}
