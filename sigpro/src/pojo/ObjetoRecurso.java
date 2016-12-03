package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ObjetoRecurso generated by hbm2java
 */
@Entity
@Table(name = "objeto_recurso", catalog = "sigpro")
public class ObjetoRecurso implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9006521502192178219L;
	private ObjetoRecursoId id;
	private Componente componente;
	private Producto producto;
	private Proyecto proyecto;
	private Recurso recurso;
	private RecursoPropiedad recursoPropiedad;

	public ObjetoRecurso() {
	}

	public ObjetoRecurso(ObjetoRecursoId id, Componente componente, Producto producto, Proyecto proyecto,
			Recurso recurso, RecursoPropiedad recursoPropiedad) {
		this.id = id;
		this.componente = componente;
		this.producto = producto;
		this.proyecto = proyecto;
		this.recurso = recurso;
		this.recursoPropiedad = recursoPropiedad;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "recursoid", column = @Column(name = "recursoid", nullable = false)),
			@AttributeOverride(name = "recursoPropiedadid", column = @Column(name = "recurso_propiedadid", nullable = false)),
			@AttributeOverride(name = "proyectoid", column = @Column(name = "proyectoid", nullable = false)),
			@AttributeOverride(name = "componenteid", column = @Column(name = "componenteid", nullable = false)),
			@AttributeOverride(name = "productoid", column = @Column(name = "productoid", nullable = false)),
			@AttributeOverride(name = "usuarioCreo", column = @Column(name = "usuario_creo", nullable = false, length = 30)),
			@AttributeOverride(name = "usuarioActualizo", column = @Column(name = "usuario_actualizo", length = 30)),
			@AttributeOverride(name = "fechaCreacion", column = @Column(name = "fecha_creacion", nullable = false, length = 19)),
			@AttributeOverride(name = "fechaActualizacion", column = @Column(name = "fecha_actualizacion", length = 19)),
			@AttributeOverride(name = "estado", column = @Column(name = "estado", nullable = false)),
			@AttributeOverride(name = "valorEntero", column = @Column(name = "valor_entero")),
			@AttributeOverride(name = "valorString", column = @Column(name = "valor_string", length = 4000)),
			@AttributeOverride(name = "valorDecimal", column = @Column(name = "valor_decimal", precision = 15)),
			@AttributeOverride(name = "valorTiempo", column = @Column(name = "valor_tiempo", length = 19)) })
	public ObjetoRecursoId getId() {
		return this.id;
	}

	public void setId(ObjetoRecursoId id) {
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
	@JoinColumn(name = "productoid", nullable = false, insertable = false, updatable = false)
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proyectoid", nullable = false, insertable = false, updatable = false)
	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recursoid", nullable = false, insertable = false, updatable = false)
	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recurso_propiedadid", nullable = false, insertable = false, updatable = false)
	public RecursoPropiedad getRecursoPropiedad() {
		return this.recursoPropiedad;
	}

	public void setRecursoPropiedad(RecursoPropiedad recursoPropiedad) {
		this.recursoPropiedad = recursoPropiedad;
	}

}
