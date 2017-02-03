package pojo;
// Generated Feb 3, 2017 9:12:33 AM by Hibernate Tools 5.2.0.CR1

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

	private Integer id;
	private UnidadEjecutora unidadEjecutora;
	private Usuario usuario;
	private String pnombre;
	private String snombre;
	private String papellido;
	private String sapellido;
	private Long cui;

	public Colaborador() {
	}

	public Colaborador(UnidadEjecutora unidadEjecutora, Usuario usuario, String pnombre, String papellido, Long cui) {
		this.unidadEjecutora = unidadEjecutora;
		this.usuario = usuario;
		this.pnombre = pnombre;
		this.papellido = papellido;
		this.cui = cui;
	}

	public Colaborador(UnidadEjecutora unidadEjecutora, Usuario usuario, String pnombre, String snombre,
			String papellido, String sapellido, Long cui) {
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

	@Column(name = "pnombre", nullable = false)
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

	@Column(name = "papellido", nullable = false)
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

	@Column(name = "cui", nullable = false)
	public Long getCui() {
		return this.cui;
	}

	public void setCui(Long cui) {
		this.cui = cui;
	}

}
