package com.bridgelabz.timesheetapplication.model;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "employeeTable")
@NoArgsConstructor
public class EmployeeData {
    /*
    Employee Model:
    employeeId: unique identifier for the employee (int)
    firstName: first name of the employee (string)
    lastName: last name of the employee (string)
    email: email address of the employee (string)
    password: hashed password of the employee (string)
    role: role of the employee, such as "developer" or "manager" (string)
    reportingManagerId: ID of the employee's reporting manager (int)
    isActive: boolean value indicating whether the employee is active or not (boolean)

    */

    @Id
    @GeneratedValue
    @Column(name = "empId")
    private int employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "managerId")
    @ManyToOne(fetch = FetchType.LAZY)
    private ManagerData reportingManagerId;

    private boolean isActive;       //false = active

    public void updateEmployeeData(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.email = employeeDTO.getEmail();
        this.password = employeeDTO.getPassword();
    }
    public EmployeeData(EmployeeDTO employeeDTO){
        this.updateEmployeeData(employeeDTO);
    }
}
