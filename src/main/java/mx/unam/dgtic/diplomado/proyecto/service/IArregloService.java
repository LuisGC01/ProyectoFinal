package mx.unam.dgtic.diplomado.proyecto.service;

import java.util.List;
import java.util.Optional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ModeloMatematico;

public interface IArregloService {

	public abstract Optional<ArregloMedicion> findById(Integer id);

	public abstract Iterable<ArregloMedicion> findAll();

	public abstract ArregloMedicion insertarArreglo(ArregloMedicion entity);

	public abstract ArregloMedicion actualizarArreglo(ArregloMedicion entity);

	public abstract void eliminarArregloPorId(Integer id);

	public abstract List<ArregloMedicion> findByUsuario(Integer idUsuario);

	public abstract MagnitudArreglo saveMagnitud(MagnitudArreglo entity);

	public abstract void deleteMagnitudesByModeloMatematico(ModeloMatematico modeloMatematico);

	public abstract void deleteDerivadasByModeloMatematico(ModeloMatematico modeloMatematico);

	public abstract Iterable<MagnitudDetalle> todosMD();

	MagnitudArreglo magnitudPorId(Integer id);

}
