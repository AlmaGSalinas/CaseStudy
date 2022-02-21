package com.cs.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.system.entity.Employee;
import com.cs.system.repository.EmployeeRepository;

@Service
public class EmployeeService{

	@Autowired
	private EmployeeRepository repo;

	
	//Obtiene la lista de los empleados
	public List<Employee> EmployeeListAll(String FN) {
		if(FN != null){
			return repo.findAll(FN);
		}
		return repo.findAll();
	}
	
	// guarda los cambios de la modificacion de un empleado
	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	}
	
	//Obtiene el ID de los empleados
	public Employee getEmployeeId(int id) {
		return repo.findById(id).get();
	}

	//Elimina empleados de la BD

	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}

}
