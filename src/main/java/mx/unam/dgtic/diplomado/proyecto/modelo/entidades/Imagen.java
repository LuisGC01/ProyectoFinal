package mx.unam.dgtic.diplomado.proyecto.modelo.entidades;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "imagen")
public class Imagen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen")
	private Integer idImagen;

	@Column(name = "img_imagen")
	private String imagen;

	@Column(name = "img_leyenda")
	private String leyenda;

	@Column(name = "img_descripcion")
	private String descripcion;

	public Imagen() {
		// TODO Auto-generated constructor stub
	}

	public Imagen(Integer idImagen, String imagen, String leyenda, String descripcion) {
		this.idImagen = idImagen;
		this.imagen = imagen;
		this.leyenda = leyenda;
		this.descripcion = descripcion;
	}

	public Imagen(String imagen, String leyenda, String descripcion) {
		super();
		this.imagen = imagen;
		this.leyenda = leyenda;
		this.descripcion = descripcion;
	}

	public Imagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public Integer getIdImagen() {
		return idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idImagen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagen other = (Imagen) obj;
		return Objects.equals(idImagen, other.idImagen);
	}

	@Override
	public String toString() {
		return "Imagen [idImagen=" + idImagen + ", imagen=" + imagen + ", leyenda=" + leyenda + ", descripcion="
				+ descripcion + "]";
	}

}
