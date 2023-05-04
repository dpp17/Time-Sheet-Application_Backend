package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import com.bridgelabz.timesheetapplication.repository.EmployeeRepo;
import com.bridgelabz.timesheetapplication.repository.ManagerRepo;
import com.bridgelabz.timesheetapplication.utility.JWTToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeBusinessLogics implements IEmployeeBusinessLogics{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private IManagerBusinessLogics iManagerBusinessLogics;

    @Autowired
    private JWTToken jwtToken;

    @Override
    public ResponseEntity<ResponseDTO> addNewEmployee(EmployeeDTO employeeDTO) {
        if(employeeDTO.getRole().equals("developer")) {
            EmployeeData isEmployeePresent = employeeRepo.findByEmailId(employeeDTO.getEmail());
            if(isEmployeePresent == null){  EmployeeData employeeData = new EmployeeData(employeeDTO);
                employeeData.setRole(employeeDTO.getRole());
                ManagerData managerData = managerRepo.findById(employeeDTO.getReportingManagerId()).orElseThrow();
                employeeData.setReportingManagerId(managerData);
                employeeRepo.save(employeeData);
                return new ResponseEntity<>(new ResponseDTO("Employee Added Successfully..",employeeData), HttpStatus.CREATED);
            }
             return new ResponseEntity<>(new ResponseDTO("Employee Already Exist....",isEmployeePresent), HttpStatus.UNAUTHORIZED);
        }
        return iManagerBusinessLogics.addNewManager(employeeDTO);
    }

    @Override
    public EmployeeData updateEmployeeDetails(EmployeeDTO employeeDTO, int empId) {
        EmployeeData employeeData = employeeRepo.findById(empId).orElseThrow();
        employeeData.updateEmployeeData(employeeDTO);
        employeeRepo.save(employeeData);
        return employeeData;
    }

    @Override
    public String deleteEmployeeById(int empId) {
        EmployeeData employeeData = employeeRepo.findById(empId).orElseThrow();
        employeeRepo.deleteById(empId);
        return "Employee Deleted Successfully..";
    }

    @Override
    public EmployeeData getEmployeeById(int empId) {
        return employeeRepo.findById(empId).orElseThrow();
    }

    @Override
    public List<EmployeeData> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public ResponseEntity<String> employeeLogin(String email, String password) {
        EmployeeData isDeveloperPresent = employeeRepo.findByEmailId(email);
        ManagerData isManagerPresent = managerRepo.findByEmailId(email);
        if(null != isManagerPresent){
            if(isManagerPresent.getPassword().equals(password)){
                String managerToken = jwtToken.createToken(isManagerPresent.getManagerId());
                return new ResponseEntity<String>( "Welcome Manager :: " + isManagerPresent.getFirstName() + " " + isManagerPresent.getLastName() +", Token :: " + managerToken, HttpStatus.OK);
            }return new ResponseEntity<String>("Wrong Password Inserted..", HttpStatus.UNAUTHORIZED);

        }else if(null != isDeveloperPresent){
            if(isDeveloperPresent.getPassword().equals(password)){
                String employeeToken = jwtToken.createToken(isDeveloperPresent.getEmployeeId());
                return new ResponseEntity<String>("Welcome Developer :: " + isDeveloperPresent.getFirstName() + " " + isDeveloperPresent.getLastName() +", Token :: " + employeeToken, HttpStatus.OK);
            }return new ResponseEntity<String>("Wrong Password Inserted..", HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<String>(" UserName Not Found..", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String> employeeLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<String>("Logged out..", HttpStatus.OK);
    }
}
