package com.bridgelabz.timesheetapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ManagerDTO {

    @NotBlank(message = "FirstName Cannot be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z]{2,}$", message = "Start FirstName With Capital Letter")
    private String firstName;

    @NotBlank(message = "LastName Cannot be empty")
    @Pattern(regexp = "^[A-Z][A-Za-z]{2,}$", message = "Start LastName With Capital Letter")
    private String lastName;

    @NotNull(message = "Email ID Cannot be empty")
    @Pattern(regexp = "^[a-z_0-9]{2,}@gmail.com$", message = "give email in format :: xxxxxxxx@gmail.com")
    private String email;

    @NotBlank(message = "Password Cannot be empty")
    @Pattern(regexp = "^[a-zA-Z_0-9]{1,}[!@#$%^&*][a-zA-Z_0-9]{1,}$", message = "password must have 1 Special Character")
    private String password;

    public ManagerDTO(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.email = employeeDTO.getEmail();
        this.password = employeeDTO.getPassword();
    }
}
