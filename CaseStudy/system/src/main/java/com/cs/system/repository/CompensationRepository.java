package com.cs.system.repository;

import org.springframework.stereotype.Repository;
import com.cs.system.entity.Compensation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CompensationRepository extends JpaRepository <Compensation, Integer>{
    
}
