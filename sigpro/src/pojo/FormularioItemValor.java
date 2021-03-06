package pojo;
// Generated Dec 13, 2017 9:28:15 AM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * FormularioItemValor generated by hbm2java
 */
@Entity
@Table(name = "formulario_item_valor", catalog = "sipro")
public class FormularioItemValor implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -473359597829774496L;
	private FormularioItemValorId id;
	private FormularioItem formularioItem;
	private ObjetoFormulario objetoFormulario;
	private int valorEntero;
	private String valorString;
	private Date valorTiempo;
	private BigDecimal valorDecimal;
	private int proyectoid;
	private int componenteid;
	private int productoid;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;

	public FormularioItemValor() {
	}

	public FormularioItemValor(FormularioItemValorId id, FormularioItem formularioItem,
			ObjetoFormulario objetoFormulario, int valorEntero, int proyectoid, int componenteid, int productoid,
			String usuarioCreo, Date fechaCreacion) {
		this.id = id;
		this.formularioItem = formularioItem;
		this.objetoFormulario = objetoFormulario;
		this.valorEntero = valorEntero;
		this.proyectoid = proyectoid;
		this.componenteid = componenteid;
		this.productoid = productoid;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
	}

	public FormularioItemValor(FormularioItemValorId id, FormularioItem formularioItem,
			ObjetoFormulario objetoFormulario, int valorEntero, String valorString, Date valorTiempo,
			BigDecimal valorDecimal, int proyectoid, int componenteid, int productoid, String usuarioCreo,
			String usuarioActualizo, Date fechaCreacion, Date fechaActualizacion) {
		this.id = id;
		this.formularioItem = formularioItem;
		this.objetoFormulario = objetoFormulario;
		this.valorEntero = valorEntero;
		this.valorString = valorString;
		this.valorTiempo = valorTiempo;
		this.valorDecimal = valorDecimal;
		this.proyectoid = proyectoid;
		this.componenteid = componenteid;
		this.productoid = productoid;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "formularioItemid", column = @Column(name = "formulario_itemid", nullable = false)),
			@AttributeOverride(name = "objetoFormularioformularioid", column = @Column(name = "objeto_formularioformularioid", nullable = false)),
			@AttributeOverride(name = "objetoFormularioobjetoTipoid", column = @Column(name = "objeto_formularioobjeto_tipoid", nullable = false)),
			@AttributeOverride(name = "objetoFormularioobjetoId", column = @Column(name = "objeto_formularioobjeto_id", nullable = false)) })
	public FormularioItemValorId getId() {
		return this.id;
	}

	public void setId(FormularioItemValorId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formulario_itemid", nullable = false, insertable = false, updatable = false)
	public FormularioItem getFormularioItem() {
		return this.formularioItem;
	}

	public void setFormularioItem(FormularioItem formularioItem) {
		this.formularioItem = formularioItem;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "objeto_formularioformularioid", referencedColumnName = "formularioid", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "objeto_formularioobjeto_tipoid", referencedColumnName = "objeto_tipo", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "objeto_formularioobjeto_id", referencedColumnName = "objeto_id", nullable = false, insertable = false, updatable = false) })
	public ObjetoFormulario getObjetoFormulario() {
		return this.objetoFormulario;
	}

	public void setObjetoFormulario(ObjetoFormulario objetoFormulario) {
		this.objetoFormulario = objetoFormulario;
	}

	@Column(name = "valor_entero", nullable = false)
	public int getValorEntero() {
		return this.valorEntero;
	}

	public void setValorEntero(int valorEntero) {
		this.valorEntero = valorEntero;
	}

	@Column(name = "valor_string", length = 4000)
	public String getValorString() {
		return this.valorString;
	}

	public void setValorString(String valorString) {
		this.valorString = valorString;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "valor_tiempo", length = 19)
	public Date getValorTiempo() {
		return this.valorTiempo;
	}

	public void setValorTiempo(Date valorTiempo) {
		this.valorTiempo = valorTiempo;
	}

	@Column(name = "valor_decimal", precision = 15)
	public BigDecimal getValorDecimal() {
		return this.valorDecimal;
	}

	public void setValorDecimal(BigDecimal valorDecimal) {
		this.valorDecimal = valorDecimal;
	}

	@Column(name = "proyectoid", nullable = false)
	public int getProyectoid() {
		return this.proyectoid;
	}

	public void setProyectoid(int proyectoid) {
		this.proyectoid = proyectoid;
	}

	@Column(name = "componenteid", nullable = false)
	public int getComponenteid() {
		return this.componenteid;
	}

	public void setComponenteid(int componenteid) {
		this.componenteid = componenteid;
	}

	@Column(name = "productoid", nullable = false)
	public int getProductoid() {
		return this.productoid;
	}

	public void setProductoid(int productoid) {
		this.productoid = productoid;
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
