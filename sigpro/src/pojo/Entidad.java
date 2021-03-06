package pojo;
// Generated Dec 13, 2017 9:28:15 AM by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad generated by hbm2java
 */
@Entity
@Table(name = "entidad", catalog = "sipro")
public class Entidad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9007715066381999444L;
	private EntidadId id;
	private String nombre;
	private String abreviatura;
	private Set<ProyectoImpacto> proyectoImpactos = new HashSet<ProyectoImpacto>(0);
	private Set<UnidadEjecutora> unidadEjecutoras = new HashSet<UnidadEjecutora>(0);

	public Entidad() {
	}

	public Entidad(EntidadId id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Entidad(EntidadId id, String nombre, String abreviatura, Set<ProyectoImpacto> proyectoImpactos,
			Set<UnidadEjecutora> unidadEjecutoras) {
		this.id = id;
		this.nombre = nombre;
		this.abreviatura = abreviatura;
		this.proyectoImpactos = proyectoImpactos;
		this.unidadEjecutoras = unidadEjecutoras;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "entidad", column = @Column(name = "entidad", nullable = false)),
			@AttributeOverride(name = "ejercicio", column = @Column(name = "ejercicio", nullable = false)) })
	public EntidadId getId() {
		return this.id;
	}

	public void setId(EntidadId id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, length = 1000)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "abreviatura", length = 100)
	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidad")
	public Set<ProyectoImpacto> getProyectoImpactos() {
		return this.proyectoImpactos;
	}

	public void setProyectoImpactos(Set<ProyectoImpacto> proyectoImpactos) {
		this.proyectoImpactos = proyectoImpactos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "entidad")
	public Set<UnidadEjecutora> getUnidadEjecutoras() {
		return this.unidadEjecutoras;
	}

	public void setUnidadEjecutoras(Set<UnidadEjecutora> unidadEjecutoras) {
		this.unidadEjecutoras = unidadEjecutoras;
	}

}
