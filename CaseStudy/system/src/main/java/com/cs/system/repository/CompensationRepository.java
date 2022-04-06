package com.cs.system.repository;

import org.beetl.sql.mapper.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.cs.system.entity.Compensation;

@Repository
public interface CompensationRepository extends JpaRepository <Compensation, Integer>{
    @Query(value = "SELECT id, date, type, description, id_fk, SUM(amount) as amount, MONTHNAME(date) as monthname, YEAR(date) as yearname"
    		+ " FROM Compensation WHERE (id_fk = :Id_fk ) GROUP BY yearname, monthname ORDER BY date asc ", nativeQuery = true)
    List<Compensation> listCompensation(@Param("id_fk") int Id_fk);
   
    //Compensation findCompensationById_fkAndDate(String id_fk, String date);
    
	@Query(value="SELECT * FROM Compensation WHERE MONTHNAME(date) = :month AND id_fk = :Id_fk AND YEAR(date) = :year", nativeQuery=true)
	List<Compensation> listCompensationByMonth(@Param("id_fk") int Id_fk, @Param("month") String month, @Param("year") int year);
    
 
	@Query(value="SELECT SUM(amount) as total FROM Compensation WHERE (id_fk = :Id_fk )", nativeQuery=true)
	Integer findTotal(@Param("id_fk") int Id_fk);
	
	@Query(value = "SELECT * FROM Compensation"
		+ "WHERE id_fk  = :Id_fk ORDER BY date ASC",  nativeQuery=true)
		List<Compensation> findCompensationsById_fk(@Param("id_fk") int Id_fk);
}
