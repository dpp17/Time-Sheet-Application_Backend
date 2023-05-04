package com.bridgelabz.timesheetapplication.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TimeSheetDTO {

    @NotNull(message = "Employee ID Cannot Be Null")
    private int employeeId;

    @NotNull(message = "Manager ID Cannot Be Null")
    private int managerId;

    @NotEmpty(message = "Description Cannot Be Null")
    private String description;
}
