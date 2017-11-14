package pojoSigade;
// Generated Nov 2, 2017 2:45:37 PM by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * DtmAvanceFisfinanEnp generated by hbm2java
 */
@Entity
@Table(name = "dtm_avance_fisfinan_enp", catalog = "sipro_analytic")
public class DtmAvanceFisfinanEnp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5641184957263724173L;
	private DtmAvanceFisfinanEnpId id;

	public DtmAvanceFisfinanEnp() {
	}

	public DtmAvanceFisfinanEnp(DtmAvanceFisfinanEnpId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "codigoPresupuestario", column = @Column(name = "codigo_presupuestario", length = 45)),
			@AttributeOverride(name = "ejercicioFiscal", column = @Column(name = "ejercicio_fiscal")),
			@AttributeOverride(name = "entidadPresupuestaria", column = @Column(name = "entidad_presupuestaria")),
			@AttributeOverride(name = "unidadEjecutora", column = @Column(name = "unidad_ejecutora")) })
	public DtmAvanceFisfinanEnpId getId() {
		return this.id;
	}

	public void setId(DtmAvanceFisfinanEnpId id) {
		this.id = id;
	}

}
