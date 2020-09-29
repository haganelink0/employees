package com.itacademy.companyJobs.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itacademy.companyJobs.Service.EmployeeService;
import com.itacademy.companyJobs.classes.EmployeeResponseDto;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8081", 
			 methods= {RequestMethod.GET, RequestMethod.POST})
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/company")
	public ResponseEntity<Iterable<EmployeeResponseDto>> showJobs () {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable int id) throws Exception{

		return new ResponseEntity<>(service.findbyId(id), HttpStatus.OK);
	}
	
	@GetMapping("/users/{jobtype}")
	public ResponseEntity<Iterable<EmployeeResponseDto>> showByJob(@PathVariable String job){
		return new ResponseEntity<>(service.findByJob(job), HttpStatus.OK);
	}

    @PostMapping(path="/employee", 
    			 consumes="application/json")
	public void insertEmployee (@RequestBody EmployeeResponseDto employee) {
		
		service.insertEmployee(employee);
	}
    
    @PutMapping(path="/users/{id}",
    			produces="application/json",
    			consumes="application/json")
    public void editEmployee(@RequestBody EmployeeResponseDto employee, @PathVariable int id) {
    	EmployeeResponseDto oldEmployee = service.findbyId(id);
    	service.deleteEmployee(oldEmployee);
    	service.insertEmployee(employee);
    }

	@DeleteMapping("/users/{id}")
	public void deleteEmployee(@PathVariable int id) {
		
		EmployeeResponseDto employee = service.findbyId(id);
		
		service.deleteEmployee(employee);

	}

}
