package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.TimeSheetDTO;
import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import com.bridgelabz.timesheetapplication.model.TimeSheetData;
import com.bridgelabz.timesheetapplication.repository.EmployeeRepo;
import com.bridgelabz.timesheetapplication.repository.ManagerRepo;
import com.bridgelabz.timesheetapplication.repository.TimeSheetRepo;
import com.bridgelabz.timesheetapplication.utility.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimeSheetBusinessLogics implements ITimeSheetBusinessLogics{

    @Autowired
    private TimeSheetRepo timeSheetRepo;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ManagerRepo managerRepo;
    @Autowired
    private JWTToken jwtToken;
    @Override
    public TimeSheetData addNewTimeSheet(TimeSheetDTO timeSheetDTO) {
        EmployeeData employeeData = employeeRepo.findById(timeSheetDTO.getEmployeeId()).orElseThrow();
        ManagerData managerData = managerRepo.findById(timeSheetDTO.getManagerId()).orElseThrow();
        TimeSheetData timeSheetData = new TimeSheetData(employeeData,managerData,timeSheetDTO.getDescription());
        timeSheetData.setStartTime(LocalDateTime.now());
        timeSheetData.setDate(LocalDate.now());
        timeSheetData.setStatus("draft");
        timeSheetRepo.save(timeSheetData);
        return timeSheetData;
    }

    @Override
    public TimeSheetData updateTimeSheet(TimeSheetDTO timeSheetDTO, int timeSheetId) {
        TimeSheetData timeSheetData = timeSheetRepo.findById(timeSheetId).orElseThrow();
        timeSheetData.updateTimeSheetData(employeeRepo.findById(timeSheetDTO.getEmployeeId()).orElseThrow(),managerRepo.findById(timeSheetDTO.getManagerId()).orElseThrow(),timeSheetDTO.getDescription());
        timeSheetData.setDate(LocalDate.now());
        timeSheetRepo.save(timeSheetData);
        return timeSheetData;
    }

    @Override
    public String deleteTimeSheetById(int timeSheetId) {
        timeSheetRepo.deleteById(timeSheetId);
        return "Time Sheet Deleted Successfully..";
    }

    @Override
    public TimeSheetData getTimeSheetById(int timeSheetId) {
        return timeSheetRepo.findById(timeSheetId).orElseThrow();
    }

    @Override
    public List<TimeSheetData> getAllTimeSheets() {
        return timeSheetRepo.findAll();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              :: Time Sheet - Additional Features ::                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public String submitTimeSheet(String employeeToken, int timeSheetId) {
        int employeeId = jwtToken.decodeToken(employeeToken);
        EmployeeData employeeData = employeeRepo.findById(employeeId).orElseThrow();
        TimeSheetData timeSheetData = timeSheetRepo.findByEmployeeId(employeeData.getEmployeeId());
        if(null != timeSheetData){
            timeSheetData.setStatus("submitted");
            timeSheetRepo.save(timeSheetData);
            return "Your Time Sheet with ID ::" + timeSheetId + " is Submitted..";
        }
        return "Time Sheet with ID :: " + timeSheetId + " is not FOUND..";
    }

    @Override
    public String approveTimeSheet(String managerToken, int timeSheetId) {
        int managerId = jwtToken.decodeToken(managerToken);
        ManagerData managerData = managerRepo.findById(managerId).orElseThrow();
        TimeSheetData timeSheetData = timeSheetRepo.findByManagerId(managerData.getManagerId());
        if(null != timeSheetData){
            timeSheetData.setStatus("approved");
            timeSheetRepo.save(timeSheetData);
            return "Your Time Sheet with ID ::" + timeSheetId + " is Approved..";
        }
        return "Time Sheet with ID :: " + timeSheetId + " is not FOUND..";
    }

    @Override
    public String rejectTimeSheet(String managerToken, int timeSheetId) {
        int managerId = jwtToken.decodeToken(managerToken);
        ManagerData managerData = managerRepo.findById(managerId).orElseThrow();
        TimeSheetData timeSheetData = timeSheetRepo.findByManagerId(managerData.getManagerId());
        if(null != timeSheetData){
            timeSheetData.setStatus("rejected");
            timeSheetRepo.save(timeSheetData);
            return "Your Time Sheet with ID ::" + timeSheetId + " is rejected..";
        }
        return "Time Sheet with ID :: " + timeSheetId + " is not FOUND..";
    }

    @Override
    public List<TimeSheetData> getTimeSheetByEmployeeId(int empId) {
        return timeSheetRepo.findAllByEmployeeId(empId);
    }

    @Override
    public List<TimeSheetData> getTimeSheetByManagerId(int managerId) {
        return timeSheetRepo.findAllByManagerId(managerId);
    }

    @Override
    public List<TimeSheetData> getSubmittedTimeSheet() {
        return timeSheetRepo.findAllSubmitted();
    }
}
