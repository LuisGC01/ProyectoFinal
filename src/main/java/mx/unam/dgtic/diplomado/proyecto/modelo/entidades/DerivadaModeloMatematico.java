package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "derivada_modelo_matematico")
public class DerivadaModeloMatematico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4779623904093553866L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_derivada_modelo_matematico")
	private Integer idDerivadaModeloMatematico;

	@Column(name = "der_mod_mat_derivada_parcial")
	private String derivadaParcial;

	@Column(name = "der_mod_mat_respecto_a")
	private String respectoA;

	@ManyToOne
	@JoinColumn(name = "id_modelo_matematico")
	// @Fetch(FetchMode.JOIN)
	@JsonIgnore
	private ModeloMatematico modeloMatematico;

	public DerivadaModeloMatematico() {
		// TODO Auto-generated constructor stub
	}

	public DerivadaModeloMatematico(Integer idDerivadaModeloMatematico, String derivadaParcial, String respectoA,
			ModeloMatematico modeloMatematico) {
		this.idDerivadaModeloMatematico = idDerivadaModeloMatematico;
		this.derivadaParcial = derivadaParcial;
		this.respectoA = respectoA;
		this.modeloMatematico = modeloMatematico;
	}

	public DerivadaModeloMatematico(String derivadaParcial, String respectoA) {
		this.derivadaParcial = derivadaParcial;
		this.respectoA = respectoA;
	}

	public DerivadaModeloMatematico(String derivadaParcial, String respectoA, ModeloMatematico modeloMatematico) {
		this.derivadaParcial = derivadaParcial;
		this.respectoA = respectoA;
		this.modeloMatematico = modeloMatematico;
	}

	public DerivadaModeloMatematico(Integer idDerivadaModeloMatematico, String derivadaParcial, String respectoA) {
		this.idDerivadaModeloMatematico = idDerivadaModeloMatematico;
		this.derivadaParcial = derivadaParcial;
		this.respectoA = respectoA;
	}

	public DerivadaModeloMatematico(Integer idDerivadaModeloMatematico) {
		this.idDerivadaModeloMatematico = idDerivadaModeloMatematico;
	}

	public Integer getIdDerivadaModeloMatematico() {
		return idDerivadaModeloMatematico;
	}

	public void setIdDerivadaModeloMatematico(Integer idDerivadaModeloMatematico) {
		this.idDerivadaModeloMatematico = idDerivadaModeloMatematico;
	}

	public String getDerivadaParcial() {
		return derivadaParcial;
	}

	public void setDerivadaParcial(String derivadaParcial) {
		this.derivadaParcial = derivadaParcial;
	}

	public String getRespectoA() {
		return respectoA;
	}

	public void setRespectoA(String respectoA) {
		this.respectoA = respectoA;
	}

	public ModeloMatematico getModeloMatematico() {
		return modeloMatematico;
	}

	public void setModeloMatematico(ModeloMatematico modeloMatematico) {
		this.modeloMatematico = modeloMatematico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDerivadaModeloMatematico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DerivadaModeloMatematico other = (DerivadaModeloMatematico) obj;
		return Objects.equals(idDerivadaModeloMatematico, other.idDerivadaModeloMatematico);
	}

	@Override
	public String toString() {
		return "DerivadaModeloMatematico [idDerivadaModeloMatematico=" + idDerivadaModeloMatematico
				+ ", derivadaParcial=" + derivadaParcial + ", respectoA=" + respectoA + "]";
	}

}
