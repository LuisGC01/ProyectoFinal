package mx.unam.dgtic.diplomado.proyecto.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.DerivadaModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Imagen;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IArregloMedicionRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IDerivadaModeloMatematicoRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IImagenRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IMagnitudArregloRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IMagnitudDetalleRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IModeloMatematicoRepository;
import mx.unam.dgtic.diplomado.proyecto.modelo.repository.IUsuarioRepository;

@Service
public class ArregloServiceImpl implements IArregloService {

	@Autowired
	private IImagenRepository imagenRepository;

	@Autowired
	private IArregloMedicionRepository arregloMedicionRepository;

	@Autowired
	private IModeloMatematicoRepository modeloMatematicoRepository;

	@Autowired
	private IDerivadaModeloMatematicoRepository derivadaModeloMatematicoRepository;

	@Autowired
	private IMagnitudArregloRepository magnitudArregloRepository;

	@Autowired
	private IMagnitudDetalleRepository magnitudDetalleRepository;

	@Autowired
	private IUsuarioRepository usuarioRepository;

	public ArregloServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ArregloMedicion> findById(Integer id) {

		return arregloMedicionRepository.findById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<ArregloMedicion> findAll() {
		return arregloMedicionRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<MagnitudDetalle> todosMD(){
		return magnitudDetalleRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ArregloMedicion> findByUsuario(Integer idUsuario) {
		return arregloMedicionRepository.findByUsuario(usuarioRepository.findById(idUsuario).get());
	}

	@Override
	@Transactional
	public void eliminarArregloPorId(Integer id) {

		ArregloMedicion amr = arregloMedicionRepository.findById(id).get();

		for (MagnitudArreglo mar : amr.getModeloMatematico().getMagnitudesArreglo()) {
			if (mar.getMagnitudDetalle() != null) {
				magnitudDetalleRepository.deleteById(mar.getMagnitudDetalle().getIdMagnitudDetalle());
			}
			magnitudArregloRepository.deleteById(mar.getIdMagnitudArreglo());
		}

		for (DerivadaModeloMatematico dmmr : amr.getModeloMatematico().getDerivadasModeloMatematico()) {
			derivadaModeloMatematicoRepository.deleteById(dmmr.getIdDerivadaModeloMatematico());
		}
		modeloMatematicoRepository.deleteById(amr.getModeloMatematico().getIdModeloMatematico());
		arregloMedicionRepository.deleteById(amr.getIdArregloMedicion());
	}

	@Override
	@Transactional
	public ArregloMedicion insertarArreglo(ArregloMedicion entity) {
		// TODO Auto-generated method stub

		Imagen ir1 = imagenRepository.save(new Imagen(entity.getImagen().getImagen(), entity.getImagen().getLeyenda(),
				entity.getImagen().getDescripcion()));
		ArregloMedicion amaux = new ArregloMedicion(entity.getTitulo(), LocalDateTime.now(), entity.getVersion(),
				entity.getDescripcion(), entity.getFormatoCalCert());
		amaux.setImagen(ir1);
		Usuario u = entity.getUsuario();
		amaux.setUsuario(usuarioRepository.findByNombreUsuarioAndActivo(u.getNombreUsuario(), true).get(0));
		ArregloMedicion amr = arregloMedicionRepository.save(amaux);
		ModeloMatematico mmr = modeloMatematicoRepository
				.save(new ModeloMatematico(entity.getModeloMatematico().getEcuacion(), amr));
		List<DerivadaModeloMatematico> derivadas = new ArrayList<DerivadaModeloMatematico>();
		System.out.println(entity.getModeloMatematico().getDerivadasModeloMatematico());
		if (entity.getModeloMatematico().getDerivadasModeloMatematico() != null) {
			if (entity.getModeloMatematico().getDerivadasModeloMatematico().size() > 0) {
				System.out.println(entity.getModeloMatematico().getDerivadasModeloMatematico().size());
				for (DerivadaModeloMatematico dme : entity.getModeloMatematico().getDerivadasModeloMatematico()) {
					System.out.println(dme.getDerivadaParcial());
					DerivadaModeloMatematico dmr = derivadaModeloMatematicoRepository
							.save(new DerivadaModeloMatematico(dme.getDerivadaParcial(), dme.getRespectoA(),
									modeloMatematicoRepository.findById(mmr.getIdModeloMatematico()).get()));
					derivadas.add(dmr);
				}
			}
		}
		List<MagnitudArreglo> magnitudes = new ArrayList<MagnitudArreglo>();
		if (entity.getModeloMatematico().getMagnitudesArreglo() != null) {
			if (entity.getModeloMatematico().getMagnitudesArreglo().size() > 0) {
				for (MagnitudArreglo mae : entity.getModeloMatematico().getMagnitudesArreglo()) {
					MagnitudArreglo mar = magnitudArregloRepository
							.save(new MagnitudArreglo(mae.getMagnitud(), mae.getUnidad(), mae.getDefinicion(),
									mae.getCapturar(), mae.getRepetir(), mae.getAsociado(), mmr));
					if (mae.getMagnitudDetalle() != null) {
						Imagen ir2 = imagenRepository.save(new Imagen(mae.getMagnitudDetalle().getImagen().getImagen(),
								mae.getMagnitudDetalle().getImagen().getLeyenda(),
								mae.getMagnitudDetalle().getImagen().getDescripcion()));
						MagnitudDetalle mdr = magnitudDetalleRepository.save(new MagnitudDetalle(
								mae.getMagnitudDetalle().getTipo(), mae.getMagnitudDetalle().getDescripcion(),
								mae.getMagnitudDetalle().getDistribucion(),
								mae.getMagnitudDetalle().getEvaluacionIncertidumbre(),
								mae.getMagnitudDetalle().getMetodoObservacion(), mae.getMagnitudDetalle().getValor(),
								ir2, mar));
						mar.setMagnitudDetalle(mdr);
					}
					magnitudes.add(mar);
				}

			}
		}
		mmr.setDerivadasModeloMatematico(derivadas);
		mmr.setMagnitudesArreglo(magnitudes);
		amr.setModeloMatematico(mmr);
		return amr;
	}

	@Override
	@Transactional
	public ArregloMedicion actualizarArreglo(ArregloMedicion entity) {
		// TODO Auto-generated method stub

		Imagen ir1 = imagenRepository.save(entity.getImagen());
		ArregloMedicion amaux = new ArregloMedicion(entity.getIdArregloMedicion(), entity.getTitulo(),
				LocalDateTime.now(), entity.getVersion(), entity.getDescripcion(), entity.getFormatoCalCert(), ir1,
				usuarioRepository.findByNombreUsuarioAndActivo(entity.getUsuario().getNombreUsuario(), true).get(0));
		// amaux.setImagen(ir1);
		// Usuario u = entity.getUsuario();
		// amaux.setUsuario();
		ArregloMedicion amr = arregloMedicionRepository.save(amaux);
		ModeloMatematico mmr = modeloMatematicoRepository.save(new ModeloMatematico(
				entity.getModeloMatematico().getIdModeloMatematico(), entity.getModeloMatematico().getEcuacion(), amr));
		List<DerivadaModeloMatematico> derivadas = new ArrayList<DerivadaModeloMatematico>();
		for (DerivadaModeloMatematico dme : entity.getModeloMatematico().getDerivadasModeloMatematico()) {
			DerivadaModeloMatematico dmr = derivadaModeloMatematicoRepository.save(new DerivadaModeloMatematico(
					dme.getIdDerivadaModeloMatematico(), dme.getDerivadaParcial(), dme.getRespectoA(),
					modeloMatematicoRepository.findById(mmr.getIdModeloMatematico()).get()));
			derivadas.add(dmr);
		}

		List<MagnitudArreglo> magnitudes = new ArrayList<MagnitudArreglo>();

		for (MagnitudArreglo mae : entity.getModeloMatematico().getMagnitudesArreglo()) {
			MagnitudArreglo mar = magnitudArregloRepository
					.save(new MagnitudArreglo(mae.getIdMagnitudArreglo(), mae.getMagnitud(), mae.getUnidad(),
							mae.getDefinicion(), mae.getCapturar(), mae.getRepetir(), mae.getAsociado(), mmr));
			if (mae.getMagnitudDetalle() != null) {
				Imagen ir2 = imagenRepository.save(new Imagen(mae.getMagnitudDetalle().getImagen().getIdImagen(),
						mae.getMagnitudDetalle().getImagen().getImagen(),
						mae.getMagnitudDetalle().getImagen().getLeyenda(),
						mae.getMagnitudDetalle().getImagen().getDescripcion()));
				MagnitudDetalle mdr = magnitudDetalleRepository.save(new MagnitudDetalle(
						mae.getMagnitudDetalle().getIdMagnitudDetalle(), mae.getMagnitudDetalle().getTipo(),
						mae.getMagnitudDetalle().getDescripcion(), mae.getMagnitudDetalle().getDistribucion(),
						mae.getMagnitudDetalle().getEvaluacionIncertidumbre(),
						mae.getMagnitudDetalle().getMetodoObservacion(), mae.getMagnitudDetalle().getValor(), ir2,
						mar));
				mar.setMagnitudDetalle(mdr);
			}
			magnitudes.add(mar);
		}

		mmr.setDerivadasModeloMatematico(derivadas);
		mmr.setMagnitudesArreglo(magnitudes);
		amr.setModeloMatematico(mmr);

		return amr;

		// return null;
	}

	@Override
	@Transactional
	public MagnitudArreglo saveMagnitud(MagnitudArreglo entity) {
		MagnitudArreglo mar = magnitudArregloRepository.save(new MagnitudArreglo(entity.getIdMagnitudArreglo(),
				entity.getMagnitud(), entity.getUnidad(), entity.getDefinicion(), entity.getCapturar(),
				entity.getRepetir(), entity.getAsociado(), entity.getModeloMatematico()));
		if (entity.getMagnitudDetalle() != null) {
			Imagen ir = imagenRepository.save(new Imagen(entity.getMagnitudDetalle().getImagen().getIdImagen(),
					entity.getMagnitudDetalle().getImagen().getImagen(),
					entity.getMagnitudDetalle().getImagen().getLeyenda(),
					entity.getMagnitudDetalle().getImagen().getDescripcion()));
			MagnitudDetalle mdr = magnitudDetalleRepository.save(new MagnitudDetalle(
					entity.getMagnitudDetalle().getIdMagnitudDetalle(), entity.getMagnitudDetalle().getTipo(),
					entity.getMagnitudDetalle().getDescripcion(), entity.getMagnitudDetalle().getDistribucion(),
					entity.getMagnitudDetalle().getEvaluacionIncertidumbre(),
					entity.getMagnitudDetalle().getMetodoObservacion(), entity.getMagnitudDetalle().getValor(), ir,
					mar));
			mar.setMagnitudDetalle(mdr);
		}
		return mar;
	}

	@Override
	@Transactional
	public void deleteMagnitudesByModeloMatematico(ModeloMatematico modeloMatematico) {
		for (MagnitudArreglo mae : modeloMatematico.getMagnitudesArreglo()) {
			if (mae.getMagnitudDetalle() != null) {
				magnitudDetalleRepository.deleteById(mae.getMagnitudDetalle().getIdMagnitudDetalle());
			}
			magnitudArregloRepository.deleteById(mae.getIdMagnitudArreglo());
		}
	}

	@Override
	@Transactional
	public void deleteDerivadasByModeloMatematico(ModeloMatematico modeloMatematico) {
		derivadaModeloMatematicoRepository.deleteByModeloMatematico(modeloMatematico);
	}
	
	@Override
	@Transactional
	public MagnitudArreglo magnitudPorId(Integer id) {
		return magnitudArregloRepository.findById(id).get();
	}

}
