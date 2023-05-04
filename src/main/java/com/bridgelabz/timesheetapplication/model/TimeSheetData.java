package com.bridgelabz.timesheetapplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "timeSheetTable")
public class TimeSheetData {
    /*
    Time Sheet Model:
    timesheetId: unique identifier for the time sheet (int)
    employeeId: ID of the employee who created the time sheet (int)
    managerId: ID of the manager who approved the time sheet (int)
    startTime: start date of the time sheet period (datetime)
    endTime: end date of the time sheet period (datetime)
    Description: text
    Date: localDate
    status: status of the time sheet, such as "draft", "submitted", "approved", or "rejected" (string)
    */
    @Id
    @GeneratedValue
    private int timesheetId;

    @ManyToOne
    private EmployeeData employeeId;

    @ManyToOne
    private ManagerData managerId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private LocalDate date;

    private String status;

    public void updateTimeSheetData(EmployeeData employeeId, ManagerData managerId, String description) {
        this.employeeId = employeeId;
        this.managerId = managerId;
        this.description = description;
    }

    public TimeSheetData(EmployeeData employeeId, ManagerData managerId, String description) {
        this.updateTimeSheetData(employeeId,managerId,description);
    }
}
