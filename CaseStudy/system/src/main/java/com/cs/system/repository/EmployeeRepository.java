package com.cs.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.cs.system.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

    @Query("SELECT p FROM Employee p WHERE p.Firstname LIKE %?1%")

    public List <Employee> findAll(String FN);
    
}
