package mx.unam.dgtic.diplomado.proyecto.modelo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;

@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Integer> {
	public abstract List<Usuario> findByNombreUsuarioAndActivo(String nombreUsuario,Boolean activo);
	
}
