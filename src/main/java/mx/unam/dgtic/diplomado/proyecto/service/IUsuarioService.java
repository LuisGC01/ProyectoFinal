package mx.unam.dgtic.diplomado.proyecto.service;

import java.util.Optional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;

public interface IUsuarioService {
	public abstract Iterable<Usuario> findAll();

	public abstract Optional<Usuario> findById(Integer id);

	public abstract Usuario save(Usuario u);

	public abstract void deleteById(Integer id);
}
