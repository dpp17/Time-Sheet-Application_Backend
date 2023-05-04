package com.bridgelabz.timesheetapplication.model;

import com.bridgelabz.timesheetapplication.dto.ManagerDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "managerTable")
@NoArgsConstructor
public class ManagerData {
    /*
    Manager Model:
    managerId: unique identifier for the manager (int)
    firstName: first name of the manager (string)
    lastName: last name of the manager (string)
    email: email address of the manager (string)
    password: hashed password of the manager (string)
    isActive: boolean value indicating whether the manager is active or not (boolean)

    */

    @Id
    @GeneratedValue
    private int managerId;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private boolean isActive;

    public void updateManagerData(ManagerDTO managerDTO) {
        this.firstName = managerDTO.getFirstName();
        this.lastName = managerDTO.getLastName();
        this.email = managerDTO.getEmail();
        this.password = managerDTO.getPassword();
    }

    public ManagerData(ManagerDTO managerDTO){
        this.updateManagerData(managerDTO);
    }

}
