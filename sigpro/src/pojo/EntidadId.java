package pojo;
// Generated Nov 2, 2017 9:36:21 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EntidadId generated by hbm2java
 */
@Embeddable
public class EntidadId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8013891628878068484L;
	private int entidad;
	private int ejercicio;

	public EntidadId() {
	}

	public EntidadId(int entidad, int ejercicio) {
		this.entidad = entidad;
		this.ejercicio = ejercicio;
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
		if (!(other instanceof EntidadId))
			return false;
		EntidadId castOther = (EntidadId) other;

		return (this.getEntidad() == castOther.getEntidad()) && (this.getEjercicio() == castOther.getEjercicio());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getEntidad();
		result = 37 * result + this.getEjercicio();
		return result;
	}

}
