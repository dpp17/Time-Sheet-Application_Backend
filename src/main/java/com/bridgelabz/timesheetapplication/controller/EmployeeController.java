package com.bridgelabz.timesheetapplication.controller;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.services.IEmployeeBusinessLogics;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    /*
    Employee : This microservice manages employee-related operations such as employee creation, updating employee details,
     deleting employee details, and so on. It can have the following APIs:
    POST /employees: Create a new employee
    PUT /employees/{employeeId}: Update an existing employee
    DELETE /employees/{employeeId}: Delete an employee
    GET /employees/{employeeId}: Get an employee by ID
    GET /employees: Get all employees
    */

    @Autowired
    private IEmployeeBusinessLogics iEmployeeBusinessLogics;

    @PostMapping("/addEmployee")
    public ResponseEntity<ResponseDTO> addNewEmployee(@RequestBody EmployeeDTO employeeDTO){
    return new ResponseEntity<>(new ResponseDTO("Employee Addition Processed..",iEmployeeBusinessLogics.addNewEmployee(employeeDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<ResponseDTO> updateEmployeeDetails(@RequestBody EmployeeDTO employeeDTO, @RequestParam int empId){
        return new ResponseEntity<>(new ResponseDTO("Employee Details Updated Successfully..",iEmployeeBusinessLogics.updateEmployeeDetails(employeeDTO,empId)),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteEmployee")
    public String deleteEmployeeById(@RequestParam int empId){
       return iEmployeeBusinessLogics.deleteEmployeeById(empId);
    }

    @GetMapping("/getEmployeeById")
    public ResponseEntity<ResponseDTO> getEmployeeById(@RequestParam int empId){
        return new ResponseEntity<>(new ResponseDTO("Employee Details..",iEmployeeBusinessLogics.getEmployeeById(empId)),HttpStatus.FOUND);
    }

    @GetMapping("/getAllEmployees")
    public List<EmployeeData> getAllEmployees(){
        return iEmployeeBusinessLogics.getAllEmployees();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Employee - Login ::                                         //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/login")
    public ResponseEntity<String>  employeeLogin(@RequestParam String email,@RequestParam String password){
        return iEmployeeBusinessLogics.employeeLogin(email,password);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                                     :: Employee - Logout ::                                        //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/logout")
    public ResponseEntity<String> employeeLogout(@RequestParam HttpServletRequest request){
        return iEmployeeBusinessLogics.employeeLogout(request);
    }
}
