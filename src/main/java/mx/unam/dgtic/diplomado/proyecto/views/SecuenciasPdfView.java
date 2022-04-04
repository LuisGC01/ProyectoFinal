package mx.unam.dgtic.diplomado.proyecto.views;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

@Component("secuenciasPdfView")
public class SecuenciasPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(model);

		List<SecuenciaMedicion> secuencias = (List<SecuenciaMedicion>) model.get("secuencias");
		PdfPTable table = new PdfPTable(5);
		table.addCell("N� arreglo");
		table.addCell("Titulo");
		table.addCell("Fecha");
		table.addCell("Creador");
		table.addCell("N� puntos");
		for (SecuenciaMedicion secuenciaMedicion : secuencias) {
			table.addCell(secuenciaMedicion.getIdSecuenciaMedicion().toString());
			table.addCell(secuenciaMedicion.getTitulo());
			table.addCell(secuenciaMedicion.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			table.addCell(secuenciaMedicion.getUsuario().getNombreUsuario());
			table.addCell(secuenciaMedicion.getSecuenciaDetalles().size()+"");
		}
		document.add(table);
	}

}
