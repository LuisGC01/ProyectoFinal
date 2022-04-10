package mx.unam.dgtic.diplomado.proyecto.views;

import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaDetalle;
import mx.unam.dgtic.diplomado.proyecto.modelo.entidades.SecuenciaMedicion;

@Component("verSecuencia.xlsx")
public class SecuenciaMedicionXlsxView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		SecuenciaMedicion secuencia = (SecuenciaMedicion) model.get("secuenciaMedicion");
		Instant instant = secuencia.getFecha().toInstant(ZoneOffset.UTC);
		Date d = Date.from(instant);
		DateFormat df = DateFormat.getInstance();
		df.setTimeZone(TimeZone.getTimeZone("AZOST"));
		
		Sheet sheet = workbook.createSheet();

		sheet.createRow(0).createCell(0).setCellValue(secuencia.getTitulo());
		sheet.createRow(1).createCell(0).setCellValue("Fecha de creacion: " + df.format(d));
		sheet.createRow(2).createCell(0).setCellValue("Creado por: " + secuencia.getUsuario().getNombreUsuario());
		sheet.createRow(3).createCell(0).setCellValue("Descripcion: " + secuencia.getDescripcion());
		
		Row headerTabla = sheet.createRow(5);
		headerTabla.createCell(0).setCellValue("N°");                
		headerTabla.createCell(1).setCellValue("Tipo");              
		headerTabla.createCell(2).setCellValue("Descripcion");       
		headerTabla.createCell(3).setCellValue("Arreglo");           
		headerTabla.createCell(4).setCellValue("Valor");             
		headerTabla.createCell(5).setCellValue("Barrido principal"); 
		headerTabla.createCell(6).setCellValue("Barrido secundario");
		headerTabla.createCell(7).setCellValue("N° mediciones");     
		headerTabla.createCell(8).setCellValue("Instrucciones");     
		headerTabla.createCell(9).setCellValue("Comentarios");       
		headerTabla.createCell(10).setCellValue("Magnitud");          
		headerTabla.createCell(11).setCellValue("Texto");             
		headerTabla.createCell(12).setCellValue("Entrada");           
		headerTabla.createCell(13).setCellValue("Validacion");        
		
		int i = 6;
		for (SecuenciaDetalle sd : secuencia.getSecuenciaDetalles()) {
			Row fila = sheet.createRow(i++);
			fila.createCell(0).setCellValue(sd.getNumeroPuntoSecuencia() + "");                                             
			fila.createCell(1).setCellValue(sd.getTipoPunto() + "");                                                        
			fila.createCell(2).setCellValue(sd.getDescripcionPunto());                                                      
			fila.createCell(3).setCellValue((sd.getArregloMedicion() != null) ? sd.getArregloMedicion().getTitulo() : "");  
			fila.createCell(4).setCellValue(sd.getValor());                                                                 
			fila.createCell(5).setCellValue(sd.getBarridoPrincipal());                                                      
			fila.createCell(6).setCellValue(sd.getBarridoSecundario());                                                     
			fila.createCell(7).setCellValue((sd.getNumeroMediciones() != null) ? sd.getNumeroMediciones() + "" : "");       
			fila.createCell(8).setCellValue(sd.getInstrucciones());                                                         
			fila.createCell(9).setCellValue(sd.getComentario());                                                            
			fila.createCell(10).setCellValue((sd.getMagnitudArreglo() != null) ? sd.getMagnitudArreglo().getMagnitud() : "");
			fila.createCell(11).setCellValue(sd.getDialogoTxt());                                                            
			fila.createCell(12).setCellValue(sd.getDialogoEntrada());                                                        
			fila.createCell(13).setCellValue(sd.getDialogoValidacion());                                                     
		}
		
	}

}
