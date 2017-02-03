package pojo;
// Generated Feb 3, 2017 9:12:33 AM by Hibernate Tools 5.2.0.CR1

import java.math.BigDecimal;
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
 * ComponentePropiedadValor generated by hbm2java
 */
@Entity
@Table(name = "componente_propiedad_valor", catalog = "sigpro")
public class ComponentePropiedadValor implements java.io.Serializable {

	private ComponentePropiedadValorId id;
	private Componente componente;
	private ComponentePropiedad componentePropiedad;
	private String valorString;
	private Integer valorEntero;
	private BigDecimal valorDecimal;
	private Date valorTiempo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public ComponentePropiedadValor() {
	}

	public ComponentePropiedadValor(ComponentePropiedadValorId id, Componente componente,
			ComponentePropiedad componentePropiedad, String usuarioCreo, Date fechaCreacion) {
		this.id = id;
		this.componente = componente;
		this.componentePropiedad = componentePropiedad;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
	}

	public ComponentePropiedadValor(ComponentePropiedadValorId id, Componente componente,
			ComponentePropiedad componentePropiedad, String valorString, Integer valorEntero, BigDecimal valorDecimal,
			Date valorTiempo, String usuarioCreo, String usuarioActualizo, Date fechaCreacion,
			Date fechaActualizacion) {
		this.id = id;
		this.componente = componente;
		this.componentePropiedad = componentePropiedad;
		this.valorString = valorString;
		this.valorEntero = valorEntero;
		this.valorDecimal = valorDecimal;
		this.valorTiempo = valorTiempo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "componenteid", column = @Column(name = "componenteid", nullable = false)),
			@AttributeOverride(name = "componentePropiedadid", column = @Column(name = "componente_propiedadid", nullable = false)) })
	public ComponentePropiedadValorId getId() {
		return this.id;
	}

	public void setId(ComponentePropiedadValorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "componenteid", nullable = false, insertable = false, updatable = false)
	public Componente getComponente() {
		return this.componente;
	}

	public void setComponente(Componente componente) {
		this.componente = componente;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "componente_propiedadid", nullable = false, insertable = false, updatable = false)
	public ComponentePropiedad getComponentePropiedad() {
		return this.componentePropiedad;
	}

	public void setComponentePropiedad(ComponentePropiedad componentePropiedad) {
		this.componentePropiedad = componentePropiedad;
	}

	@Column(name = "valor_string", length = 4000)
	public String getValorString() {
		return this.valorString;
	}

	public void setValorString(String valorString) {
		this.valorString = valorString;
	}

	@Column(name = "valor_entero")
	public Integer getValorEntero() {
		return this.valorEntero;
	}

	public void setValorEntero(Integer valorEntero) {
		this.valorEntero = valorEntero;
	}

	@Column(name = "valor_decimal", precision = 15)
	public BigDecimal getValorDecimal() {
		return this.valorDecimal;
	}

	public void setValorDecimal(BigDecimal valorDecimal) {
		this.valorDecimal = valorDecimal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "valor_tiempo", length = 19)
	public Date getValorTiempo() {
		return this.valorTiempo;
	}

	public void setValorTiempo(Date valorTiempo) {
		this.valorTiempo = valorTiempo;
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

}
