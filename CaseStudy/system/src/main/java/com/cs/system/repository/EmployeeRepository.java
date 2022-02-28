package com.cs.system.repository;

import org.beetl.sql.mapper.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.websocket.Session;

import com.cs.system.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

   
   /* @Query("SELECT p FROM Employee WHERE p.Firstname LIKE %?1%")
    public List <Employee> findAll(String FN); */

    //Find an employee in the DB
    @Query(value = "SELECT * FROM Employee"
    + " WHERE (Firstname = :FN OR :FN = '')"
    + " AND (Lastname = :LN OR :LN = '')"
    + " AND (Position = :PO OR :PO = '')", nativeQuery = true)
    List<Employee> searchEmployee(@Param("Firstname") String FN,  @Param("Lastname")String LN, @Param("Position")String PO);
   
  /*  @Query(value = "SELECT Firstname, MiddleName, Lastname, Birthdate FROM Employees"
    + " WHERE (Firstname = :FN)"
    + " AND (Middlename = :MN)"
    + " AND (Lastname = :LN)"
    + " AND (Birthdate = :BD)", nativeQuery = true)
    List<Employee> verifyExists(@Param("Firstname") String FN, @Param("Middlname") String MN, @Param("Lastname") String LN, @Param("Birthdate")String BD);
*/
}
