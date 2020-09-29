package com.itacademy.companyJobs.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Employees")
public class EmployeeResponseDto extends ResponseDto {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy= GenerationType.AUTO, generator="native")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="job")
	private JobType job;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobType getJob() {
		return job;
	}

	public void setJob(JobType job) {
		this.job = job;
	}
	
	public EmployeeResponseDto(Integer id) {
		this.id = id;
	}

	public EmployeeResponseDto(Integer id, String name, JobType job) {
		super();
		this.id = id;
		this.name = name;
		this.job = job;
	}

	public EmployeeResponseDto() {
		super();
	}
	
	

}