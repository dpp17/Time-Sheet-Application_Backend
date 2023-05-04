package com.bridgelabz.timesheetapplication.exception;

public class EmployeeIdNotFoundException extends RuntimeException{
    public EmployeeIdNotFoundException(String message) {
        super(message);
    }
}
