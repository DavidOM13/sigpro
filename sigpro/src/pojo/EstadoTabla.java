package pojo;
// Generated Feb 3, 2017 9:12:33 AM by Hibernate Tools 5.2.0.CR1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * EstadoTabla generated by hbm2java
 */
@Entity
@Table(name = "estado_tabla", catalog = "sigpro")
public class EstadoTabla implements java.io.Serializable {

	private EstadoTablaId id;
	private String valores;

	public EstadoTabla() {
	}

	public EstadoTabla(EstadoTablaId id, String valores) {
		this.id = id;
		this.valores = valores;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "usuario", column = @Column(name = "usuario", nullable = false, length = 30)),
			@AttributeOverride(name = "tabla", column = @Column(name = "tabla", nullable = false, length = 50)) })
	public EstadoTablaId getId() {
		return this.id;
	}

	public void setId(EstadoTablaId id) {
		this.id = id;
	}

	@Column(name = "valores", nullable = false, length = 1000)
	public String getValores() {
		return this.valores;
	}

	public void setValores(String valores) {
		this.valores = valores;
	}

}
