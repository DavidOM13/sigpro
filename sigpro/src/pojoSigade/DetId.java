package pojoSigade;
// Generated May 3, 2017 3:42:48 PM by Hibernate Tools 5.2.1.Final

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * DetId generated by hbm2java
 */
@Embeddable
public class DetId implements java.io.Serializable {

	private Integer ejercicioFiscal;
	private String mesDesembolso;
	private String codigoPrespuestario;
	private Integer etnidadSicoin;
	private Integer unidadEjecutoriaSicoin;
	private String monedaDesembolso;
	private BigDecimal desembolsosMesMoneda;
	private Integer tcMonUsd;
	private BigDecimal desembolsosMesUsd;
	private Integer tcUsdGtq;
	private BigDecimal desembolsosMesGtq;

	public DetId() {
	}

	public DetId(Integer ejercicioFiscal, String mesDesembolso, String codigoPrespuestario, Integer etnidadSicoin,
			Integer unidadEjecutoriaSicoin, String monedaDesembolso, BigDecimal desembolsosMesMoneda, Integer tcMonUsd,
			BigDecimal desembolsosMesUsd, Integer tcUsdGtq, BigDecimal desembolsosMesGtq) {
		this.ejercicioFiscal = ejercicioFiscal;
		this.mesDesembolso = mesDesembolso;
		this.codigoPrespuestario = codigoPrespuestario;
		this.etnidadSicoin = etnidadSicoin;
		this.unidadEjecutoriaSicoin = unidadEjecutoriaSicoin;
		this.monedaDesembolso = monedaDesembolso;
		this.desembolsosMesMoneda = desembolsosMesMoneda;
		this.tcMonUsd = tcMonUsd;
		this.desembolsosMesUsd = desembolsosMesUsd;
		this.tcUsdGtq = tcUsdGtq;
		this.desembolsosMesGtq = desembolsosMesGtq;
	}

	@Column(name = "ejercicio_fiscal")
	public Integer getEjercicioFiscal() {
		return this.ejercicioFiscal;
	}

	public void setEjercicioFiscal(Integer ejercicioFiscal) {
		this.ejercicioFiscal = ejercicioFiscal;
	}

	@Column(name = "mes_desembolso", length = 2)
	public String getMesDesembolso() {
		return this.mesDesembolso;
	}

	public void setMesDesembolso(String mesDesembolso) {
		this.mesDesembolso = mesDesembolso;
	}

	@Column(name = "codigo_prespuestario", length = 45)
	public String getCodigoPrespuestario() {
		return this.codigoPrespuestario;
	}

	public void setCodigoPrespuestario(String codigoPrespuestario) {
		this.codigoPrespuestario = codigoPrespuestario;
	}

	@Column(name = "etnidad_sicoin")
	public Integer getEtnidadSicoin() {
		return this.etnidadSicoin;
	}

	public void setEtnidadSicoin(Integer etnidadSicoin) {
		this.etnidadSicoin = etnidadSicoin;
	}

	@Column(name = "unidad_ejecutoria_sicoin")
	public Integer getUnidadEjecutoriaSicoin() {
		return this.unidadEjecutoriaSicoin;
	}

	public void setUnidadEjecutoriaSicoin(Integer unidadEjecutoriaSicoin) {
		this.unidadEjecutoriaSicoin = unidadEjecutoriaSicoin;
	}

	@Column(name = "moneda_desembolso", length = 45)
	public String getMonedaDesembolso() {
		return this.monedaDesembolso;
	}

	public void setMonedaDesembolso(String monedaDesembolso) {
		this.monedaDesembolso = monedaDesembolso;
	}

	@Column(name = "desembolsos_mes_moneda", precision = 15)
	public BigDecimal getDesembolsosMesMoneda() {
		return this.desembolsosMesMoneda;
	}

	public void setDesembolsosMesMoneda(BigDecimal desembolsosMesMoneda) {
		this.desembolsosMesMoneda = desembolsosMesMoneda;
	}

	@Column(name = "tc_mon_usd")
	public Integer getTcMonUsd() {
		return this.tcMonUsd;
	}

	public void setTcMonUsd(Integer tcMonUsd) {
		this.tcMonUsd = tcMonUsd;
	}

	@Column(name = "desembolsos_mes_usd", precision = 15)
	public BigDecimal getDesembolsosMesUsd() {
		return this.desembolsosMesUsd;
	}

	public void setDesembolsosMesUsd(BigDecimal desembolsosMesUsd) {
		this.desembolsosMesUsd = desembolsosMesUsd;
	}

	@Column(name = "tc_usd_gtq")
	public Integer getTcUsdGtq() {
		return this.tcUsdGtq;
	}

	public void setTcUsdGtq(Integer tcUsdGtq) {
		this.tcUsdGtq = tcUsdGtq;
	}

	@Column(name = "desembolsos_mes_gtq", precision = 15)
	public BigDecimal getDesembolsosMesGtq() {
		return this.desembolsosMesGtq;
	}

	public void setDesembolsosMesGtq(BigDecimal desembolsosMesGtq) {
		this.desembolsosMesGtq = desembolsosMesGtq;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DetId))
			return false;
		DetId castOther = (DetId) other;

		return ((this.getEjercicioFiscal() == castOther.getEjercicioFiscal())
				|| (this.getEjercicioFiscal() != null && castOther.getEjercicioFiscal() != null
						&& this.getEjercicioFiscal().equals(castOther.getEjercicioFiscal())))
				&& ((this.getMesDesembolso() == castOther.getMesDesembolso())
						|| (this.getMesDesembolso() != null && castOther.getMesDesembolso() != null
								&& this.getMesDesembolso().equals(castOther.getMesDesembolso())))
				&& ((this.getCodigoPrespuestario() == castOther.getCodigoPrespuestario())
						|| (this.getCodigoPrespuestario() != null && castOther.getCodigoPrespuestario() != null
								&& this.getCodigoPrespuestario().equals(castOther.getCodigoPrespuestario())))
				&& ((this.getEtnidadSicoin() == castOther.getEtnidadSicoin())
						|| (this.getEtnidadSicoin() != null && castOther.getEtnidadSicoin() != null
								&& this.getEtnidadSicoin().equals(castOther.getEtnidadSicoin())))
				&& ((this.getUnidadEjecutoriaSicoin() == castOther.getUnidadEjecutoriaSicoin())
						|| (this.getUnidadEjecutoriaSicoin() != null && castOther.getUnidadEjecutoriaSicoin() != null
								&& this.getUnidadEjecutoriaSicoin().equals(castOther.getUnidadEjecutoriaSicoin())))
				&& ((this.getMonedaDesembolso() == castOther.getMonedaDesembolso())
						|| (this.getMonedaDesembolso() != null && castOther.getMonedaDesembolso() != null
								&& this.getMonedaDesembolso().equals(castOther.getMonedaDesembolso())))
				&& ((this.getDesembolsosMesMoneda() == castOther.getDesembolsosMesMoneda())
						|| (this.getDesembolsosMesMoneda() != null && castOther.getDesembolsosMesMoneda() != null
								&& this.getDesembolsosMesMoneda().equals(castOther.getDesembolsosMesMoneda())))
				&& ((this.getTcMonUsd() == castOther.getTcMonUsd()) || (this.getTcMonUsd() != null
						&& castOther.getTcMonUsd() != null && this.getTcMonUsd().equals(castOther.getTcMonUsd())))
				&& ((this.getDesembolsosMesUsd() == castOther.getDesembolsosMesUsd())
						|| (this.getDesembolsosMesUsd() != null && castOther.getDesembolsosMesUsd() != null
								&& this.getDesembolsosMesUsd().equals(castOther.getDesembolsosMesUsd())))
				&& ((this.getTcUsdGtq() == castOther.getTcUsdGtq()) || (this.getTcUsdGtq() != null
						&& castOther.getTcUsdGtq() != null && this.getTcUsdGtq().equals(castOther.getTcUsdGtq())))
				&& ((this.getDesembolsosMesGtq() == castOther.getDesembolsosMesGtq())
						|| (this.getDesembolsosMesGtq() != null && castOther.getDesembolsosMesGtq() != null
								&& this.getDesembolsosMesGtq().equals(castOther.getDesembolsosMesGtq())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getEjercicioFiscal() == null ? 0 : this.getEjercicioFiscal().hashCode());
		result = 37 * result + (getMesDesembolso() == null ? 0 : this.getMesDesembolso().hashCode());
		result = 37 * result + (getCodigoPrespuestario() == null ? 0 : this.getCodigoPrespuestario().hashCode());
		result = 37 * result + (getEtnidadSicoin() == null ? 0 : this.getEtnidadSicoin().hashCode());
		result = 37 * result + (getUnidadEjecutoriaSicoin() == null ? 0 : this.getUnidadEjecutoriaSicoin().hashCode());
		result = 37 * result + (getMonedaDesembolso() == null ? 0 : this.getMonedaDesembolso().hashCode());
		result = 37 * result + (getDesembolsosMesMoneda() == null ? 0 : this.getDesembolsosMesMoneda().hashCode());
		result = 37 * result + (getTcMonUsd() == null ? 0 : this.getTcMonUsd().hashCode());
		result = 37 * result + (getDesembolsosMesUsd() == null ? 0 : this.getDesembolsosMesUsd().hashCode());
		result = 37 * result + (getTcUsdGtq() == null ? 0 : this.getTcUsdGtq().hashCode());
		result = 37 * result + (getDesembolsosMesGtq() == null ? 0 : this.getDesembolsosMesGtq().hashCode());
		return result;
	}

}
