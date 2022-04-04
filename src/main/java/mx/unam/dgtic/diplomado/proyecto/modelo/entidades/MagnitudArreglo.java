package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "magnitud_arreglo")
public class MagnitudArreglo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7795149520782695069L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_magnitud_arreglo")
	private Integer idMagnitudArreglo;

	@Column(name = "mag_arr_magnitud")
	private String magnitud;

	@Column(name = "mag_arr_unidad")
	private String unidad;

	@Column(name = "mag_arr_definicion")
	private String definicion;

	@Column(name = "mag_arr_capturar")
	private Boolean capturar=false;

	@Column(name = "mag_arr_repetir")
	private Boolean repetir=false;

	@Column(name = "mag_arr_asociado")
	private String asociado;

	@ManyToOne
	@JoinColumn(name = "id_modelo_matematico")
	// @Fetch(FetchMode.JOIN)
	@JsonIgnore
	private ModeloMatematico modeloMatematico;

	@OneToOne(mappedBy = "magnitudArreglo")
	private MagnitudDetalle magnitudDetalle;

	@OneToMany(mappedBy = "magnitudArreglo")
	private Set<SecuenciaDetalle> secuenciaDetalles;

	public MagnitudArreglo() {
		// TODO Auto-generated constructor stub
		//this.magnitudDetalle=new MagnitudDetalle();
	}

	public MagnitudArreglo(Integer idMagnitudArreglo, String magnitud, String unidad, String definicion,
			Boolean capturar, Boolean repetir, String asociado, ModeloMatematico modeloMatematico,
			MagnitudDetalle magnitudDetalle) {
		this.idMagnitudArreglo = idMagnitudArreglo;
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
		this.modeloMatematico = modeloMatematico;
		this.magnitudDetalle = magnitudDetalle;
	}

	public MagnitudArreglo(String magnitud, String unidad, String definicion, Boolean capturar, Boolean repetir,
			String asociado) {
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
	}

	public MagnitudArreglo(String magnitud, String unidad, String definicion, Boolean capturar, Boolean repetir,
			String asociado, ModeloMatematico modeloMatematico, MagnitudDetalle magnitudDetalle) {
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
		this.modeloMatematico = modeloMatematico;
		this.magnitudDetalle = magnitudDetalle;
	}

	public MagnitudArreglo(Integer idMagnitudArreglo, String magnitud, String unidad, String definicion,
			Boolean capturar, Boolean repetir, String asociado) {
		this.idMagnitudArreglo = idMagnitudArreglo;
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
	}

	public MagnitudArreglo(String magnitud, String unidad, String definicion, Boolean capturar, Boolean repetir,
			String asociado, ModeloMatematico modeloMatematico) {
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
		this.modeloMatematico = modeloMatematico;
	}

	public MagnitudArreglo(Integer idMagnitudArreglo, String magnitud, String unidad, String definicion,
			Boolean capturar, Boolean repetir, String asociado, ModeloMatematico modeloMatematico) {
		this.idMagnitudArreglo = idMagnitudArreglo;
		this.magnitud = magnitud;
		this.unidad = unidad;
		this.definicion = definicion;
		this.capturar = capturar;
		this.repetir = repetir;
		this.asociado = asociado;
		this.modeloMatematico = modeloMatematico;
	}

	public MagnitudArreglo(Integer idMagnitudArreglo) {
		this.idMagnitudArreglo = idMagnitudArreglo;
	}

	public Integer getIdMagnitudArreglo() {
		return idMagnitudArreglo;
	}

	public void setIdMagnitudArreglo(Integer idMagnitudArreglo) {
		this.idMagnitudArreglo = idMagnitudArreglo;
	}

	public String getMagnitud() {
		return magnitud;
	}

	public void setMagnitud(String magnitud) {
		this.magnitud = magnitud;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}

	public Boolean getCapturar() {
		return capturar;
	}

	public void setCapturar(Boolean capturar) {
		this.capturar = capturar;
	}

	public Boolean getRepetir() {
		return repetir;
	}

	public void setRepetir(Boolean repetir) {
		this.repetir = repetir;
	}

	public String getAsociado() {
		return asociado;
	}

	public void setAsociado(String asociado) {
		this.asociado = asociado;
	}

	public ModeloMatematico getModeloMatematico() {
		return modeloMatematico;
	}

	public void setModeloMatematico(ModeloMatematico modeloMatematico) {
		this.modeloMatematico = modeloMatematico;
	}

	public MagnitudDetalle getMagnitudDetalle() {
		return magnitudDetalle;
	}

	public void setMagnitudDetalle(MagnitudDetalle magnitudDetalle) {
		this.magnitudDetalle = magnitudDetalle;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idMagnitudArreglo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MagnitudArreglo other = (MagnitudArreglo) obj;
		return Objects.equals(idMagnitudArreglo, other.idMagnitudArreglo);
	}

	@Override
	public String toString() {
		return "MagnitudArreglo [idMagnitudArreglo=" + idMagnitudArreglo + ", magnitud=" + magnitud + ", unidad="
				+ unidad + ", definicion=" + definicion + ", capturar=" + capturar + ", repetir=" + repetir
				+ ", asociado=" + asociado + ", magnitudDetalle=" + magnitudDetalle + "]";
	}

}
