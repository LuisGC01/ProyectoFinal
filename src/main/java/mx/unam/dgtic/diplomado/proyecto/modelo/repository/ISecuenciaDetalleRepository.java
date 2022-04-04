package mx.unam.dgtic.diplomado.proyecto.modelo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

@Repository
public interface ISecuenciaDetalleRepository extends CrudRepository<SecuenciaDetalle, Integer> {

	public abstract void deleteBySecuenciaMedicion(SecuenciaMedicion secuenciaMedicion);
	
}
