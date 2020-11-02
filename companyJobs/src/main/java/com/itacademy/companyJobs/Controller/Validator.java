package com.itacademy.companyJobs.Controller;

import com.itacademy.companyJobs.dto.EmployeeResponseDto;

public class Validator {
	
	public static Boolean isValid (EmployeeResponseDto employee) {
		Boolean response = true;
		
		if(employee.getId() == null || employee.getJob() == null || employee.getName() == null ) {
			response = false;
		}
		
		return response;
	}

}
