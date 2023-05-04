package com.bridgelabz.timesheetapplication.controller;

import com.bridgelabz.timesheetapplication.dto.ManagerDTO;
import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import com.bridgelabz.timesheetapplication.services.IManagerBusinessLogics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    /*
    Manager microservice: This microservice manages manager-related operations such as manager creation, updating manager
     details, deleting manager details, and so on. It can have the following APIs:
    POST /managers: Create a new manager
    PUT /managers/{managerId}: Update an existing manager
    DELETE /managers/{managerId}: Delete a manager
    GET /managers/{managerId}: Get a manager by ID
    GET /managers: Get all managers

    */
    @Autowired
    private IManagerBusinessLogics iManagerBusinessLogics;

    @PostMapping("/addManager")
    public ResponseEntity<ResponseDTO> addNewManager(@RequestBody ManagerDTO managerDTO){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Manager Added Successfully..",iManagerBusinessLogics.addNewManager(managerDTO)), HttpStatus.CREATED);
    }

    @PutMapping("/updateManager")
    public ResponseEntity<ResponseDTO> updateManagerDetails(@RequestBody ManagerDTO managerDTO, @RequestParam int managerId){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Manager Details updated..", iManagerBusinessLogics.updateManagerDetails(managerDTO,managerId)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteManager")
    public String deleteManagerById(@RequestParam int managerId){
        return iManagerBusinessLogics.deleteManagerById(managerId);
    }

    @GetMapping("/getManagerById")
    public ResponseEntity<ResponseDTO> getManagerDetailsById(@RequestParam int managerId){
        return new ResponseEntity<ResponseDTO>(new ResponseDTO("Manager's Details..",iManagerBusinessLogics.getManagerDetailsById(managerId)),HttpStatus.FOUND);
    }

    @GetMapping("/getAllManager")
    public List<ManagerData> getAllManagerDetails(){
        return iManagerBusinessLogics.getAllManagerDetails();
    }

}
