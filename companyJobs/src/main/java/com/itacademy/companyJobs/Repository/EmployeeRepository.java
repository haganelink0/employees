package com.itacademy.companyJobs.Repository;

import org.springframework.data.repository.CrudRepository;

import com.itacademy.companyJobs.dto.EmployeeResponseDto;
import com.itacademy.companyJobs.dto.JobType;


public interface EmployeeRepository extends CrudRepository<EmployeeResponseDto,Integer> {

	Iterable<EmployeeResponseDto> findByJob(JobType job);

}
