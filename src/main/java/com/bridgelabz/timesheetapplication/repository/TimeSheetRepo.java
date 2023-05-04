package com.bridgelabz.timesheetapplication.repository;

import com.bridgelabz.timesheetapplication.model.EmployeeData;
import com.bridgelabz.timesheetapplication.model.ManagerData;
import com.bridgelabz.timesheetapplication.model.TimeSheetData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSheetRepo extends JpaRepository<TimeSheetData,Integer> {
    @Query(value = "select * from timesheetapp.manager_table where manager_id_manager_id = :managerId",nativeQuery = true)
    TimeSheetData findByManagerId(int managerId);

    @Query(value = "select * from timesheetapp.employee_table where employee_id_emp_id = :employeeId",nativeQuery = true)
    TimeSheetData findByEmployeeId(int employeeId);

    @Query(value = "select * from timesheetapp.manager_table where manager_id_manager_id = :managerId",nativeQuery = true)
    List<TimeSheetData> findAllByManagerId(int managerId);

    @Query(value = "select * from timesheetapp.employee_table where employee_id_emp_id = :employeeId",nativeQuery = true)
    List<TimeSheetData> findAllByEmployeeId(int employeeId);

    @Query(value = "select * from timesheetapp.employee_table where status = 'submitted'",nativeQuery = true)
    List<TimeSheetData> findAllSubmitted();
}
