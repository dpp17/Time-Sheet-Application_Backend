package com.bridgelabz.timesheetapplication.services;

import com.bridgelabz.timesheetapplication.dto.EmployeeDTO;
import com.bridgelabz.timesheetapplication.dto.ManagerDTO;
import com.bridgelabz.timesheetapplication.dto.ResponseDTO;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import com.bridgelabz.timesheetapplication.repository.ManagerRepo;
import com.bridgelabz.timesheetapplication.utility.EmailServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerBusinessLogics implements IManagerBusinessLogics{

    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private EmailServices emailService;


    @Override
    public ManagerData addNewManager(ManagerDTO managerDTO) {
        ManagerData isManagerPresent = managerRepo.findByEmailId(managerDTO.getEmail());
        if(isManagerPresent == null){
            ManagerData managerData = new ManagerData(managerDTO);
            managerRepo.save(managerData);
        emailService.sendEmail(managerDTO.getEmail(),"Your Are Added As Manager Successfully",
                "Hey... "+ managerDTO.getFirstName() +" " + managerDTO.getLastName() +"\n\n Your Password :: "+ managerDTO.getPassword() +
                        "\n\n Regards,\n Time Sheet Application.");
            return managerData;
        }
        return null;
    }

    @Override
    public ResponseEntity<ResponseDTO> addNewManager(EmployeeDTO employeeDTO) {
        ManagerDTO managerDTO = new ManagerDTO(employeeDTO);
        ManagerData isManagerPresent = managerRepo.findByEmailId(managerDTO.getEmail());
        if(isManagerPresent == null){
        ManagerData managerData = new ManagerData(managerDTO);
        managerRepo.save(managerData);
        emailService.sendEmail(managerDTO.getEmail(),"Your Are Added As Manager Successfully",
                "Hey... "+ managerDTO.getFirstName() +" " + managerDTO.getLastName() +"\n\n Your Password :: "+ managerDTO.getPassword() +
                        "\n\n Regards,\n Time Sheet Application.");
        return new ResponseEntity<>(new ResponseDTO("Manager Added Successfully..",managerData), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseDTO("Manager Already exist..",isManagerPresent), HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ManagerData updateManagerDetails(ManagerDTO managerDTO, int managerId) {
        ManagerData managerData = managerRepo.findById(managerId).orElseThrow();
        managerData.updateManagerData(managerDTO);
        managerRepo.save(managerData);
        return managerData;
    }

    @Override
    public String deleteManagerById(int managerId) {
        ManagerData managerData = managerRepo.findById(managerId).orElseThrow();
        managerRepo.deleteById(managerId);
        return "Manager Details with ID : "+ managerId +", Deleted Successfully..";
    }

    @Override
    public ManagerData getManagerDetailsById(int managerId) {
        return managerRepo.findById(managerId).orElseThrow();
    }

    @Override
    public List<ManagerData> getAllManagerDetails() {
        return managerRepo.findAll();
    }
}
