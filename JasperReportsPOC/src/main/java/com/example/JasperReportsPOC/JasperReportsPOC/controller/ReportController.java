package com.example.JasperReportsPOC.JasperReportsPOC.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.JasperReportsPOC.JasperReportsPOC.Service.ReportService;
import com.example.JasperReportsPOC.JasperReportsPOC.entity.Employee;
import com.example.JasperReportsPOC.JasperReportsPOC.repository.EmployeeRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
public class ReportController {
	
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private ReportService reportService;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return reportService.exportReport(format);
    }
}
