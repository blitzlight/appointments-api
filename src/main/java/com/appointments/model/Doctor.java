package com.appointments.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor {

	@Id
	@Column
	private long id;
	
	@Column
	private String firstName;
	
	@Column
	private String middleName;
	
	@Column 
	private String lastName;
	
	@Column
	private String specialization;
}
