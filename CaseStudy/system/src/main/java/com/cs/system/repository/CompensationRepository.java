package com.cs.system.repository;

import org.beetl.sql.mapper.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.cs.system.entity.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository <Compensation, Integer>{
 /*   @Query(value = "SELECT * FROM Compensation"
    + " WHERE (id_fk = :Id_fk )"
    + " AND (date = :Date )" , nativeQuery = true)
    List<Compensation> listCompensation(@Param("id_fk") String Id_fk,  @Param("date")String Date);
   
    Compensation findCompensationById_fkAndDate(String id_fk, String date);
   */
}
