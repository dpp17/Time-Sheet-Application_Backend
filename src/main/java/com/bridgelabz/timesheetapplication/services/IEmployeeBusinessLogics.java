package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEmployeeBusinessLogics {
    Object addNewEmployee(EmployeeDTO employeeDTO);

    EmployeeData updateEmployeeDetails(EmployeeDTO employeeDTO, int empId);

    String deleteEmployeeById(int empId);

    EmployeeData getEmployeeById(int empId);

    List<EmployeeData> getAllEmployees();

    ResponseEntity<String>  employeeLogin(String email, String password);

    ResponseEntity<String> employeeLogout(HttpServletRequest request);
}
