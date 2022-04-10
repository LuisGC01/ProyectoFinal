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

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;

@Component("arreglosPdfView")
public class ArreglosPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(model);

		List<ArregloMedicion> arreglos = (List<ArregloMedicion>) model.get("arreglos");
		PdfPTable table = new PdfPTable(5);
		table.addCell("N° arreglo");
		table.addCell("Titulo");
		table.addCell("Version");
		table.addCell("Fecha");
		table.addCell("Creador");
		for (ArregloMedicion arregloMedicion : arreglos) {

			table.addCell(arregloMedicion.getIdArregloMedicion().toString());
			table.addCell(arregloMedicion.getTitulo());
			table.addCell(arregloMedicion.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			table.addCell(arregloMedicion.getVersion());
			table.addCell(arregloMedicion.getUsuario().getNombreUsuario());
		}
		document.add(table);
	}

}
