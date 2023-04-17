package com.example.JasperReportsPOC.JasperReportsPOC.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JasperReportsPOC.JasperReportsPOC.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
