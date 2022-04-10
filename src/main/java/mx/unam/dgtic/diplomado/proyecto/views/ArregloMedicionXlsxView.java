package mx.unam.dgtic.diplomado.proyecto.views;

import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.DerivadaModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;

@Component("verArreglo.xlsx")
public class ArregloMedicionXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArregloMedicion arreglo = (ArregloMedicion) model.get("arreglo");

		Instant instant = arreglo.getFecha().toInstant(ZoneOffset.UTC);
		Date d = Date.from(instant);
		DateFormat df = DateFormat.getInstance();
		df.setTimeZone(TimeZone.getTimeZone("AZOST"));
		Sheet sheet = workbook.createSheet();

		sheet.createRow(0).createCell(0).setCellValue(arreglo.getTitulo());
		sheet.createRow(1).createCell(0).setCellValue("Fecha de creacion: " + df.format(d));
		sheet.createRow(2).createCell(0).setCellValue("Creado por: " + arreglo.getUsuario().getNombreUsuario());
		sheet.createRow(3).createCell(0).setCellValue("Version: " + arreglo.getVersion());
		sheet.createRow(4).createCell(0).setCellValue("Descripcion del arreglo: " + arreglo.getDescripcion());
		sheet.createRow(5).createCell(0).setCellValue("Formato Cal-Cert: " + arreglo.getFormatoCalCert());
		sheet.createRow(6).createCell(0).setCellValue("Imagen");
		sheet.createRow(7).createCell(0).setCellValue("Imagen referenciada " + arreglo.getImagen().getImagen());
		sheet.createRow(8).createCell(0).setCellValue("Leyenda de la imagen: " + arreglo.getImagen().getLeyenda());
		sheet.createRow(9).createCell(0)
				.setCellValue("Descripcion de la imagen: " + arreglo.getImagen().getDescripcion());
		sheet.createRow(11).createCell(0).setCellValue("Modelo Matematico: ");
		sheet.createRow(12).createCell(0).setCellValue("Ecuacion: " + arreglo.getModeloMatematico().getEcuacion());
		sheet.createRow(13).createCell(0).setCellValue("Derivadas");

		int i = 14;
		for (DerivadaModeloMatematico dmm : arreglo.getModeloMatematico().getDerivadasModeloMatematico()) {
			Row fila = sheet.createRow(i++);
			fila.createCell(0)
					.setCellValue("d(" + arreglo.getModeloMatematico().getMagnitudesArreglo().get(0).getMagnitud()
							+ ")/d(" + dmm.getRespectoA() + ") = " + dmm.getDerivadaParcial());
		}

		sheet.createRow(i++).createCell(0).setCellValue("Magnitudes");

		for (MagnitudArreglo ma : arreglo.getModeloMatematico().getMagnitudesArreglo()) {
			Row fila = sheet.createRow(i++);
			fila.createCell(0).setCellValue(ma.toString());
		}

	}

}
