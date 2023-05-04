package com.bridgelabz.timesheetapplication.controller;

import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.dto.TimeSheetDTO;
import com.bridgelabz.timesheetapplication.model.TimeSheetData;
import com.bridgelabz.timesheetapplication.services.ITimeSheetBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeSheet")
public class TimeSheetController {
    /*
    Time sheet microservice: This microservice manages time sheet-related operations such as time sheet creation, updating timesheet details, deleting time sheet details, and so on. It can have the following APIs:
    POST /timesheets: Create a new timesheet
    PUT /timesheets/{timesheetId}: Update an existing timesheet
    DELETE /timesheets/{timesheetId}: Delete a timesheet
    GET /timesheets/{timesheetId}: Get a time sheet by ID
    GET /timesheets: Get all time sheets
    */

    @Autowired
    private ITimeSheetBusinessLogics iTimeSheetBusinessLogics;

    @PostMapping("/createTimeSheet")
    public ResponseEntity<ResponseDTO> addNewTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("New Time Sheet Created..", iTimeSheetBusinessLogics.addNewTimeSheet(timeSheetDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/updateTimeSheet")
    public ResponseEntity<ResponseDTO> updateTimeSheet(@RequestBody TimeSheetDTO timeSheetDTO, @RequestParam int timeSheetId){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Time Sheet Updated..", iTimeSheetBusinessLogics.updateTimeSheet(timeSheetDTO,timeSheetId)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteTimeSheet")
    public String deleteTimeSheetById(@RequestParam int timeSheetId){
        return iTimeSheetBusinessLogics.deleteTimeSheetById(timeSheetId);
    }

    @GetMapping("/getTimeSheet")
    public ResponseEntity<ResponseDTO> getTimeSheetById(@RequestParam int timeSheetId){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Time Sheet Updated..", iTimeSheetBusinessLogics.getTimeSheetById(timeSheetId)), HttpStatus.FOUND);
    }

    @GetMapping("/getAllTimeSheets")
    public List<TimeSheetData> getAllTimeSheets(){
        return iTimeSheetBusinessLogics.getAllTimeSheets();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              :: Time Sheet - Additional Features ::                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    PUT /timesheets/{timesheetId}/submit: Submit a time sheet for approval. This API would change the status of the time sheet from "draft" to "submitted", indicating that the employee has completed their time sheet and it is ready for review by their manager.
    PUT /timesheets/{timesheetId}/approve: Approve a time sheet. This API would change the status of the time sheet from "submitted" to "approved", indicating that the manager has reviewed and approved the employee's time sheet.
    PUT /timesheets/{timesheetId}/reject: Reject a time sheet. This API would change the status of the time sheet from "submitted" to "rejected", indicating that the manager has reviewed the employee's time sheet and has rejected it for some reason.
    GET /employees/ {employeeId}/timesheets: Get all time sheets for a particular employee. This API would return a list of all time sheets for the employee with the specified ID.
    GET /managers/  {managerId}/timesheets: Get all time sheets for a particular manager. This API would return a list of all time sheets that have been submitted for approval by the manager with the specified ID.
    GET /timesheets?status=submitted: Get all submitted time sheets. This API would return a list of all time sheets that have been submitted by employees and are waiting for approval by their managers.

    */

    @PutMapping("/submitTimeSheet/{Token}")
    public String submitTimeSheet(@PathVariable String employeeToken, @RequestParam int timeSheetId){
        return iTimeSheetBusinessLogics.submitTimeSheet(employeeToken,timeSheetId);
    }

    @PutMapping("/approveTimeSheet/{Token}")
    public String approveTimeSheet(@PathVariable String managerToken, @RequestParam int timeSheetId){
        return iTimeSheetBusinessLogics.approveTimeSheet(managerToken,timeSheetId);
    }

    @PutMapping("/rejectTimeSheet/{Token}")
    public String rejectTimeSheet(@PathVariable String managerToken, @RequestParam int timeSheetId){
        return iTimeSheetBusinessLogics.rejectTimeSheet(managerToken,timeSheetId);
    }

    @GetMapping("/getTimeSheetByEmployeeId")
    public  List<TimeSheetData> getTimeSheetByEmployeeId(@RequestParam int empId){
        return iTimeSheetBusinessLogics.getTimeSheetByEmployeeId(empId);
    }

    @GetMapping("/getTimeSheetByManagerId")
    public  List<TimeSheetData> getTimeSheetByManagerId(@RequestParam int managerId){
        return iTimeSheetBusinessLogics.getTimeSheetByManagerId(managerId);
    }

    @GetMapping("/getSubmittedTimeSheet")
    public List<TimeSheetData> getSubmittedTimeSheet(){
        return iTimeSheetBusinessLogics.getSubmittedTimeSheet();
    }
}
