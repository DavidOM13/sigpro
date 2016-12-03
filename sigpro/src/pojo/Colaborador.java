package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Colaborador generated by hbm2java
 */
@Entity
@Table(name = "colaborador", catalog = "sigpro")
public class Colaborador implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6851928415964749645L;
	private Integer id;
	private UnidadEjecutora unidadEjecutora;
	private Usuario usuario;
	private String pnombre;
	private String snombre;
	private String papellido;
	private String sapellido;
	private Integer cui;

	public Colaborador() {
	}

	public Colaborador(UnidadEjecutora unidadEjecutora, Usuario usuario) {
		this.unidadEjecutora = unidadEjecutora;
		this.usuario = usuario;
	}

	public Colaborador(UnidadEjecutora unidadEjecutora, Usuario usuario, String pnombre, String snombre,
			String papellido, String sapellido, Integer cui) {
		this.unidadEjecutora = unidadEjecutora;
		this.usuario = usuario;
		this.pnombre = pnombre;
		this.snombre = snombre;
		this.papellido = papellido;
		this.sapellido = sapellido;
		this.cui = cui;
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
	@JoinColumn(name = "unidad_ejecutoraunidad_ejecutora", nullable = false)
	public UnidadEjecutora getUnidadEjecutora() {
		return this.unidadEjecutora;
	}

	public void setUnidadEjecutora(UnidadEjecutora unidadEjecutora) {
		this.unidadEjecutora = unidadEjecutora;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuariousuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "pnombre")
	public String getPnombre() {
		return this.pnombre;
	}

	public void setPnombre(String pnombre) {
		this.pnombre = pnombre;
	}

	@Column(name = "snombre")
	public String getSnombre() {
		return this.snombre;
	}

	public void setSnombre(String snombre) {
		this.snombre = snombre;
	}

	@Column(name = "papellido")
	public String getPapellido() {
		return this.papellido;
	}

	public void setPapellido(String papellido) {
		this.papellido = papellido;
	}

	@Column(name = "sapellido")
	public String getSapellido() {
		return this.sapellido;
	}

	public void setSapellido(String sapellido) {
		this.sapellido = sapellido;
	}

	@Column(name = "cui")
	public Integer getCui() {
		return this.cui;
	}

	public void setCui(Integer cui) {
		this.cui = cui;
	}

}
