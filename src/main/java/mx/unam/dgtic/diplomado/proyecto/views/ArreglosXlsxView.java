package mx.unam.dgtic.diplomado.proyecto.views;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.ArregloMedicion;

@Component("arreglosXlsxView")
public class ArreglosXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"arreglos.xlsx\"");
		Sheet sheet = workbook.createSheet();
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("N° arreglo");
		header.createCell(1).setCellValue("Titulo");
		header.createCell(2).setCellValue("Fecha");
		header.createCell(3).setCellValue("Version");
		header.createCell(4).setCellValue("Creador");
		int c=1;
		
		List<ArregloMedicion> arreglos = (List<ArregloMedicion>)model.get("arreglos");
		
		for (ArregloMedicion a : arreglos) {
			Row arregloRow = sheet.createRow(c);
			arregloRow.createCell(0).setCellValue(a.getIdArregloMedicion());
			arregloRow.createCell(1).setCellValue(a.getTitulo());
			arregloRow.createCell(2).setCellValue(a.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			arregloRow.createCell(3).setCellValue(a.getVersion());
			arregloRow.createCell(4).setCellValue(a.getUsuario().getNombre());
			c++;
		}
		
	}

}
