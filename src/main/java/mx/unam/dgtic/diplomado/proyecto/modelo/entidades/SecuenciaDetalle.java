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
@Table(name = "secuencia_detalle")
public class SecuenciaDetalle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4240347558214409145L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_secuencia_detalle")
	private Integer idSecuenciaDetalle;

	@Column(name = "sec_det_numero_punto_secuencia")
	private Integer numeroPuntoSecuencia;

	@Column(name = "sec_det_tipo_punto")
	private Character tipoPunto;

	@Column(name = "sec_det_descripcion_punto")
	private String descripcionPunto;

	@Column(name = "sec_det_valor")
	private String valor;

	@Column(name = "sec_det_barrido_principal")
	private String barridoPrincipal;

	@Column(name = "sec_det_barrido_secundario")
	private String barridoSecundario;

	@Column(name = "sec_det_numero_mediciones")
	private Integer numeroMediciones;

	@Column(name = "sec_det_instrucciones")
	private String instrucciones;

	@Column(name = "sec_det_comentarios")
	private String comentario;

	@Column(name = "sec_det_dialogo_txto")
	private String dialogoTxt;

	@Column(name = "sec_det_dialogo_entrada")
	private String dialogoEntrada;

	@Column(name = "sec_det_dialogo_validacion")
	private String dialogoValidacion;

	@ManyToOne
	@JoinColumn(name = "id_secuencia_medicion")
	@JsonIgnore
	private SecuenciaMedicion secuenciaMedicion;

	@ManyToOne
	@JoinColumn(name = "id_arreglo_medicion")
	private ArregloMedicion arregloMedicion;

	@ManyToOne
	@JoinColumn(name = "id_magnitud_arreglo")
	private MagnitudArreglo magnitudArreglo;

	public SecuenciaDetalle() {
		// TODO Auto-generated constructor stub
	}

	public SecuenciaDetalle(Integer numeroPuntoSecuencia, Character tipoPunto, String descripcionPunto,
			String dialogoTxt, String dialogoEntrada, String dialogoValidacion, ArregloMedicion arregloMedicion,
			MagnitudArreglo magnitudArreglo) {
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
		this.arregloMedicion = arregloMedicion;
		this.magnitudArreglo = magnitudArreglo;
	}

	public SecuenciaDetalle(Integer numeroPuntoSecuencia, Character tipoPunto, String descripcionPunto, String valor,
			String barridoPrincipal, String barridoSecundario, Integer numeroMediciones, String instrucciones,
			String comentario, String dialogoTxt, String dialogoEntrada, String dialogoValidacion,
			ArregloMedicion arregloMedicion, MagnitudArreglo magnitudArreglo) {
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
		this.arregloMedicion = arregloMedicion;
		this.magnitudArreglo = magnitudArreglo;
	}

	public SecuenciaDetalle(Integer idSecuenciaDetalle, Integer numeroPuntoSecuencia, Character tipoPunto,
			String descripcionPunto, String valor, String barridoPrincipal, String barridoSecundario,
			Integer numeroMediciones, String instrucciones, String comentario, String dialogoTxt, String dialogoEntrada,
			String dialogoValidacion, SecuenciaMedicion secuenciaMedicion, ArregloMedicion arregloMedicion,
			MagnitudArreglo magnitudArreglo) {
		this.idSecuenciaDetalle = idSecuenciaDetalle;
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
		this.secuenciaMedicion = secuenciaMedicion;
		this.arregloMedicion = arregloMedicion;
		this.magnitudArreglo = magnitudArreglo;
	}

	public SecuenciaDetalle(Integer numeroPuntoSecuencia, Character tipoPunto, String descripcionPunto, String valor,
			String barridoPrincipal, String barridoSecundario, Integer numeroMediciones, String instrucciones,
			String comentario, String dialogoTxt, String dialogoEntrada, String dialogoValidacion,
			SecuenciaMedicion secuenciaMedicion, ArregloMedicion arregloMedicion, MagnitudArreglo magnitudArreglo) {
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
		this.secuenciaMedicion = secuenciaMedicion;
		this.arregloMedicion = arregloMedicion;
		this.magnitudArreglo = magnitudArreglo;
	}

	public SecuenciaDetalle(Integer idSecuenciaDetalle, Integer numeroPuntoSecuencia, Character tipoPunto,
			String descripcionPunto, String valor, String barridoPrincipal, String barridoSecundario,
			Integer numeroMediciones, String instrucciones, String comentario, String dialogoTxt, String dialogoEntrada,
			String dialogoValidacion) {
		this.idSecuenciaDetalle = idSecuenciaDetalle;
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
	}

	public SecuenciaDetalle(Integer numeroPuntoSecuencia, Character tipoPunto, String descripcionPunto, String valor,
			String barridoPrincipal, String barridoSecundario, Integer numeroMediciones, String instrucciones,
			String comentario, String dialogoTxt, String dialogoEntrada, String dialogoValidacion) {
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
	}

	public SecuenciaDetalle(Integer idSecuenciaDetalle, Integer numeroPuntoSecuencia, Character tipoPunto,
			String descripcionPunto, String valor, String barridoPrincipal, String barridoSecundario,
			Integer numeroMediciones, String instrucciones, String comentario, String dialogoTxt, String dialogoEntrada,
			String dialogoValidacion, ArregloMedicion arregloMedicion, MagnitudArreglo magnitudArreglo) {
		this.idSecuenciaDetalle = idSecuenciaDetalle;
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
		this.tipoPunto = tipoPunto;
		this.descripcionPunto = descripcionPunto;
		this.valor = valor;
		this.barridoPrincipal = barridoPrincipal;
		this.barridoSecundario = barridoSecundario;
		this.numeroMediciones = numeroMediciones;
		this.instrucciones = instrucciones;
		this.comentario = comentario;
		this.dialogoTxt = dialogoTxt;
		this.dialogoEntrada = dialogoEntrada;
		this.dialogoValidacion = dialogoValidacion;
		this.arregloMedicion = arregloMedicion;
		this.magnitudArreglo = magnitudArreglo;
	}

	public SecuenciaDetalle(Integer idSecuenciaDetalle) {
		this.idSecuenciaDetalle = idSecuenciaDetalle;
	}

	public Integer getIdSecuenciaDetalle() {
		return idSecuenciaDetalle;
	}

	public void setIdSecuenciaDetalle(Integer idSecuenciaDetalle) {
		this.idSecuenciaDetalle = idSecuenciaDetalle;
	}

	public Integer getNumeroPuntoSecuencia() {
		return numeroPuntoSecuencia;
	}

	public void setNumeroPuntoSecuencia(Integer numeroPuntoSecuencia) {
		this.numeroPuntoSecuencia = numeroPuntoSecuencia;
	}

	public Character getTipoPunto() {
		return tipoPunto;
	}

	public void setTipoPunto(Character tipoPunto) {
		this.tipoPunto = tipoPunto;
	}

	public String getDescripcionPunto() {
		return descripcionPunto;
	}

	public void setDescripcionPunto(String descripcionPunto) {
		this.descripcionPunto = descripcionPunto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getBarridoPrincipal() {
		return barridoPrincipal;
	}

	public void setBarridoPrincipal(String barridoPrincipal) {
		this.barridoPrincipal = barridoPrincipal;
	}

	public String getBarridoSecundario() {
		return barridoSecundario;
	}

	public void setBarridoSecundario(String barridoSecundario) {
		this.barridoSecundario = barridoSecundario;
	}

	public Integer getNumeroMediciones() {
		return numeroMediciones;
	}

	public void setNumeroMediciones(Integer numeroMediciones) {
		this.numeroMediciones = numeroMediciones;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getDialogoTxt() {
		return dialogoTxt;
	}

	public void setDialogoTxt(String dialogoTxt) {
		this.dialogoTxt = dialogoTxt;
	}

	public String getDialogoEntrada() {
		return dialogoEntrada;
	}

	public void setDialogoEntrada(String dialogoEntrada) {
		this.dialogoEntrada = dialogoEntrada;
	}

	public String getDialogoValidacion() {
		return dialogoValidacion;
	}

	public void setDialogoValidacion(String dialogoValidacion) {
		this.dialogoValidacion = dialogoValidacion;
	}

	public SecuenciaMedicion getSecuenciaMedicion() {
		return secuenciaMedicion;
	}

	public void setSecuenciaMedicion(SecuenciaMedicion secuenciaMedicion) {
		this.secuenciaMedicion = secuenciaMedicion;
	}

	public ArregloMedicion getArregloMedicion() {
		return arregloMedicion;
	}

	public void setArregloMedicion(ArregloMedicion arregloMedicion) {
		this.arregloMedicion = arregloMedicion;
	}

	public MagnitudArreglo getMagnitudArreglo() {
		return magnitudArreglo;
	}

	public void setMagnitudArreglo(MagnitudArreglo magnitudArreglo) {
		this.magnitudArreglo = magnitudArreglo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSecuenciaDetalle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SecuenciaDetalle other = (SecuenciaDetalle) obj;
		return Objects.equals(idSecuenciaDetalle, other.idSecuenciaDetalle);
	}

	@Override
	public String toString() {
		return "SecuenciaDetalle [idSecuenciaDetalle=" + idSecuenciaDetalle + ", numeroPuntoSecuencia="
				+ numeroPuntoSecuencia + ", tipoPunto=" + tipoPunto + ", descripcionPunto=" + descripcionPunto
				+ ", valor=" + valor + ", barridoPrincipal=" + barridoPrincipal + ", barridoSecundario="
				+ barridoSecundario + ", numeroMediciones=" + numeroMediciones + ", instrucciones=" + instrucciones
				+ ", comentario=" + comentario + ", dialogoTxt=" + dialogoTxt + ", dialogoEntrada=" + dialogoEntrada
				+ ", dialogoValidacion=" + dialogoValidacion + ", arregloMedicion="
				+ ((arregloMedicion != null) ? arregloMedicion.getIdArregloMedicion() : null) + ", magnitudArreglo="
				+ ((magnitudArreglo != null) ? magnitudArreglo.getIdMagnitudArreglo() : null) + "]";
	}

}
