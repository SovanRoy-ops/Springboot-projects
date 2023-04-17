package com.example.JasperReportsPOC.JasperReportsPOC.Service;

import com.example.JasperReportsPOC.JasperReportsPOC.entity.Employee;
import com.example.JasperReportsPOC.JasperReportsPOC.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
	
	  @Autowired
	    private EmployeeRepository repository;

	    public String exportReport(String reportFormat) throws FileNotFoundException, JRException{
	        String path = "D:";
	        List<Employee> employees = repository.findAll();
	        //load file and compile it
	        File file = ResourceUtils.getFile("classpath:EmployeeReport.jrxml");
	        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy", "created by Sovan Roy");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        if (reportFormat.equalsIgnoreCase("html")) {
	            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
	        }
	        else if (reportFormat.equalsIgnoreCase("pdf")) {
	            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
	        }
//			else if (reportFormat.equalsIgnoreCase("excel")) {
//				JRXlsExporter xlsExporter = new JRXlsExporter();
//				String outXlsName = "test.xls";
//				xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//				xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outXlsName));
//				SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
//				xlsReportConfiguration.setOnePagePerSheet(false);
//				xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
//				xlsReportConfiguration.setDetectCellType(false);
//				xlsReportConfiguration.setWhitePageBackground(false);
//				xlsExporter.setConfiguration(xlsReportConfiguration);
//
//				xlsExporter.exportReport();
//			}
			return "report generated in path : " + path;
	    }
	}