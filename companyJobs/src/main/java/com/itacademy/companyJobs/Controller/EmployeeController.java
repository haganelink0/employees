package com.itacademy.companyJobs.Controller;


import java.util.Optional;

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

import com.itacademy.companyJobs.Controller.Exceptions.InvalidJsonException;
import com.itacademy.companyJobs.Service.EmployeeService;
import com.itacademy.companyJobs.dto.EmployeeResponseDto;
import com.itacademy.companyJobs.dto.JobType;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080", 
			 methods= {RequestMethod.GET, RequestMethod.POST})
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	HttpStatus hs = HttpStatus.NOT_FOUND; //base HttpStatus to use in multiple methods
	Optional<EmployeeResponseDto> worker = null; //base employee to use in multiple methods
	Iterable<EmployeeResponseDto> workerList = null;  //base list to use
	
	@GetMapping("/company") //sends a list with all workers
	public ResponseEntity<Iterable<EmployeeResponseDto>> showJobs () throws Exception {
		
		try {
			workerList  = service.findAll();
			hs = HttpStatus.OK;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(workerList, hs);
	}
	
	@GetMapping("/users/{id}") //sends a single worker
	public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable int id) throws InvalidJsonException{

		worker = service.findbyId(id);
		if (worker.isEmpty()) {
			throw new InvalidJsonException("Wrong id or user not found");
		} else {
			return new ResponseEntity<>(worker.get(), hs);
		}

	}
	
	@GetMapping("/users/jobs/{jobtype}") //sends a list of workers by job
	public ResponseEntity<Iterable<EmployeeResponseDto>> showByJob(@PathVariable JobType jobtype){
		
		return new ResponseEntity<>(service.findByJob(jobtype), HttpStatus.OK);
	}

    @PostMapping(path="/employee", 
    			 consumes="application/json") //inserts a new worker on de database
	public ResponseEntity<EmployeeResponseDto> insertEmployee (@RequestBody EmployeeResponseDto employee) throws InvalidJsonException {
    	
    	if (Boolean.TRUE.equals(Validator.isValid(employee))) {
    		service.insertEmployee(employee);
    		
    	} else {
    		throw new InvalidJsonException("Invalid Json");
    	}
    	
    	return new ResponseEntity<>(employee, hs);
	}
    
    @PutMapping(path="/users/{id}",
    			produces="application/json",
    			consumes="application/json") //edits the worker selected
    public void editEmployee(@RequestBody EmployeeResponseDto employee, @PathVariable int id) throws InvalidJsonException {

    		worker = service.findbyId(id);
    		if (worker.isEmpty()) {
    			throw new InvalidJsonException("User not found");
    		}
        	service.deleteEmployee(worker.get());
        	if(Boolean.FALSE.equals(Validator.isValid(employee))) {
        		throw new InvalidJsonException("Invalid Json");
        	}
        	service.insertEmployee(employee);

    }

	@DeleteMapping("/users/{id}") //deletes the worker selected
	public void deleteEmployee(@PathVariable int id) throws InvalidJsonException {
		
		worker = service.findbyId(id);
		
		if (worker.isPresent()) {
			service.deleteEmployee(worker.get());
    	} else {
    		throw new InvalidJsonException("User Not Found");
    	}
	
	}

}
