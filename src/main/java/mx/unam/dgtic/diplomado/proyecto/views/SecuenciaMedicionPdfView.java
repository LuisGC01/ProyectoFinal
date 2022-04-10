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

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

@Component("verSecuencia")
public class SecuenciaMedicionPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		SecuenciaMedicion secuencia = (SecuenciaMedicion) model.get("secuenciaMedicion");
		Instant instant = secuencia.getFecha().toInstant(ZoneOffset.UTC);
		Date d = Date.from(instant);
		DateFormat df = DateFormat.getInstance();
		df.setTimeZone(TimeZone.getTimeZone("AZOST"));

		PdfPTable table1 = new PdfPTable(1);
		table1.setSpacingAfter(20);
		table1.addCell(secuencia.getTitulo());
		table1.addCell("Fecha de creacion: " + df.format(d));
		table1.addCell("Creado por: " + secuencia.getUsuario().getNombreUsuario());
		table1.addCell("Descripcion: " + secuencia.getDescripcion());

		PdfPTable table2 = new PdfPTable(1);
		table2.setSpacingAfter(20);
		table2.addCell("Puntos:");

		PdfPTable table3 = new PdfPTable(14);
		table3.addCell("N°");
		table3.addCell("Tipo");
		table3.addCell("Descripcion");
		table3.addCell("Arreglo");
		table3.addCell("Valor");
		table3.addCell("Barrido principal");
		table3.addCell("Barrido secundario");
		table3.addCell("N° mediciones");
		table3.addCell("Instrucciones");
		table3.addCell("Comentarios");
		table3.addCell("Magnitud");
		table3.addCell("Texto");
		table3.addCell("Entrada");
		table3.addCell("Validacion");

		for (SecuenciaDetalle sd : secuencia.getSecuenciaDetalles()) {
			table3.addCell(sd.getNumeroPuntoSecuencia() + "");
			table3.addCell(sd.getTipoPunto() + "");
			table3.addCell(sd.getDescripcionPunto());
			table3.addCell((sd.getArregloMedicion() != null) ? sd.getArregloMedicion().getTitulo() : "");
			table3.addCell(sd.getValor());
			table3.addCell(sd.getBarridoPrincipal());
			table3.addCell(sd.getBarridoSecundario());
			table3.addCell((sd.getNumeroMediciones() != null) ? sd.getNumeroMediciones() + "" : "");
			table3.addCell(sd.getInstrucciones());
			table3.addCell(sd.getComentario());
			table3.addCell((sd.getMagnitudArreglo() != null) ? sd.getMagnitudArreglo().getMagnitud() : "");
			table3.addCell(sd.getDialogoTxt());
			table3.addCell(sd.getDialogoEntrada());
			table3.addCell(sd.getDialogoValidacion());
		}

		document.add(table1);
		document.add(table2);
		document.add(table3);
	}

}
