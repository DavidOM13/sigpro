package pojo;
// Generated May 15, 2017 4:04:46 PM by Hibernate Tools 5.2.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * CtipoPropiedadId generated by hbm2java
 */
@Embeddable
public class CtipoPropiedadId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8552251174636414201L;
	private int componenteTipoid;
	private int componentePropiedadid;

	public CtipoPropiedadId() {
	}

	public CtipoPropiedadId(int componenteTipoid, int componentePropiedadid) {
		this.componenteTipoid = componenteTipoid;
		this.componentePropiedadid = componentePropiedadid;
	}

	@Column(name = "componente_tipoid", nullable = false)
	public int getComponenteTipoid() {
		return this.componenteTipoid;
	}

	public void setComponenteTipoid(int componenteTipoid) {
		this.componenteTipoid = componenteTipoid;
	}

	@Column(name = "componente_propiedadid", nullable = false)
	public int getComponentePropiedadid() {
		return this.componentePropiedadid;
	}

	public void setComponentePropiedadid(int componentePropiedadid) {
		this.componentePropiedadid = componentePropiedadid;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CtipoPropiedadId))
			return false;
		CtipoPropiedadId castOther = (CtipoPropiedadId) other;

		return (this.getComponenteTipoid() == castOther.getComponenteTipoid())
				&& (this.getComponentePropiedadid() == castOther.getComponentePropiedadid());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getComponenteTipoid();
		result = 37 * result + this.getComponentePropiedadid();
		return result;
	}

}
