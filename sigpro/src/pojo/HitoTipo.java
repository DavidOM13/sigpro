package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HitoTipo generated by hbm2java
 */
@Entity
@Table(name = "hito_tipo", catalog = "sigpro")
public class HitoTipo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5259416777540558823L;
	private Integer id;
	private String nombre;
	private String descripcion;
	private int estado;
	private Set<Hito> hitos = new HashSet<Hito>(0);

	public HitoTipo() {
	}

	public HitoTipo(int estado) {
		this.estado = estado;
	}

	public HitoTipo(String nombre, String descripcion, int estado, Set<Hito> hitos) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.estado = estado;
		this.hitos = hitos;
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

	@Column(name = "nombre", length = 1000)
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

	@Column(name = "estado", nullable = false)
	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "hitoTipo")
	public Set<Hito> getHitos() {
		return this.hitos;
	}

	public void setHitos(Set<Hito> hitos) {
		this.hitos = hitos;
	}

}
