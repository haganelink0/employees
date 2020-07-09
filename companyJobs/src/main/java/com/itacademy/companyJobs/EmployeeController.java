package com.itacademy.companyJobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@RequestMapping("/company")
	public String showJobs (Model model) {
		
		model.addAttribute("office", service.findAll());
		return "company";
	}

      @RequestMapping("/newEmployee")
	public String newEmployee() {
		
		
		return "newEmployee";
	}

      @RequestMapping("/insertEmployee")
	public String insertEmployee (Employee employee, Model model) {
		
		service.insertEmployee(employee);
		model.addAttribute("office",service.findAll());
		
		return "company";
	}

	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("id") Integer id, Model model) {
		
		Employee employee = new Employee(id);
		
		service.deleteEmployee(employee);
		model.addAttribute("office",service.findAll());
		
		return "redirect:company";
	}

}
