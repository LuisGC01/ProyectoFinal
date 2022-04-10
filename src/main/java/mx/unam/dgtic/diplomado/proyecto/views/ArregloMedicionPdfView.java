package mx.unam.dgtic.diplomado.proyecto.views;

import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.DerivadaModeloMatematico;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.MagnitudArreglo;

@Component("verArreglo")
public class ArregloMedicionPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ArregloMedicion arreglo = (ArregloMedicion) model.get("arreglo");

		Instant instant = arreglo.getFecha().toInstant(ZoneOffset.UTC);
		Date d= Date.from(instant);
		DateFormat df =DateFormat.getInstance();
		df.setTimeZone(TimeZone.getTimeZone("AZOST"));
		PdfPTable table1 = new PdfPTable(1);
		table1.setSpacingAfter(20);
		table1.addCell(arreglo.getTitulo());
		table1.addCell("Fecha de creacion: " + df.format(d));
//		table1.addCell("Fecha de creacion: " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.US).format(d));
		table1.addCell("Creado por: " + arreglo.getUsuario().getNombreUsuario());
		table1.addCell("Version: " + arreglo.getVersion());
		table1.addCell("Descripcion del arreglo: " + arreglo.getDescripcion());
		table1.addCell("Formato Cal-Cert: " + arreglo.getFormatoCalCert());
		table1.addCell("Imagen");
		table1.addCell("Imagen referenciada " + arreglo.getImagen().getImagen());
		table1.addCell("Leyenda de la imagen: " + arreglo.getImagen().getLeyenda());
		table1.addCell("Descripcion de la imagen: " + arreglo.getImagen().getDescripcion());

		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		table2.addCell("Modelo Matematico:");
		table2.addCell("Ecuacion: " + arreglo.getModeloMatematico().getEcuacion());
		table2.addCell("Derivadas");
		for (DerivadaModeloMatematico dmm : arreglo.getModeloMatematico().getDerivadasModeloMatematico()) {
			table2.addCell("d(" + arreglo.getModeloMatematico().getMagnitudesArreglo().get(0).getMagnitud() + ")/d("
					+ dmm.getRespectoA() + ") = " + dmm.getDerivadaParcial());

		}
		table2.addCell("Magnitudes:");
		for (MagnitudArreglo ma : arreglo.getModeloMatematico().getMagnitudesArreglo()) {
			table2.addCell(ma.toString());
		}

		document.add(table1);
		document.add(table2);
	}

}
