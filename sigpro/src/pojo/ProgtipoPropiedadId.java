package pojo;
// Generated 22/02/2017 11:25:59 AM by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProgtipoPropiedadId generated by hbm2java
 */
@Embeddable
public class ProgtipoPropiedadId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5154611946077674905L;
	private int programaTipoid;
	private int programaPropiedadid;

	public ProgtipoPropiedadId() {
	}

	public ProgtipoPropiedadId(int programaTipoid, int programaPropiedadid) {
		this.programaTipoid = programaTipoid;
		this.programaPropiedadid = programaPropiedadid;
	}

	@Column(name = "programa_tipoid", nullable = false)
	public int getProgramaTipoid() {
		return this.programaTipoid;
	}

	public void setProgramaTipoid(int programaTipoid) {
		this.programaTipoid = programaTipoid;
	}

	@Column(name = "programa_propiedadid", nullable = false)
	public int getProgramaPropiedadid() {
		return this.programaPropiedadid;
	}

	public void setProgramaPropiedadid(int programaPropiedadid) {
		this.programaPropiedadid = programaPropiedadid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProgtipoPropiedadId))
			return false;
		ProgtipoPropiedadId castOther = (ProgtipoPropiedadId) other;

		return (this.getProgramaTipoid() == castOther.getProgramaTipoid())
				&& (this.getProgramaPropiedadid() == castOther.getProgramaPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProgramaTipoid();
		result = 37 * result + this.getProgramaPropiedadid();
		return result;
	}

}