package mx.unam.dgtic.diplomado.proyecto.controller;

import java.util.ArrayList;

import org.matheclipse.core.convert.VariablesSet;
import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.DerivadaModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;
import mx.unam.dgtic.diplomado.proyecto.service.IArregloService;

@Controller
@RequestMapping("/arreglos")
public class ArregloController {

	private ArrayList<MagnitudArreglo> alMagnitudes;
	private MagnitudArreglo maMagnitudArreglo;
	private Integer c = 0;
	private ArregloMedicion amArregloMedicion;
	private String f;

	@Autowired
	private IArregloService arregloService;

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public String listadoInicial(Model model) {

		f = "";
		c = 0;
		amArregloMedicion = new ArregloMedicion();
		alMagnitudes = new ArrayList<MagnitudArreglo>();
		maMagnitudArreglo = new MagnitudArreglo();
		Iterable<ArregloMedicion> arreglos = arregloService.findAll();
		model.addAttribute("arreglos", arreglos);
		return "ArreglosDefinidos";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "/ver/{id}")
	public String verArreglo(@PathVariable Integer id, Model model) {
		ArregloMedicion arreglo = arregloService.findById(id).get();
		model.addAttribute("arreglo", arreglo);
		model.addAttribute("f", arreglo.getModeloMatematico().getMagnitudesArreglo().get(0).getMagnitud());
		return "verArreglo";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public String nuevoArreglo(Model model) {
		c = 0;
		alMagnitudes = new ArrayList<MagnitudArreglo>();
		ArregloMedicion arreglo = new ArregloMedicion();
		model.addAttribute("arreglo", arreglo);
		return "crearArreglo";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/definirMagnitudes", method = RequestMethod.POST)
	public ModelAndView ecuacionProcesada(@ModelAttribute("arreglo") ArregloMedicion arreglo) {
		MagnitudArreglo magnitudArreglo = null;
		c = 0;
		f = "";
		String[] ecuaciones = arreglo.getModeloMatematico().getEcuacion().split("=");
		ExprEvaluator util = new ExprEvaluator();
		f = ecuaciones[0];
		IExpr expr = util.parse(ecuaciones[1]);
		String derivada = "diff(";
		magnitudArreglo = new MagnitudArreglo(ecuaciones[0], null, null, null, null, null);
		alMagnitudes.add(magnitudArreglo);
		magnitudArreglo = null;
		for (int i = 1; i <= VariablesSet.getVariables(expr).argSize(); i++) {
			magnitudArreglo = new MagnitudArreglo(VariablesSet.getVariables(expr).get(i).toString(), null, null, null,
					null, null);
			magnitudArreglo.setMagnitudDetalle(new MagnitudDetalle());
			alMagnitudes.add(magnitudArreglo);
			derivada += ecuaciones[1] + "," + VariablesSet.getVariables(expr).get(i).toString() + ")";
			arreglo.getModeloMatematico().getDerivadasModeloMatematico().add(new DerivadaModeloMatematico(
					util.eval(derivada).toString(), VariablesSet.getVariables(expr).get(i).toString()));
			derivada = "diff(";
		}
		ModelAndView model = new ModelAndView("definirMagnitudArreglo");
		model.addObject("magnitud", alMagnitudes.get(c));
		amArregloMedicion = arreglo;
		return model;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/siguiente")
	public ModelAndView siguienteMagnitud(@ModelAttribute("magnitud") MagnitudArreglo magnitud) {
		ModelAndView mv = null;
		System.out.println(magnitud);
		alMagnitudes.get(c).setUnidad(magnitud.getUnidad());
		alMagnitudes.get(c).setDefinicion(magnitud.getDefinicion());
		alMagnitudes.get(c).setCapturar(magnitud.getCapturar());
		alMagnitudes.get(c).setRepetir(magnitud.getRepetir());
		alMagnitudes.get(c).setAsociado(magnitud.getAsociado());
		alMagnitudes.get(c).setMagnitudDetalle(magnitud.getMagnitudDetalle());

		c++;
		if (c < alMagnitudes.size()) {
			mv = new ModelAndView("definirMagnitudArreglo");
			try {
				mv.addObject("magnitud", alMagnitudes.get(c));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return new ModelAndView("redirect:/arreglos/");
			}
		} else {
			mv = new ModelAndView("vistaFInalArreglo");
			amArregloMedicion.getModeloMatematico().setMagnitudesArreglo(alMagnitudes);
			mv.addObject("arreglo", amArregloMedicion);
			mv.addObject("f", f);
			c = 0;
		}
		return mv;
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(Authentication authentication) {
//		amArregloMedicion.setUsuario(new Usuario(1));
		System.out.println(authentication.getName());
		amArregloMedicion.setUsuario(new Usuario(authentication.getName()));
		if (amArregloMedicion.getIdArregloMedicion() == null) {
			arregloService.insertarArreglo(amArregloMedicion);
		} else {
			System.out.println(amArregloMedicion.getIdArregloMedicion());
			arregloService.actualizarArreglo(amArregloMedicion);
		}

		System.out.println(amArregloMedicion);

		return "redirect:/arreglos/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		ArregloMedicion arreglo = arregloService.findById(id).get();
		amArregloMedicion = arreglo;
		model.addAttribute("arreglo", arreglo);
		model.addAttribute("f", arreglo.getModeloMatematico().getMagnitudesArreglo().get(0).getMagnitud());
		return "editarArreglo";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/editar/magnitud/{id}")
	public String editarMagnitud(@PathVariable Integer id, Model model) {
		for (MagnitudArreglo magnitudArreglo : amArregloMedicion.getModeloMatematico().getMagnitudesArreglo()) {
			if (magnitudArreglo.getIdMagnitudArreglo().equals(id)) {
				model.addAttribute("magnitud", magnitudArreglo);
				maMagnitudArreglo = magnitudArreglo;
			}
		}
		return "editarMagnitudArreglo";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editar/magnitud/guardar/", method = RequestMethod.POST)
	public String guardarMagnitudEditada(@ModelAttribute("magnitud") MagnitudArreglo magnitud) {
		maMagnitudArreglo.setUnidad(magnitud.getUnidad());
		maMagnitudArreglo.setDefinicion(magnitud.getDefinicion());
		if (magnitud.getMagnitudDetalle() != null) {
			maMagnitudArreglo.setCapturar(magnitud.getCapturar());
			maMagnitudArreglo.setRepetir(magnitud.getRepetir());
			maMagnitudArreglo.setAsociado(magnitud.getAsociado());
			maMagnitudArreglo.getMagnitudDetalle().setTipo(magnitud.getMagnitudDetalle().getTipo());
			maMagnitudArreglo.getMagnitudDetalle().setDescripcion(magnitud.getMagnitudDetalle().getDescripcion());
			maMagnitudArreglo.getMagnitudDetalle().setDistribucion(magnitud.getMagnitudDetalle().getDistribucion());
			maMagnitudArreglo.getMagnitudDetalle()
					.setEvaluacionIncertidumbre(magnitud.getMagnitudDetalle().getEvaluacionIncertidumbre());
			maMagnitudArreglo.getMagnitudDetalle()
					.setMetodoObservacion(magnitud.getMagnitudDetalle().getMetodoObservacion());
			maMagnitudArreglo.getMagnitudDetalle().setValor(magnitud.getMagnitudDetalle().getValor());
			maMagnitudArreglo.getMagnitudDetalle().getImagen()
					.setImagen(magnitud.getMagnitudDetalle().getImagen().getImagen());
			maMagnitudArreglo.getMagnitudDetalle().getImagen()
					.setLeyenda(magnitud.getMagnitudDetalle().getImagen().getLeyenda());
			maMagnitudArreglo.getMagnitudDetalle().getImagen()
					.setDescripcion(magnitud.getMagnitudDetalle().getImagen().getDescripcion());
		}
		MagnitudArreglo mr = arregloService.saveMagnitud(maMagnitudArreglo);
		return "redirect:/arreglos/editar/" + mr.getModeloMatematico().getArregloMedicion().getIdArregloMedicion();
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/editar/guardar/{id}", method = RequestMethod.POST)
	public ModelAndView guardarActualizacion(@PathVariable Integer id,
			@ModelAttribute("arreglo") ArregloMedicion arreglo, Authentication authentication) {

		amArregloMedicion.setIdArregloMedicion(id);
		amArregloMedicion.setIdArregloMedicion(id);
		amArregloMedicion.setTitulo(arreglo.getTitulo());
		amArregloMedicion.setVersion(arreglo.getVersion());
		amArregloMedicion.setDescripcion(arreglo.getDescripcion());
		System.out.println(authentication.getName());
		amArregloMedicion.setUsuario(new Usuario(authentication.getName()));
		System.out.println(arreglo.getFormatoCalCert().length());

		if ((arreglo.getFormatoCalCert() != null) && (arreglo.getFormatoCalCert().length() > 1)) {
			amArregloMedicion.setFormatoCalCert(arreglo.getFormatoCalCert());
		}
		if ((arreglo.getImagen().getImagen() != null) && (arreglo.getImagen().getImagen().length() > 1)) {
			amArregloMedicion.getImagen().setImagen(arreglo.getImagen().getImagen());
		}
		amArregloMedicion.getImagen().setLeyenda(arreglo.getImagen().getLeyenda());
		amArregloMedicion.getImagen().setDescripcion(arreglo.getImagen().getDescripcion());
		System.out.println(arreglo.getModeloMatematico().getEcuacion());
		System.out.println(amArregloMedicion.getModeloMatematico().getEcuacion());
		if (amArregloMedicion.getModeloMatematico().getEcuacion().equals(arreglo.getModeloMatematico().getEcuacion())) {
			arregloService.actualizarArreglo(amArregloMedicion);
			return new ModelAndView("redirect:/arreglos/");
		} else {
			alMagnitudes = new ArrayList<MagnitudArreglo>();
			arregloService.deleteDerivadasByModeloMatematico(amArregloMedicion.getModeloMatematico());
			arregloService.deleteMagnitudesByModeloMatematico(amArregloMedicion.getModeloMatematico());
			amArregloMedicion.getModeloMatematico().setEcuacion(arreglo.getModeloMatematico().getEcuacion());
			return ecuacionProcesada(amArregloMedicion);
		}

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar/{id}")
	public String elimarArreglo(@PathVariable Integer id) {
		arregloService.eliminarArregloPorId(id);
		return "redirect:/arreglos/";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/pdf")
	public String pdf(Model model) {
		model.addAttribute("arreglos", arregloService.findAll());
		return "arreglosPdfView";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/xlsx")
	public String xlsx(Model model) {
		model.addAttribute("arreglos", arregloService.findAll());
		return "arreglosXlsxView";
	}

}
