package br.recife.fucturajava4.reports;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Relatorios {
	public static byte[] apresentar(String jrxml, Map<String,Object> parametros, List<?> beanCollection){
		try{
			//gerando o jasper design
			JasperDesign desenho = JRXmlLoader.load(jrxml);
			
			//compila o relatório
			JasperReport relatorio = JasperCompileManager.compileReport( desenho );
			
			//Cria datasource a partir de sua lista de objetos
			JRDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(beanCollection);
			
			//executa o relatório
			JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros, beanCollectionDataSource);
			
			//Exporta no formato de pdf
			byte[] bytes = JasperExportManager.exportReportToPdf(impressao);
			
			return bytes;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
