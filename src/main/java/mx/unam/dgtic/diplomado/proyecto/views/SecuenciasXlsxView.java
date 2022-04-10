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
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

@Component("secuenciasXlsxView")
public class SecuenciasXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setHeader("Content-Disposition", "attachment; filename=\"secuencias.xlsx\"");
		Sheet sheet = workbook.createSheet();
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("N° arreglo");
		header.createCell(1).setCellValue("Titulo");
		header.createCell(2).setCellValue("Fecha");
		header.createCell(3).setCellValue("Creador");
		header.createCell(4).setCellValue("N Puntos");
		int c=1;
		
		List<SecuenciaMedicion> secuencias = (List<SecuenciaMedicion>)model.get("secuencias");
		
		for (SecuenciaMedicion s : secuencias) {
			Row secuenciaRow = sheet.createRow(c);
			secuenciaRow.createCell(0).setCellValue(s.getIdSecuenciaMedicion());
			secuenciaRow.createCell(1).setCellValue(s.getTitulo());
			secuenciaRow.createCell(2).setCellValue(s.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			secuenciaRow.createCell(3).setCellValue(s.getUsuario().getNombre());
			secuenciaRow.createCell(4).setCellValue(s.getSecuenciaDetalles().size());
			c++;
		}
		
	}

}
