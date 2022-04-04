package mx.unam.dgtic.diplomado.proyecto.controller;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.Usuario;
import mx.unam.dgtic.diplomado.proyecto.service.IArregloService;
import mx.unam.dgtic.diplomado.proyecto.service.ISecuenciaService;

@Controller
@RequestMapping("/secuencias")
@SessionAttributes("secuenciaMedicion")
public class SecuenciaController {
	@Autowired
	private ISecuenciaService secuenciaService;

	@Autowired
	private IArregloService arregloService;

	@RequestMapping("/")
	public String listarSecuencias(Model model) {
		model.addAttribute("secuencias", secuenciaService.findAll());
		return "SecuenciasDefinidas";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/crear")
	public String crear(Model model) {
		// System.out.println(smSecuenciaMedicion);
		SecuenciaMedicion secuenciaMedicion = new SecuenciaMedicion();
		model.addAttribute("secuenciaMedicion", secuenciaMedicion);
		model.addAttribute("arreglos", arregloService.findAll());
		model.addAttribute("magnitudes", arregloService.todosMD());
		return "crearSecuencia";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardarSecuencia(SecuenciaMedicion secuenciaMedicion,
			@RequestParam(name = "arrTipos[]", required = false) String arrTipos[],
			@RequestParam(name = "arrDescs[]", required = false) String arrDescs[],
			@RequestParam(name = "arrArrMed[]", required = false) Integer arrArrMed[],
			@RequestParam(name = "arrVal[]", required = false) String arrVal[],
			@RequestParam(name = "arrBarrPrin[]", required = false) String arrBarrPrin[],
			@RequestParam(name = "arrBarrSec[]", required = false) String arrBarrSec[],
			@RequestParam(name = "arrNumMed[]", required = false) Integer arrNumMed[],
			@RequestParam(name = "arrInst[]", required = false) String arrInst[],
			@RequestParam(name = "arrComens[]", required = false) String arrComens[],
			@RequestParam(name = "arrMag[]", required = false) Integer arrMag[],
			@RequestParam(name = "arrTxtD[]", required = false) String arrTxtD[],
			@RequestParam(name = "arrInpD[]", required = false) String arrInpD[],
			@RequestParam(name = "arrValid[]", required = false) String arrValid[], SessionStatus status,
			Authentication authentication) {
		secuenciaMedicion.setUsuario(new Usuario(authentication.getName()));
//		secuenciaMedicion.setUsuario(new Usuario(1));
		for (int i = 0; i < arrTipos.length; i++) {
			secuenciaMedicion.getSecuenciaDetalles()
					.add(new SecuenciaDetalle(i + 1, arrTipos[i].charAt(0),
							((arrDescs[i].length() > 0 && arrDescs[i] != null) ? arrDescs[i] : null),
							((arrVal[i] != null && arrVal[i].length() > 0) ? arrVal[i] : null),
							((arrBarrPrin[i] != null && arrBarrPrin[i].length() > 0) ? arrBarrPrin[i] : null),
							((arrBarrSec[i] != null && arrBarrSec[i].length() > 0) ? arrBarrSec[i] : null),
							arrNumMed[i], ((arrInst[i] != null && arrInst[i].length() > 0) ? arrInst[i] : null),
							((arrComens[i] != null && arrComens[i].length() > 0) ? arrComens[i] : null),
							((arrTxtD[i] != null && arrTxtD[i].length() > 0) ? arrTxtD[i] : null),
							((arrInpD[i] != null && arrInpD[i].length() > 0) ? arrInpD[i] : null),
							((arrValid[i] != null && arrValid[i].length() > 0) ? arrValid[i] : null),
							((arrArrMed[i] != null && arrArrMed[i] > 0) ? new ArregloMedicion(arrArrMed[i]) : null),
							((arrMag[i] != null && arrMag[i] > 0) ? new MagnitudArreglo(arrMag[i]) : null)));
		}
		secuenciaService.insertarSecuencia(secuenciaMedicion);
		status.setComplete();
		return "redirect:/secuencias/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/eliminar/{id}")
	public String eliminarArreglo(@PathVariable Integer id) {
		secuenciaService.borrarSecuenciaPorId(id);
		return "redirect:/secuencias/";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/guardarActualizar/{id}", method = RequestMethod.POST)
	public String guardarSecuenciaActualizada(@PathVariable Integer id, SecuenciaMedicion secuenciaMedicion,
			@RequestParam(name = "idBD[]", required = false) Integer idBD[],
			@RequestParam(name = "arrTipos[]", required = false) String arrTipos[],
			@RequestParam(name = "arrDescs[]", required = false) String arrDescs[],
			@RequestParam(name = "arrArrMed[]", required = false) Integer arrArrMed[],
			@RequestParam(name = "arrVal[]", required = false) String arrVal[],
			@RequestParam(name = "arrBarrPrin[]", required = false) String arrBarrPrin[],
			@RequestParam(name = "arrBarrSec[]", required = false) String arrBarrSec[],
			@RequestParam(name = "arrNumMed[]", required = false) Integer arrNumMed[],
			@RequestParam(name = "arrInst[]", required = false) String arrInst[],
			@RequestParam(name = "arrComens[]", required = false) String arrComens[],
			@RequestParam(name = "arrMag[]", required = false) Integer arrMag[],
			@RequestParam(name = "arrTxtD[]", required = false) String arrTxtD[],
			@RequestParam(name = "arrInpD[]", required = false) String arrInpD[],
			@RequestParam(name = "arrValid[]", required = false) String arrValid[], SessionStatus status,
			Authentication authentication) {

		secuenciaMedicion.setUsuario(new Usuario(authentication.getName()));
		secuenciaMedicion.setIdSecuenciaMedicion(id);
		secuenciaMedicion.setSecuenciaDetalles(new LinkedList<SecuenciaDetalle>());
		// secuenciaMedicion.setUsuario(new Usuario(1));
		for (int i = 0; i < arrTipos.length; i++) {
			// System.out.println(i+" - "+arrTipos[i]+", "+idBD[i]+", "+arrDescs[i]);
			secuenciaMedicion.getSecuenciaDetalles()
					.add(new SecuenciaDetalle(idBD[i], i + 1, arrTipos[i].charAt(0),
							((arrDescs[i].length() > 0 && arrDescs[i] != null) ? arrDescs[i] : null),
							((arrVal[i] != null && arrVal[i].length() > 0) ? arrVal[i] : null),
							((arrBarrPrin[i] != null && arrBarrPrin[i].length() > 0) ? arrBarrPrin[i] : null),
							((arrBarrSec[i] != null && arrBarrSec[i].length() > 0) ? arrBarrSec[i] : null),
							arrNumMed[i], ((arrInst[i] != null && arrInst[i].length() > 0) ? arrInst[i] : null),
							((arrComens[i] != null && arrComens[i].length() > 0) ? arrComens[i] : null),
							((arrTxtD[i] != null && arrTxtD[i].length() > 0) ? arrTxtD[i] : null),
							((arrInpD[i] != null && arrInpD[i].length() > 0) ? arrInpD[i] : null),
							((arrValid[i] != null && arrValid[i].length() > 0) ? arrValid[i] : null),
							((arrArrMed[i] != null && arrArrMed[i] > 0) ? new ArregloMedicion(arrArrMed[i]) : null),
							((arrMag[i] != null && arrMag[i] > 0) ? new MagnitudArreglo(arrMag[i]) : null)));
		}

		System.out.println(secuenciaMedicion);
		secuenciaService.actualizarSecuencia(secuenciaMedicion);
		status.setComplete();
		return "redirect:/secuencias/";
	}

	@Secured("ROLE_USER")
	@RequestMapping("/ver/{id}")
	public String verSecuencia(@PathVariable Integer id, Model model) {
		SecuenciaMedicion secuenciaMedicion = secuenciaService.findById(id).get();
		model.addAttribute("secuenciaMedicion", secuenciaMedicion);
		return "verSecuencia";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/editar/{id}")
	public String cargarEdicion(@PathVariable Integer id, Model model) {
		SecuenciaMedicion secuenciaMedicion = secuenciaService.findById(id).get();
		model.addAttribute("secuenciaMedicion", secuenciaMedicion);
		model.addAttribute("arreglos", arregloService.findAll());
		model.addAttribute("magnitudes", arregloService.todosMD());
		return "editarSecuencia";

	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/pdf")
	public String pdf(Model model) {
		model.addAttribute("secuencias", secuenciaService.findAll());
		return "secuenciasPdfView";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping("/xlsx")
	public String xlsx(Model model) {
		model.addAttribute("secuencias", secuenciaService.findAll());
		return "secuenciasXlsxView";
	}

}
