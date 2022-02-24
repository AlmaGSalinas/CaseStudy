package com.cs.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.cs.system.service.EmployeeService;
import com.cs.system.entity.*;

import java.util.List;
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@RequestMapping ({"/","/menu"})
	public String Menu(){
		return "menu";
	}
	
	@RequestMapping ("/EmployeeControl")
	public String EmployeeListing(Model mod,  @Param("keyword") String keyword) {
		List<Employee> EmployeeList = service.EmployeeListAll(keyword);
	
		mod.addAttribute("EmployeeList", EmployeeList);
		mod.addAttribute("keyword", keyword);
		return "show_employees"; // return show_employees.htlm
	}

	@RequestMapping("/new/employee")
	public String ShowEmployeeForm(Model mod) {
		Employee EmpObj = new Employee();
		mod.addAttribute("employee", EmpObj);
		return "create_employee";
	}

	@RequestMapping(value= "/SaveEmployee", method= RequestMethod.POST)
	public String SaveEmployee(@ModelAttribute("employee") Employee emp) {
		
		service.saveEmployee(emp);

		return "redirect:/";
	}

	@RequestMapping("/ModifyEmployee/{id}")
	public ModelAndView ShowEditFormEmployee(@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("modify_employee");
		Employee emp = service.getEmployeeId(id);
		modelAV.addObject("employee", emp);
		return modelAV;
	}

	@RequestMapping ("/deleteEmployee/{id}")
	public String DeleteEmployee(@PathVariable(name = "id") Integer id){
		service.deleteEmployee(id);
		return "redirect:/";
	}

}

