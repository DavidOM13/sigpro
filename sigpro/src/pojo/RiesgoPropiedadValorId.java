package pojo;
// Generated Dec 3, 2016 8:45:14 AM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RiesgoPropiedadValorId generated by hbm2java
 */
@Embeddable
public class RiesgoPropiedadValorId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3833355906005685475L;
	private int riesgoid;
	private int riesgoPropiedadid;

	public RiesgoPropiedadValorId() {
	}

	public RiesgoPropiedadValorId(int riesgoid, int riesgoPropiedadid) {
		this.riesgoid = riesgoid;
		this.riesgoPropiedadid = riesgoPropiedadid;
	}

	@Column(name = "riesgoid", nullable = false)
	public int getRiesgoid() {
		return this.riesgoid;
	}

	public void setRiesgoid(int riesgoid) {
		this.riesgoid = riesgoid;
	}

	@Column(name = "riesgo_propiedadid", nullable = false)
	public int getRiesgoPropiedadid() {
		return this.riesgoPropiedadid;
	}

	public void setRiesgoPropiedadid(int riesgoPropiedadid) {
		this.riesgoPropiedadid = riesgoPropiedadid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RiesgoPropiedadValorId))
			return false;
		RiesgoPropiedadValorId castOther = (RiesgoPropiedadValorId) other;

		return (this.getRiesgoid() == castOther.getRiesgoid())
				&& (this.getRiesgoPropiedadid() == castOther.getRiesgoPropiedadid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRiesgoid();
		result = 37 * result + this.getRiesgoPropiedadid();
		return result;
	}

}
