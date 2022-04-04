package mx.unam.dgtic.diplomado.proyecto.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IArregloMedicionRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IMagnitudArregloRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.ISecuenciaDetalleRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.ISecuenciaMedicionRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IUsuarioRepository;

@Service
public class SecuenciaServiceImpl implements ISecuenciaService {

	@Autowired
	private ISecuenciaMedicionRepository secuenciaMedicionRepository;

	@Autowired
	private ISecuenciaDetalleRepository secuenciaDetalleRepository;

	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private IArregloMedicionRepository arregloMedicionRepository;

	@Autowired
	private IMagnitudArregloRepository magnitudArregloRepository;

	public SecuenciaServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<SecuenciaMedicion> findAll() {
		return secuenciaMedicionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SecuenciaMedicion> findById(Integer id) {
		return secuenciaMedicionRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<SecuenciaMedicion> findByUsuario(Integer idUsuario) {
		return secuenciaMedicionRepository.findByUsuario(usuarioRepository.findById(idUsuario).get());
	}

	@Override
	@Transactional
	public SecuenciaMedicion insertarSecuencia(SecuenciaMedicion s) {
		SecuenciaMedicion smaux = new SecuenciaMedicion(s.getTitulo(), s.getDescripcion(), LocalDateTime.now());
		smaux.setUsuario(usuarioRepository.findByNombreUsuarioAndActivo(s.getUsuario().getNombreUsuario(), true).get(0));
		SecuenciaMedicion smr = secuenciaMedicionRepository.save(smaux);
		List<SecuenciaDetalle> detalles = new ArrayList<SecuenciaDetalle>();
		if (s.getSecuenciaDetalles() != null) {
			if (s.getSecuenciaDetalles().size() > 0) {
				for (SecuenciaDetalle sd : s.getSecuenciaDetalles()) {
					SecuenciaDetalle sdaux = new SecuenciaDetalle(sd.getNumeroPuntoSecuencia(), sd.getTipoPunto(),
							sd.getDescripcionPunto(), sd.getValor(), sd.getBarridoPrincipal(),
							sd.getBarridoSecundario(), sd.getNumeroMediciones(), sd.getInstrucciones(),
							sd.getComentario(), sd.getDialogoTxt(), sd.getDialogoEntrada(), sd.getDialogoValidacion());
					sdaux.setSecuenciaMedicion(smr);
					if (sd.getArregloMedicion() != null) {
						sdaux.setArregloMedicion(arregloMedicionRepository
								.findById(sd.getArregloMedicion().getIdArregloMedicion()).get());
					}
					if (sd.getMagnitudArreglo() != null) {
						sdaux.setMagnitudArreglo(magnitudArregloRepository
								.findById(sd.getMagnitudArreglo().getIdMagnitudArreglo()).get());
					}
					SecuenciaDetalle sdr = secuenciaDetalleRepository.save(sdaux);
					detalles.add(sdr);
				}
				smr.setSecuenciaDetalles(detalles);
			}
		}
		return smr;
	}

	@Override
	@Transactional
	public SecuenciaMedicion actualizarSecuencia(SecuenciaMedicion s) {
		SecuenciaMedicion smaux = new SecuenciaMedicion(s.getIdSecuenciaMedicion(), s.getTitulo(), s.getDescripcion(),
				LocalDateTime.now());
		smaux.setUsuario(usuarioRepository.findByNombreUsuarioAndActivo(s.getUsuario().getNombreUsuario(), true).get(0));
		SecuenciaMedicion smr = secuenciaMedicionRepository.save(smaux);
		secuenciaDetalleRepository.deleteBySecuenciaMedicion(smr);
		List<SecuenciaDetalle> detalles = new ArrayList<SecuenciaDetalle>();
		if (s.getSecuenciaDetalles() != null) {
			if (s.getSecuenciaDetalles().size() > 0) {
				for (SecuenciaDetalle sd : s.getSecuenciaDetalles()) {
					SecuenciaDetalle sdaux = new SecuenciaDetalle(sd.getIdSecuenciaDetalle(),
							sd.getNumeroPuntoSecuencia(), sd.getTipoPunto(), sd.getDescripcionPunto(), sd.getValor(),
							sd.getBarridoPrincipal(), sd.getBarridoSecundario(), sd.getNumeroMediciones(),
							sd.getInstrucciones(), sd.getComentario(), sd.getDialogoTxt(), sd.getDialogoEntrada(),
							sd.getDialogoValidacion());
					sdaux.setSecuenciaMedicion(smr);
					if (sd.getArregloMedicion() != null) {
						sdaux.setArregloMedicion(arregloMedicionRepository
								.findById(sd.getArregloMedicion().getIdArregloMedicion()).get());
					}
					if (sd.getMagnitudArreglo() != null) {
						sdaux.setMagnitudArreglo(magnitudArregloRepository
								.findById(sd.getMagnitudArreglo().getIdMagnitudArreglo()).get());
					}
					System.out.println(sdaux);
					SecuenciaDetalle sdr = secuenciaDetalleRepository.save(sdaux);
					detalles.add(sdr);
				}
				smr.setSecuenciaDetalles(detalles);
			}
		}
		return smr;
	}

	@Override
	@Transactional
	public void borrarSecuenciaPorId(Integer id) {
		SecuenciaMedicion smr = secuenciaMedicionRepository.findById(id).get();
		if (smr.getSecuenciaDetalles() != null && smr.getSecuenciaDetalles().size() > 0) {
			for (SecuenciaDetalle sd : smr.getSecuenciaDetalles()) {
				secuenciaDetalleRepository.deleteById(sd.getIdSecuenciaDetalle());
			}
		}
		secuenciaMedicionRepository.deleteById(smr.getIdSecuenciaMedicion());
	}

}
