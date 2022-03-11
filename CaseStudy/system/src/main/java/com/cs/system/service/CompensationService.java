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

public List<Compensation> CompensationList() {
    return repo.findAll();
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
