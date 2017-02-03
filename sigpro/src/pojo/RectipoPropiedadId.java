package pojo;
// Generated Feb 3, 2017 9:12:33 AM by Hibernate Tools 5.2.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RectipoPropiedadId generated by hbm2java
 */
@Embeddable
public class RectipoPropiedadId implements java.io.Serializable {

	private int recursoPropiedadid;
	private int recursoTipoid;

	public RectipoPropiedadId() {
	}

	public RectipoPropiedadId(int recursoPropiedadid, int recursoTipoid) {
		this.recursoPropiedadid = recursoPropiedadid;
		this.recursoTipoid = recursoTipoid;
	}

	@Column(name = "recurso_propiedadid", nullable = false)
	public int getRecursoPropiedadid() {
		return this.recursoPropiedadid;
	}

	public void setRecursoPropiedadid(int recursoPropiedadid) {
		this.recursoPropiedadid = recursoPropiedadid;
	}

	@Column(name = "recurso_tipoid", nullable = false)
	public int getRecursoTipoid() {
		return this.recursoTipoid;
	}

	public void setRecursoTipoid(int recursoTipoid) {
		this.recursoTipoid = recursoTipoid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RectipoPropiedadId))
			return false;
		RectipoPropiedadId castOther = (RectipoPropiedadId) other;

		return (this.getRecursoPropiedadid() == castOther.getRecursoPropiedadid())
				&& (this.getRecursoTipoid() == castOther.getRecursoTipoid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getRecursoPropiedadid();
		result = 37 * result + this.getRecursoTipoid();
		return result;
	}

}
