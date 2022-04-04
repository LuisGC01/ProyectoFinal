package mx.unam.dgtic.diplomado.proyecto.service;

import java.util.List;
import java.util.Optional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

public interface ISecuenciaService {

	public abstract Iterable<SecuenciaMedicion> findAll();

	public abstract Optional<SecuenciaMedicion> findById(Integer id);

	public abstract SecuenciaMedicion insertarSecuencia(SecuenciaMedicion s);

	public abstract SecuenciaMedicion actualizarSecuencia(SecuenciaMedicion s);

	public abstract void borrarSecuenciaPorId(Integer id);

	public abstract List<SecuenciaMedicion> findByUsuario(Integer idUsuario);


}
