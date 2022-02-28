package com.cs.system.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs.system.entity.Employee;
import com.cs.system.repository.EmployeeRepository;

@Service
public class EmployeeService{

	@Autowired
	private EmployeeRepository repo;

	//find an employee 
	public List<Employee>searchEmployee(String FN, String LN, String PO){
		return repo.searchEmployee(FN, LN, PO);
	}
	
	//show the list of all employees in the DB
	public List<Employee> EmployeeListAll() {
		/*if(FN != null ){
			return repo.findAll(FN);
		} */
		return repo.findAll();
		
	}
	
	// save de modify information of an employee
	public Employee saveEmployee(Employee employee) {
		return repo.save(employee);
	
	}

	public Boolean verifyExists (Employee emp){
		String FN = emp.getFirstname();
		String MN = emp.getMiddlename();
		String LN = emp.getLastname();
		String BD = emp.getBirthdate();

		return false;
	}
	
	
	
	
	//Obtain the ID of the employees
	public Employee getEmployeeId(int id) {
		return repo.findById(id).get();
	}

	//Delete an employee
	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}
}