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


	/*	if(FN!=null || MN!=null && LN!=null && BD!=null ){

			if(FN.equals(employee.getFirstname())&&(MN.equals(employee.getMiddlename())&& (LN.equals(employee.getLastname())) && (BD.equals(employee.getBirthdate())))){

			}*/
		/*int value;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 String date = BD;
		 LocalDate localDate = LocalDate.parse(date, formatter);

		 if(BD!=null){
		 if (localDate.isBefore(LocalDate.now())) {
			value=1;
			return repo.save(employee);
		 }
		 else{
			value=0;
		 }*/
	
	
	//Obtain the ID of the employees
	public Employee getEmployeeId(int id) {
		return repo.findById(id).get();
	}

	//Delete an employee
	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}
}