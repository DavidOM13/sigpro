package pojo;
// Generated Nov 9, 2017 5:32:35 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProyectoUsuarioId generated by hbm2java
 */
@Embeddable
public class ProyectoUsuarioId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5272437490933762966L;
	private int proyectoid;
	private String usuario;

	public ProyectoUsuarioId() {
	}

	public ProyectoUsuarioId(int proyectoid, String usuario) {
		this.proyectoid = proyectoid;
		this.usuario = usuario;
	}

	@Column(name = "proyectoid", nullable = false)
	public int getProyectoid() {
		return this.proyectoid;
	}

	public void setProyectoid(int proyectoid) {
		this.proyectoid = proyectoid;
	}

	@Column(name = "usuario", nullable = false, length = 30)
	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProyectoUsuarioId))
			return false;
		ProyectoUsuarioId castOther = (ProyectoUsuarioId) other;

		return (this.getProyectoid() == castOther.getProyectoid())
				&& ((this.getUsuario() == castOther.getUsuario()) || (this.getUsuario() != null
						&& castOther.getUsuario() != null && this.getUsuario().equals(castOther.getUsuario())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProyectoid();
		result = 37 * result + (getUsuario() == null ? 0 : this.getUsuario().hashCode());
		return result;
	}

}
