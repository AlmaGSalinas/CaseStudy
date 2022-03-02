package com.cs.system.repository;

import org.beetl.sql.mapper.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.cs.system.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

    //Find an employee in the DB
    @Query(value = "SELECT * FROM Employee"
    + " WHERE (firstname = :FN OR :FN = '')"
    + " AND (lastname = :LN OR :LN = '')"
    + " AND (position = :PO OR :PO = '')", nativeQuery = true)
    List<Employee> searchEmployee(@Param("firstname") String FN,  @Param("lastname")String LN, @Param("position")String PO);
   
    Employee findEmployeeByFirstnameAndMiddlenameAndLastnameAndBirthdate(String firstname, String middlename, String lastname, String birthdate);
   /*
    @Query(value = "SELECT Firstname, MiddleName, Lastname, Birthdate FROM Employees"
    + " WHERE (Firstname = :FN)"
    + " AND (Middlename = :MN)"
    + " AND (Lastname = :LN)"
    + " AND (Birthdate = :BD)", nativeQuery = true)
    List<Employee> obtainInfo(@Param("Firstname") String FN, @Param("Middlname") String MN, @Param("Lastname") String LN, @Param("Birthdate")String BD);
    */
}
