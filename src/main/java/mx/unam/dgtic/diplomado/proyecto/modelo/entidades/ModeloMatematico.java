package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "modelo_matematico")
public class ModeloMatematico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4338566749380577556L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_modelo_matematico")
	private Integer idModeloMatematico;

	@Column(name = "mod_mat_ecuacion")
	private String ecuacion;

	@OneToOne
	@JoinColumn(name = "id_arreglo_medicion")
	// @Fetch(FetchMode.JOIN)
	@JsonIgnore
	private ArregloMedicion arregloMedicion;

	@OneToMany(mappedBy = "modeloMatematico", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<DerivadaModeloMatematico> derivadasModeloMatematico;

	@OneToMany(mappedBy = "modeloMatematico", fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	private List<MagnitudArreglo> magnitudesArreglo;

	public ModeloMatematico() {
		// TODO Auto-generated constructor stub
		this.derivadasModeloMatematico = new ArrayList<DerivadaModeloMatematico>();
		this.magnitudesArreglo = new ArrayList<MagnitudArreglo>();
	}

	public ModeloMatematico(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public ModeloMatematico(Integer idModeloMatematico, String ecuacion, ArregloMedicion arregloMedicion,
			List<DerivadaModeloMatematico> derivadasModeloMatematico, List<MagnitudArreglo> magnitudesArreglo) {
		this.idModeloMatematico = idModeloMatematico;
		this.ecuacion = ecuacion;
		this.arregloMedicion = arregloMedicion;
		this.derivadasModeloMatematico = derivadasModeloMatematico;
		this.magnitudesArreglo = magnitudesArreglo;
	}

	public ModeloMatematico(String ecuacion, ArregloMedicion arregloMedicion,
			List<DerivadaModeloMatematico> derivadasModeloMatematico, List<MagnitudArreglo> magnitudesArreglo) {
		this.ecuacion = ecuacion;
		this.arregloMedicion = arregloMedicion;
		this.derivadasModeloMatematico = derivadasModeloMatematico;
		this.magnitudesArreglo = magnitudesArreglo;
	}

	public ModeloMatematico(Integer idModeloMatematico, String ecuacion) {
		this.idModeloMatematico = idModeloMatematico;
		this.ecuacion = ecuacion;
	}

	public ModeloMatematico(String ecuacion, ArregloMedicion arregloMedicion) {
		this.ecuacion = ecuacion;
		this.arregloMedicion = arregloMedicion;
	}

	public ModeloMatematico(Integer idModeloMatematico, String ecuacion, ArregloMedicion arregloMedicion) {
		this.idModeloMatematico = idModeloMatematico;
		this.ecuacion = ecuacion;
		this.arregloMedicion = arregloMedicion;
	}

	public ModeloMatematico(Integer idModeloMatematico) {
		this.idModeloMatematico = idModeloMatematico;
	}

	public Integer getIdModeloMatematico() {
		return idModeloMatematico;
	}

	public void setIdModeloMatematico(Integer idModeloMatematico) {
		this.idModeloMatematico = idModeloMatematico;
	}

	public String getEcuacion() {
		return ecuacion;
	}

	public void setEcuacion(String ecuacion) {
		this.ecuacion = ecuacion;
	}

	public ArregloMedicion getArregloMedicion() {
		return arregloMedicion;
	}

	public void setArregloMedicion(ArregloMedicion arregloMedicion) {
		this.arregloMedicion = arregloMedicion;
	}

	public List<DerivadaModeloMatematico> getDerivadasModeloMatematico() {
		return derivadasModeloMatematico;
	}

	public void setDerivadasModeloMatematico(List<DerivadaModeloMatematico> derivadasModeloMatematico) {
		this.derivadasModeloMatematico = derivadasModeloMatematico;
	}

	public List<MagnitudArreglo> getMagnitudesArreglo() {
		return magnitudesArreglo;
	}

	public void setMagnitudesArreglo(List<MagnitudArreglo> magnitudesArreglo) {
		this.magnitudesArreglo = magnitudesArreglo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idModeloMatematico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloMatematico other = (ModeloMatematico) obj;
		return Objects.equals(idModeloMatematico, other.idModeloMatematico);
	}

	@Override
	public String toString() {
		return "ModeloMatematico [idModeloMatematico=" + idModeloMatematico + ", ecuacion=" + ecuacion
				+ ", derivadasModeloMatematico=" + derivadasModeloMatematico + ", magnitudesArreglo="
				+ magnitudesArreglo + "]";
	}

}
