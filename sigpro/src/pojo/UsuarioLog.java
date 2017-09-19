package pojo;
// Generated Sep 19, 2017 6:41:06 AM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UsuarioLog generated by hbm2java
 */
@Entity
@Table(name = "usuario_log", catalog = "sipro")
public class UsuarioLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5914325121762239640L;
	private UsuarioLogId id;

	public UsuarioLog() {
	}

	public UsuarioLog(UsuarioLogId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "usuario", column = @Column(name = "usuario", length = 30)),
			@AttributeOverride(name = "fecha", column = @Column(name = "fecha", length = 19)) })
	public UsuarioLogId getId() {
		return this.id;
	}

	public void setId(UsuarioLogId id) {
		this.id = id;
	}

}
