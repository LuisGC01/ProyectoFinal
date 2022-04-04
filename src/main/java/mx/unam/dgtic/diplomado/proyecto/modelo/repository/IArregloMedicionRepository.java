package mx.unam.dgtic.diplomado.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;

@Repository
public interface IArregloMedicionRepository extends CrudRepository<ArregloMedicion, Integer> {

	public abstract List<ArregloMedicion> findByUsuario(Usuario usuario);

}
