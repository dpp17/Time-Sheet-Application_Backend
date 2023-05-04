package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.bridgelabz.timesheetapplication.dto.ManagerDTO;
import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IManagerBusinessLogics {
    ManagerData addNewManager(ManagerDTO managerDTO);
    ResponseEntity<ResponseDTO> addNewManager(EmployeeDTO employeeDTO);

    ManagerData updateManagerDetails(ManagerDTO managerDTO, int managerId);

    String deleteManagerById(int managerId);

    ManagerData getManagerDetailsById(int managerId);

    List<ManagerData> getAllManagerDetails();
}
