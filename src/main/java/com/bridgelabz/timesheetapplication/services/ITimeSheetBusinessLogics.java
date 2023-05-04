package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.TimeSheetDTO;
import com.bridgelabz.timesheetapplication.model.TimeSheetData;

import java.util.List;

public interface ITimeSheetBusinessLogics {

    TimeSheetData addNewTimeSheet(TimeSheetDTO timeSheetDTO);

    TimeSheetData updateTimeSheet(TimeSheetDTO timeSheetDTO, int timeSheetId);

    String deleteTimeSheetById(int timeSheetId);

    TimeSheetData getTimeSheetById(int timeSheetId);

    List<TimeSheetData> getAllTimeSheets();


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    //                              :: Time Sheet - Additional Features ::                                //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    String submitTimeSheet(String employeeToken, int timeSheetId);

    String approveTimeSheet(String managerToken, int timeSheetId);

    String rejectTimeSheet(String managerToken, int timeSheetId);

    List<TimeSheetData> getTimeSheetByEmployeeId(int empId);

    List<TimeSheetData> getTimeSheetByManagerId(int managerId);

    List<TimeSheetData> getSubmittedTimeSheet();
}
