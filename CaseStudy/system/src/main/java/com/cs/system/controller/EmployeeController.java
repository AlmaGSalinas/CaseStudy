package com.cs.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cs.system.service.CompensationService;
import com.cs.system.service.EmployeeService;
import com.cs.system.entity.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService service;
    private CompensationService ComService; 

	//Employees 
	@RequestMapping ({"/","/menu"})
	public String Menu(){
		return "menu";
	}
	
	@RequestMapping ("/EmployeeControl")
	public String searchEmp(Model mod, String FN, String LN, String PO) {
		try{
			List<Employee> EmployeeList = service.searchEmployee(FN, LN, PO);	
				mod.addAttribute("EmployeeList", EmployeeList);
		}catch(Exception ex){
			System.out.println(ex);
		} 
		return "show_employees"; // return show_employees.htlm
	}

	@RequestMapping("/new/employee")
	public String ShowEmployeeForm(Model mod) {
		Employee EmpObj = new Employee();
		mod.addAttribute("employee", EmpObj);
		return "create_employee";
	}

	@RequestMapping(value= "/SaveEmployee", method= RequestMethod.POST)
	public String SaveEmployee(@ModelAttribute("employee") @Validated Employee emp, RedirectAttributes rediAtt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String BD = emp.getBirthdate();
		java.util.Date obj = new java.util.Date();
		LocalDate date = LocalDate.parse(BD, formatter);
		
		 if (date.isBefore(LocalDate.now())) {
			if(service.verifyExists(emp)==false){
				service.saveEmployee(emp);
				//the employee isn't in the DB
				return "redirect:/menu?success";
			}else{
				//the employee exists in the BD
				return "redirect:/menu?errorEmployeeExists";
			}
			
		 }else{
			 //Birthdate invalid
		return "redirect:/menu?errorBirthdate";	
		}
	}

	@RequestMapping(value= "/ModifyEmployee/{id}")
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

	//Compensations
	@RequestMapping ("/MenuCompensation/{id}")
	public ModelAndView MenuCompensation(@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("menu_compensation");
		Employee emp = service.getEmployeeId(id);
		modelAV.addObject("employee", emp);
		return modelAV;
	}

	@RequestMapping ("/ModifyCompensation/{id}")
	public ModelAndView ModifyCompensationForm(@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("modify_compensation");
		Employee emp = service.getEmployeeId(id);
		modelAV.addObject("employee", emp);
		return modelAV;
	}

	@RequestMapping ("/AddCompensation/{id}")
	public ModelAndView AddCompensationForm(@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("add_compensation");
		Employee emp = service.getEmployeeId(id);
		modelAV.addObject("employee", emp);
		return modelAV;
	}



    @RequestMapping ("/CompensationHistory/{id}")
    public ModelAndView vieCompensation (@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("view_compensation");
		Employee emp = service.getEmployeeId(id);
		modelAV.addObject("employee", emp);
		return modelAV;
	}
	
	@RequestMapping ("/deleteCompensation/{id}")
	    public String DeleteCompensation(@PathVariable(name = "id") Integer id){
		ComService.deleteCompensation(id);
		return "redirect:/";
		}

	@RequestMapping(value= "/SaveCompensation", method= RequestMethod.POST)
		public String saveCompensation(@ModelAttribute("compensation") @Validated Compensation comp, RedirectAttributes rediAtt) {
		ComService.saveCompensation(comp);
		return "redirect:/" ;
		}
}
