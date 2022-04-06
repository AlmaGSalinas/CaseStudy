package com.cs.system.service;

import java.util.Date;
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

    public Compensation updateCompensation(Compensation compensation) {
       return repo.save(compensation);
   }
    
    public Compensation getCompensationId (int id) {
        return repo.findById(id).get();
    }
    
    public void deleteCompensation(int id) {
        repo.deleteById(id);
    }

    public List<Compensation> findCompensationsById_fk(int Id_fk){
        return repo.findCompensationsById_fk(Id_fk);

    }
    public Boolean verifyExistsCompensation (Compensation comp){
		String type = comp.getType();
		int amount = comp.getAmount();
		String description = comp.getDescription();

		Compensation objComp = repo.findCompensationByTypeAndDescriptionAndAmount(type, description, amount);

		if(objComp!=null){
			return true;
		}else{
			return false;
		}
		
}
}    