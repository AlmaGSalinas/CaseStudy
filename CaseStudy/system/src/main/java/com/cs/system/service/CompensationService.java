package com.cs.system.service;

import java.util.List;

import com.cs.system.entity.Compensation;
import com.cs.system.repository.CompensationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationService {
    
    @Autowired
    private CompensationRepository repo;
    
    public List<Compensation> CompensationList(int Id_fk) { //encontrar compensaciones de acuerdo a X empleado
        return repo.listCompensation(Id_fk);
    }
    
    public List<Compensation> CompensationListByMonth(int Id_fk, String month, int year){
        return repo.listCompensationByMonth(Id_fk, month, year);
    }
    
    public int findTotal(int id) {
        return repo.findTotal(id);
    }
    
    public void saveCompensation(Compensation compensation) {
         repo.save(compensation);
    }
    
    public Compensation getCompensationId (int id) {
        return repo.findById(id).get();
    }
    
    public void deleteCompensation(int id) {
        repo.deleteById(id);
    }
}    