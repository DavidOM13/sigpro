package pojo;
// Generated Sep 29, 2017 8:14:24 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RolUsuarioProyecto generated by hbm2java
 */
@Entity
@Table(name = "rol_usuario_proyecto", catalog = "sipro")
public class RolUsuarioProyecto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4544038347637757622L;
	private RolUsuarioProyectoId id;

	public RolUsuarioProyecto() {
	}

	public RolUsuarioProyecto(RolUsuarioProyectoId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "rol", column = @Column(name = "rol", nullable = false)),
			@AttributeOverride(name = "proyecto", column = @Column(name = "proyecto", nullable = false)),
			@AttributeOverride(name = "usuario", column = @Column(name = "usuario", nullable = false, length = 30)) })
	public RolUsuarioProyectoId getId() {
		return this.id;
	}

	public void setId(RolUsuarioProyectoId id) {
		this.id = id;
	}

}
