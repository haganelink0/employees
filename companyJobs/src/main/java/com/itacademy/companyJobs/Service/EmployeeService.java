package com.itacademy.companyJobs.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.companyJobs.Repository.EmployeeRepository;
import com.itacademy.companyJobs.classes.EmployeeResponseDto;
import com.itacademy.companyJobs.classes.JobType;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public Iterable<EmployeeResponseDto> findAll(){
		return repository.findAll();
	}
	
	public void insertEmployee(EmployeeResponseDto employee) {
		repository.save(employee);
	}
	
	public void deleteEmployee(EmployeeResponseDto employee) {
		repository.delete(employee);
	}

	public EmployeeResponseDto findbyId(int id) {
		Optional<EmployeeResponseDto> employee = repository.findById(id);
		
		if (employee.isPresent()) {
			return employee.get();
		}
		return null;
	}
	
	public Iterable<EmployeeResponseDto> findByJob(JobType job){
		
		return repository.findByJob(job);
	}
	

}
