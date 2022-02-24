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

	
	//Obtiene la lista de los empleados
	public List<Employee> EmployeeListAll(String keyword) {
		if(keyword != null){
			return repo.findAll(keyword);
		}
		return repo.findAll();
		
	}
	
	// guarda los cambios de la modificacion de un empleado
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
	
	
	//Obtiene el ID de los empleados
	public Employee getEmployeeId(int id) {
		return repo.findById(id).get();
	}

	//Elimina empleados de la BD

	public void deleteEmployee(int id) {
		repo.deleteById(id);
	}

	//evalua la fecha de nacimiento 
	public void valueBirhtDate(String BD){
	
}

}