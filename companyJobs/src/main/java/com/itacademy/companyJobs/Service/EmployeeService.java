package com.itacademy.companyJobs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itacademy.companyJobs.Classes.Employee;
import com.itacademy.companyJobs.Repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;
	
	public Iterable<Employee> findAll(){
		return repository.findAll();
	}
	
	public void insertEmployee(Employee employee) {
		repository.save(employee);
	}
	
	public void deleteEmployee(Employee employee) {
		repository.delete(employee);
	}

}
