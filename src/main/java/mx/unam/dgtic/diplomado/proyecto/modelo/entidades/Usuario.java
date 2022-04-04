package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2017067021225263600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "usu_nombre_usuario")
	private String nombreUsuario;

	@Column(name = "usu_password")
	private String password;

	@Column(name = "usu_puesto")
	private String puesto;

	@Column(name = "usu_nombre")
	private String nombre;

	@Column(name = "usu_primer_apellido")
	private String primerApellido;

	@Column(name = "usu_segundo_apellido")
	private String segundoApellido;

	@Column(name = "usu_activo")
	private Boolean activo;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	// @Fetch(FetchMode.JOIN)
	private List<ArregloMedicion> arreglosMedicion;
	// private List<SecuenciaMedicion> secuenciasMedicion;

	@OneToMany(mappedBy = "usuario")
	@JsonIgnore
	private List<SecuenciaMedicion> secuenciasMedicion;

	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer idUsuario, String nombreUsuario, String password, String puesto, String nombre,
			String primerApellido, String segundoApellido, Boolean activo) {
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.puesto = puesto;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.activo = activo;
	}

	public Usuario(String nombreUsuario, String password, String puesto, String nombre, String primerApellido,
			String segundoApellido, Boolean activo) {
		this.nombreUsuario = nombreUsuario;
		this.password = password;
		this.puesto = puesto;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.activo = activo;
	}

	public Usuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public List<ArregloMedicion> getArreglosMedicion() {
		return arreglosMedicion;
	}

	public void setArreglosMedicion(List<ArregloMedicion> arreglosMedicion) {
		this.arreglosMedicion = arreglosMedicion;
	}

	public List<SecuenciaMedicion> getSecuenciasMedicion() {
		return secuenciasMedicion;
	}

	public void setSecuenciasMedicion(List<SecuenciaMedicion> secuenciasMedicion) {
		this.secuenciasMedicion = secuenciasMedicion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", password=" + password
				+ ", puesto=" + puesto + ", nombre=" + nombre + ", primerApellido=" + primerApellido
				+ ", segundoApellido=" + segundoApellido + ", activo=" + activo + "]";
	}

}
