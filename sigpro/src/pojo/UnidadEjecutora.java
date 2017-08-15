package pojo;
// Generated Aug 14, 2017 12:17:40 PM by Hibernate Tools 5.2.3.Final

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

/**
 * UnidadEjecutora generated by hbm2java
 */
@Entity
@Table(name = "unidad_ejecutora", catalog = "sipro")
public class UnidadEjecutora implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8956254344413330777L;
	private Integer unidadEjecutora;
	private Entidad entidad;
	private String nombre;
	private Set<Componente> componentes = new HashSet<Componente>(0);
	private Set<Colaborador> colaboradors = new HashSet<Colaborador>(0);
	private Set<Subproducto> subproductos = new HashSet<Subproducto>(0);
	private Set<Prestamo> prestamos = new HashSet<Prestamo>(0);
	private Set<Producto> productos = new HashSet<Producto>(0);
	private Set<Proyecto> proyectos = new HashSet<Proyecto>(0);

	public UnidadEjecutora() {
	}

	public UnidadEjecutora(Entidad entidad, String nombre) {
		this.entidad = entidad;
		this.nombre = nombre;
	}

	public UnidadEjecutora(Entidad entidad, String nombre, Set<Componente> componentes, Set<Colaborador> colaboradors,
			Set<Subproducto> subproductos, Set<Prestamo> prestamos, Set<Producto> productos, Set<Proyecto> proyectos) {
		this.entidad = entidad;
		this.nombre = nombre;
		this.componentes = componentes;
		this.colaboradors = colaboradors;
		this.subproductos = subproductos;
		this.prestamos = prestamos;
		this.productos = productos;
		this.proyectos = proyectos;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "unidad_ejecutora", unique = true, nullable = false)
	public Integer getUnidadEjecutora() {
		return this.unidadEjecutora;
	}

	public void setUnidadEjecutora(Integer unidadEjecutora) {
		this.unidadEjecutora = unidadEjecutora;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entidadentidad", nullable = false)
	public Entidad getEntidad() {
		return this.entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	@Column(name = "nombre", nullable = false, length = 1000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Componente> getComponentes() {
		return this.componentes;
	}

	public void setComponentes(Set<Componente> componentes) {
		this.componentes = componentes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Colaborador> getColaboradors() {
		return this.colaboradors;
	}

	public void setColaboradors(Set<Colaborador> colaboradors) {
		this.colaboradors = colaboradors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Subproducto> getSubproductos() {
		return this.subproductos;
	}

	public void setSubproductos(Set<Subproducto> subproductos) {
		this.subproductos = subproductos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Prestamo> getPrestamos() {
		return this.prestamos;
	}

	public void setPrestamos(Set<Prestamo> prestamos) {
		this.prestamos = prestamos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadEjecutora")
	public Set<Proyecto> getProyectos() {
		return this.proyectos;
	}

	public void setProyectos(Set<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

}
