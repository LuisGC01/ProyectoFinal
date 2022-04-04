package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "arreglo_medicion")
public class ArregloMedicion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3881095940867153931L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_arreglo_medicion")
	private Integer idArregloMedicion;

	@Column(name = "arr_med_titulo")
	private String titulo;

	@Column(name = "arr_med_fecha")
	private LocalDateTime fecha;

	@Column(name = "arr_med_version")
	private String version;

	@Column(name = "arr_med_descripcion")
	private String descripcion;

	@Column(name = "arr_med_formato_cal_cert")
	private String formatoCalCert;

	@OneToOne
	@JoinColumn(name = "id_imagen", nullable = false)
	// @Fetch(FetchMode.JOIN)
	private Imagen imagen;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	// @Fetch(FetchMode.JOIN)
	private Usuario usuario;

	@OneToOne(mappedBy = "arregloMedicion")
	private ModeloMatematico modeloMatematico;

	@OneToMany(mappedBy = "arregloMedicion")
	@JsonIgnore
	private Set<SecuenciaDetalle> secuenciaDetalles;

	public ArregloMedicion() {
		// TODO Auto-generated constructor stub
		this.usuario = new Usuario();
		this.imagen=new Imagen();
		this.modeloMatematico=new ModeloMatematico();
	}

	public ArregloMedicion(Integer idArregloMedicion, String titulo, LocalDateTime fecha, String version,
			String descripcion, String formatoCalCert, Imagen imagen, Usuario usuario,
			ModeloMatematico modeloMatematico) {
		this.idArregloMedicion = idArregloMedicion;
		this.titulo = titulo;
		this.fecha = fecha;
		this.version = version;
		this.descripcion = descripcion;
		this.formatoCalCert = formatoCalCert;
		this.imagen = imagen;
		this.usuario = usuario;
		this.modeloMatematico = modeloMatematico;
	}

	public ArregloMedicion(String titulo, LocalDateTime fecha, String version, String descripcion,
			String formatoCalCert, Imagen imagen, Usuario usuario, ModeloMatematico modeloMatematico) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.version = version;
		this.descripcion = descripcion;
		this.formatoCalCert = formatoCalCert;
		this.imagen = imagen;
		this.usuario = usuario;
		this.modeloMatematico = modeloMatematico;
	}

	public ArregloMedicion(String titulo, LocalDateTime fecha, String version, String descripcion,
			String formatoCalCert) {
		this.titulo = titulo;
		this.fecha = fecha;
		this.version = version;
		this.descripcion = descripcion;
		this.formatoCalCert = formatoCalCert;
	}

	public ArregloMedicion(Integer idArregloMedicion, String titulo, LocalDateTime fecha, String version,
			String descripcion, String formatoCalCert) {
		this.idArregloMedicion = idArregloMedicion;
		this.titulo = titulo;
		this.fecha = fecha;
		this.version = version;
		this.descripcion = descripcion;
		this.formatoCalCert = formatoCalCert;
	}

	public ArregloMedicion(Integer idArregloMedicion, String titulo, LocalDateTime fecha, String version,
			String descripcion, String formatoCalCert, Imagen imagen, Usuario usuario) {
		this.idArregloMedicion = idArregloMedicion;
		this.titulo = titulo;
		this.fecha = fecha;
		this.version = version;
		this.descripcion = descripcion;
		this.formatoCalCert = formatoCalCert;
		this.imagen = imagen;
		this.usuario = usuario;
	}

	public ArregloMedicion(Integer idArregloMedicion) {
		this.idArregloMedicion = idArregloMedicion;
	}

	public Integer getIdArregloMedicion() {
		return idArregloMedicion;
	}

	public void setIdArregloMedicion(Integer idArregloMedicion) {
		this.idArregloMedicion = idArregloMedicion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFormatoCalCert() {
		return formatoCalCert;
	}

	public void setFormatoCalCert(String formatoCalCert) {
		this.formatoCalCert = formatoCalCert;
	}

	public Imagen getImagen() {
		return imagen;
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ModeloMatematico getModeloMatematico() {
		return modeloMatematico;
	}

	public void setModeloMatematico(ModeloMatematico modeloMatematico) {
		this.modeloMatematico = modeloMatematico;
	}

	public Set<SecuenciaDetalle> getSecuenciaDetalles() {
		return secuenciaDetalles;
	}

	public void setSecuenciaDetalles(Set<SecuenciaDetalle> secuenciaDetalles) {
		this.secuenciaDetalles = secuenciaDetalles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idArregloMedicion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArregloMedicion other = (ArregloMedicion) obj;
		return Objects.equals(idArregloMedicion, other.idArregloMedicion);
	}

	@Override
	public String toString() {
		return "ArregloMedicion [idArregloMedicion=" + idArregloMedicion + ", titulo=" + titulo + ", fecha=" + fecha
				+ ", version=" + version + ", descripcion=" + descripcion + ", formatoCalCert=" + formatoCalCert
				+ ", imagen=" + imagen + ", usuario=" + usuario.getIdUsuario() + ", modeloMatematico="
				+ modeloMatematico + "]";
	}

}
