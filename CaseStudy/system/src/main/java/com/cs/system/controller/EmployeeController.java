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
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
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

	@Autowired
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

    @RequestMapping ("/CompensationHistory/employee/{id}")
    public ModelAndView viewCompensation (@PathVariable(name = "id") Integer id) {
		ModelAndView modelAV = new ModelAndView("view_compensation");
		Employee emp = service.getEmployeeId(id);
		List<Compensation> compensations = ComService.CompensationList(id);
		int total = ComService.findTotal(id);
		modelAV.addObject("employee", emp);
		modelAV.addObject("compensations", compensations);
		modelAV.addObject("total", total);
		return modelAV;
	}

	  //compensaciones por mes
	  @RequestMapping(value="/CompensationHistory/employee/{id}/{month}/{year}", method= RequestMethod.GET)
	  public ModelAndView viewCompensationByMonth(@PathVariable(name="id") int id, @PathVariable(name="month") String month, @PathVariable(name="year") int year) {
		  ModelAndView modelAV = new ModelAndView("view_monthly_compensations");
		  List<Compensation> compensations = ComService.CompensationListByMonth(id,month,year);
		  Employee emp = service.getEmployeeId(id);
  
		  modelAV.addObject("compensations", compensations);
		  modelAV.addObject("month", month);
		  modelAV.addObject("year", year);
		  modelAV.addObject("employee", emp);
		  return modelAV;
	  }

	@RequestMapping ("/deleteCompensation/{id}")
	    public String DeleteCompensation(@PathVariable(name = "id") Integer id){
		ComService.deleteCompensation(id);
		return "redirect:/menu?deleteCompensation";
		}


	@RequestMapping ("/ModifyCompensation/employee/{id}/Compensation/{idCom}")
		public ModelAndView ModifyCompensationForm( @PathVariable(name = "id") int id, @PathVariable (name = "idCom") int idCom) {
			ModelAndView modelAV = new ModelAndView("modify_compensation");
			modelAV.addObject("employee", service.getEmployeeId(id));
			modelAV.addObject("compensation", ComService.getCompensationId(idCom));
			return modelAV;
		
		}
	
	@RequestMapping ("/AddCompensation/{id}")
		public ModelAndView ShowAddCompensationForm(@PathVariable(name = "id") Integer id) {
			ModelAndView modelAV = new ModelAndView("add_compensation");
			Employee emp = service.getEmployeeId(id);
			modelAV.addObject("employee", emp);
			Compensation comp = new Compensation();
			modelAV.addObject("compensation", comp);
			return modelAV;
		}
	
	@RequestMapping("/SaveCompensation/{id}")
		public String SaveCompensation(@ModelAttribute("compensation") @PathVariable(name = "id") Integer id, @Validated Compensation comp) {
			Employee emp = service.getEmployeeId(id);
			comp.setId_fk(emp);
			String CompensationType =  comp.getType();	
			int amount = comp.getAmount();

			if(ComService.verifyExistsCompensation(comp)==false){
			switch(CompensationType){

				 case "Adjustment":
				 if(amount<0 || amount>0){
					ComService.saveCompensation(comp);
					return "redirect:/MenuCompensation/{id}?success";
				 }else{
					return "redirect:/MenuCompensation/{id}?errorAdjustment";
				}

				case "Allowance":
				 if(amount>0){
					ComService.saveCompensation(comp);
					return "redirect:/MenuCompensation/{id}?success";
				 }else{
					return "redirect:/MenuCompensation/{id}?errorAllowance";
				}

				case "Bonus":
				 if(amount>0){
					ComService.saveCompensation(comp);
					return "redirect:/MenuCompensation/{id}?success";
				 }else{
					return "redirect:/MenuCompensation/{id}?errorBonus";
				}

				case "Comission":
				 if(amount>0){
					ComService.saveCompensation(comp);
					return "redirect:/MenuCompensation/{id}?success";
				 }else{
					return "redirect:/MenuCompensation/{id}?errorComission";
				}

				case "Salary":
				 if(amount>=0 || amount<=0){
					ComService.saveCompensation(comp);
					return "redirect:/MenuCompensation/{id}?success";
				 }else{
					return "redirect:/MenuCompensation/{id}?errorSalary";
				}
				
		}
		return "redirect:/MenuCompensation/{id}";

	}else{
		return "redirect:/MenuCompensation/{id}?errorExists";
	}
} 

    //MODIFICA LA COMPENSACI??N
	@RequestMapping("/SaveModification/employee/{id}/compensation/{idCom}")
	public String saveModifyCompensation(@ModelAttribute("compensation") @PathVariable(name = "id") Integer id,  @PathVariable(name = "idCom") Integer idCom, @Validated Compensation comp, RedirectAttributes rediAtt, Model mod) {
	Employee emp = service.getEmployeeId(id);

	Compensation Existingcompensation = ComService.getCompensationId(idCom);
	Existingcompensation.setAmount(comp.getAmount());
	Existingcompensation.setDescription(comp.getDescription());
	
	mod.addAttribute("compensation", Existingcompensation);
	comp.setId_fk(emp);
	String CompensationType =  comp.getType();	
	int amount = comp.getAmount();
	if(ComService.verifyExistsCompensation(comp)==false){

		switch(CompensationType){

			case "Adjustment":
			if(amount<0 || amount>0){
			
				ComService.updateCompensation(Existingcompensation);

			   return "redirect:/MenuCompensation/{id}?success";
			}else{
			   return "redirect:/MenuCompensation/{id}?errorAdjustment";
		   }
   
		   case "Allowance":
			if(amount>0){
			
			  ComService.updateCompensation(Existingcompensation);
			   return "redirect:/MenuCompensation/{id}?success";
			}else{
			   return "redirect:/MenuCompensation/{id}?errorAllowance";
		   }
   
		   case "Bonus":
			if(amount>0){
			
				ComService.updateCompensation(Existingcompensation);
			   return "redirect:/MenuCompensation/{id}?success";
			}else{
			   return "redirect:/MenuCompensation/{id}?errorBonus";
		   }
   
		   case "Comission":
			if(amount>0){
		
				ComService.updateCompensation(Existingcompensation);
			   return "redirect:/MenuCompensation/{id}?success";
			}else{
			   return "redirect:/MenuCompensation/{id}?errorComission";
		   }
   
		   case "Salary":
			if(amount>=0 || amount<=0){
			
				ComService.updateCompensation(Existingcompensation);
			   return "redirect:/MenuCompensation/{id}?success";
			}else{
			   return "redirect:/MenuCompensation/{id}?errorSalary";
		   }
		   
   }			
	}else{
		return "redirect:/MenuCompensation/{id}?errorExists";
	}
	 

return "redirect:/MenuCompensation/{id}";

}
}

