package com.bridgelabz.timesheetapplication.repository;

import com.bridgelabz.timesheetapplication.model.ManagerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepo extends JpaRepository<ManagerData,Integer> {

    @Query(value = "select * from timesheetapp.manager_table where email = :email",nativeQuery = true)
    ManagerData findByEmailId(String email);
}
