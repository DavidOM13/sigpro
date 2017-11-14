package pojo;
// Generated Nov 12, 2017 1:30:38 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * MetaAvanceId generated by hbm2java
 */
@Embeddable
public class MetaAvanceId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -887561860408909433L;
	private int metaid;
	private Date fecha;

	public MetaAvanceId() {
	}

	public MetaAvanceId(int metaid, Date fecha) {
		this.metaid = metaid;
		this.fecha = fecha;
	}

	@Column(name = "metaid", nullable = false)
	public int getMetaid() {
		return this.metaid;
	}

	public void setMetaid(int metaid) {
		this.metaid = metaid;
	}

	@Column(name = "fecha", nullable = false, length = 19)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MetaAvanceId))
			return false;
		MetaAvanceId castOther = (MetaAvanceId) other;

		return (this.getMetaid() == castOther.getMetaid())
				&& ((this.getFecha() == castOther.getFecha()) || (this.getFecha() != null
						&& castOther.getFecha() != null && this.getFecha().equals(castOther.getFecha())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getMetaid();
		result = 37 * result + (getFecha() == null ? 0 : this.getFecha().hashCode());
		return result;
	}

}
