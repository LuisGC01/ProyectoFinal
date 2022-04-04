package mx.unam.dgtic.diplomado.proyecto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IUsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;

	public UsuarioServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Integer id) {
		return usuarioRepository.findById(id);
	}

	public Usuario findByNombreUsuarioAndActivo(String nombreUsuario, Boolean activo) {
		return usuarioRepository.findByNombreUsuarioAndActivo(nombreUsuario, activo).get(0);
	}

	@Override
	@Transactional
	public Usuario save(Usuario u) {
		return usuarioRepository.save(u);
	}

	@Override
	@Transactional
	public void deleteById(Integer id) {
		usuarioRepository.deleteById(id);
	}

}
