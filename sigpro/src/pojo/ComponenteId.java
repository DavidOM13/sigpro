package pojo;
// Generated Sep 11, 2017 2:41:53 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ComponenteId generated by hbm2java
 */
@Embeddable
public class ComponenteId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9027839418649959787L;
	private int id;
	private int entidad;
	private int ejercicio;

	public ComponenteId() {
	}

	public ComponenteId(int id, int entidad, int ejercicio) {
		this.id = id;
		this.entidad = entidad;
		this.ejercicio = ejercicio;
	}

	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "entidad", nullable = false)
	public int getEntidad() {
		return this.entidad;
	}

	public void setEntidad(int entidad) {
		this.entidad = entidad;
	}

	@Column(name = "ejercicio", nullable = false)
	public int getEjercicio() {
		return this.ejercicio;
	}

	public void setEjercicio(int ejercicio) {
		this.ejercicio = ejercicio;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ComponenteId))
			return false;
		ComponenteId castOther = (ComponenteId) other;

		return (this.getId() == castOther.getId()) && (this.getEntidad() == castOther.getEntidad())
				&& (this.getEjercicio() == castOther.getEjercicio());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + this.getEntidad();
		result = 37 * result + this.getEjercicio();
		return result;
	}

}
