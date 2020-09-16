package com.itacademy.companyJobs.Repository;

import org.springframework.data.repository.CrudRepository;

import com.itacademy.companyJobs.Classes.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,String> {

}
