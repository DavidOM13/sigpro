package pojo;
// Generated Sep 11, 2017 3:37:18 PM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PlanAdquisicionesDetalle generated by hbm2java
 */
@Entity
@Table(name = "plan_adquisiciones_detalle", catalog = "sipro")
public class PlanAdquisicionesDetalle implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4789316518902979027L;
	private Integer id;
	private CategoriaAdquisicion categoriaAdquisicion;
	private PlanAdquisiciones planAdquisiciones;
	private Integer tipoAdquisicion;
	private String unidadMedida;
	private Integer cantidad;
	private BigDecimal total;
	private BigDecimal precioUnitario;
	private Date preparacionDocPlanificado;
	private Date preparacionDocReal;
	private Date lanzamientoEventoPlanificado;
	private Date lanzamientoEventoReal;
	private Date recepcionOfertasPlanificado;
	private Date recepcionOfertasReal;
	private Date adjudicacionPlanificado;
	private Date adjudicacionReal;
	private Date firmaContratoPlanificado;
	private Date firmaContratoReal;
	private int objetoId;
	private int objetoTipo;
	private String usuarioCreo;
	private String usuarioActualizo;
	private Date fechaCreacion;
	private Date fechaActualizacion;
	private int estado;
	private Integer bloqueado;
	private String numeroContrato;
	private BigDecimal montoContrato;
	private Long nog;

	public PlanAdquisicionesDetalle() {
	}

	public PlanAdquisicionesDetalle(PlanAdquisiciones planAdquisiciones, int objetoId, int objetoTipo,
			String usuarioCreo, Date fechaCreacion, int estado) {
		this.planAdquisiciones = planAdquisiciones;
		this.objetoId = objetoId;
		this.objetoTipo = objetoTipo;
		this.usuarioCreo = usuarioCreo;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}

	public PlanAdquisicionesDetalle(CategoriaAdquisicion categoriaAdquisicion, PlanAdquisiciones planAdquisiciones,
			Integer tipoAdquisicion, String unidadMedida, Integer cantidad, BigDecimal total, BigDecimal precioUnitario,
			Date preparacionDocPlanificado, Date preparacionDocReal, Date lanzamientoEventoPlanificado,
			Date lanzamientoEventoReal, Date recepcionOfertasPlanificado, Date recepcionOfertasReal,
			Date adjudicacionPlanificado, Date adjudicacionReal, Date firmaContratoPlanificado, Date firmaContratoReal,
			int objetoId, int objetoTipo, String usuarioCreo, String usuarioActualizo, Date fechaCreacion,
			Date fechaActualizacion, int estado, Integer bloqueado, String numeroContrato, BigDecimal montoContrato,
			Long nog) {
		this.categoriaAdquisicion = categoriaAdquisicion;
		this.planAdquisiciones = planAdquisiciones;
		this.tipoAdquisicion = tipoAdquisicion;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
		this.total = total;
		this.precioUnitario = precioUnitario;
		this.preparacionDocPlanificado = preparacionDocPlanificado;
		this.preparacionDocReal = preparacionDocReal;
		this.lanzamientoEventoPlanificado = lanzamientoEventoPlanificado;
		this.lanzamientoEventoReal = lanzamientoEventoReal;
		this.recepcionOfertasPlanificado = recepcionOfertasPlanificado;
		this.recepcionOfertasReal = recepcionOfertasReal;
		this.adjudicacionPlanificado = adjudicacionPlanificado;
		this.adjudicacionReal = adjudicacionReal;
		this.firmaContratoPlanificado = firmaContratoPlanificado;
		this.firmaContratoReal = firmaContratoReal;
		this.objetoId = objetoId;
		this.objetoTipo = objetoTipo;
		this.usuarioCreo = usuarioCreo;
		this.usuarioActualizo = usuarioActualizo;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.estado = estado;
		this.bloqueado = bloqueado;
		this.numeroContrato = numeroContrato;
		this.montoContrato = montoContrato;
		this.nog = nog;
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
	@JoinColumn(name = "categoria_adquisicion")
	public CategoriaAdquisicion getCategoriaAdquisicion() {
		return this.categoriaAdquisicion;
	}

	public void setCategoriaAdquisicion(CategoriaAdquisicion categoriaAdquisicion) {
		this.categoriaAdquisicion = categoriaAdquisicion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_adquisicion_id", nullable = false)
	public PlanAdquisiciones getPlanAdquisiciones() {
		return this.planAdquisiciones;
	}

	public void setPlanAdquisiciones(PlanAdquisiciones planAdquisiciones) {
		this.planAdquisiciones = planAdquisiciones;
	}

	@Column(name = "tipo_adquisicion")
	public Integer getTipoAdquisicion() {
		return this.tipoAdquisicion;
	}

	public void setTipoAdquisicion(Integer tipoAdquisicion) {
		this.tipoAdquisicion = tipoAdquisicion;
	}

	@Column(name = "unidad_medida", length = 30)
	public String getUnidadMedida() {
		return this.unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@Column(name = "cantidad")
	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Column(name = "total", precision = 15)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Column(name = "precio_unitario", precision = 15)
	public BigDecimal getPrecioUnitario() {
		return this.precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "preparacion_doc_planificado", length = 19)
	public Date getPreparacionDocPlanificado() {
		return this.preparacionDocPlanificado;
	}

	public void setPreparacionDocPlanificado(Date preparacionDocPlanificado) {
		this.preparacionDocPlanificado = preparacionDocPlanificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "preparacion_doc_real", length = 19)
	public Date getPreparacionDocReal() {
		return this.preparacionDocReal;
	}

	public void setPreparacionDocReal(Date preparacionDocReal) {
		this.preparacionDocReal = preparacionDocReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lanzamiento_evento_planificado", length = 19)
	public Date getLanzamientoEventoPlanificado() {
		return this.lanzamientoEventoPlanificado;
	}

	public void setLanzamientoEventoPlanificado(Date lanzamientoEventoPlanificado) {
		this.lanzamientoEventoPlanificado = lanzamientoEventoPlanificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lanzamiento_evento_real", length = 19)
	public Date getLanzamientoEventoReal() {
		return this.lanzamientoEventoReal;
	}

	public void setLanzamientoEventoReal(Date lanzamientoEventoReal) {
		this.lanzamientoEventoReal = lanzamientoEventoReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "recepcion_ofertas_planificado", length = 19)
	public Date getRecepcionOfertasPlanificado() {
		return this.recepcionOfertasPlanificado;
	}

	public void setRecepcionOfertasPlanificado(Date recepcionOfertasPlanificado) {
		this.recepcionOfertasPlanificado = recepcionOfertasPlanificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "recepcion_ofertas_real", length = 19)
	public Date getRecepcionOfertasReal() {
		return this.recepcionOfertasReal;
	}

	public void setRecepcionOfertasReal(Date recepcionOfertasReal) {
		this.recepcionOfertasReal = recepcionOfertasReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "adjudicacion_planificado", length = 19)
	public Date getAdjudicacionPlanificado() {
		return this.adjudicacionPlanificado;
	}

	public void setAdjudicacionPlanificado(Date adjudicacionPlanificado) {
		this.adjudicacionPlanificado = adjudicacionPlanificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "adjudicacion_real", length = 19)
	public Date getAdjudicacionReal() {
		return this.adjudicacionReal;
	}

	public void setAdjudicacionReal(Date adjudicacionReal) {
		this.adjudicacionReal = adjudicacionReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firma_contrato_planificado", length = 19)
	public Date getFirmaContratoPlanificado() {
		return this.firmaContratoPlanificado;
	}

	public void setFirmaContratoPlanificado(Date firmaContratoPlanificado) {
		this.firmaContratoPlanificado = firmaContratoPlanificado;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "firma_contrato_real", length = 19)
	public Date getFirmaContratoReal() {
		return this.firmaContratoReal;
	}

	public void setFirmaContratoReal(Date firmaContratoReal) {
		this.firmaContratoReal = firmaContratoReal;
	}

	@Column(name = "objeto_id", nullable = false)
	public int getObjetoId() {
		return this.objetoId;
	}

	public void setObjetoId(int objetoId) {
		this.objetoId = objetoId;
	}

	@Column(name = "objeto_tipo", nullable = false)
	public int getObjetoTipo() {
		return this.objetoTipo;
	}

	public void setObjetoTipo(int objetoTipo) {
		this.objetoTipo = objetoTipo;
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

	@Column(name = "bloqueado")
	public Integer getBloqueado() {
		return this.bloqueado;
	}

	public void setBloqueado(Integer bloqueado) {
		this.bloqueado = bloqueado;
	}

	@Column(name = "numero_contrato", length = 45)
	public String getNumeroContrato() {
		return this.numeroContrato;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	@Column(name = "monto_contrato", precision = 15)
	public BigDecimal getMontoContrato() {
		return this.montoContrato;
	}

	public void setMontoContrato(BigDecimal montoContrato) {
		this.montoContrato = montoContrato;
	}

	@Column(name = "nog")
	public Long getNog() {
		return this.nog;
	}

	public void setNog(Long nog) {
		this.nog = nog;
	}

}
