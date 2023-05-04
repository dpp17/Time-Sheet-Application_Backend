package com.bridgelabz.timesheetapplication.repository;

import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeData,Integer> {

    @Query(value = "select * from timesheetapp.employee_table where email = :email",nativeQuery = true)
    EmployeeData findByEmailId(String email);
}
