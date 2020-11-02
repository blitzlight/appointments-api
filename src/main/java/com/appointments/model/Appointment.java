package com.appointments.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointseq")
	@SequenceGenerator(sequenceName = "appointseq", allocationSize = 1, name = "appointseq")
	@Column
	private long id;
	
	@Column
	private String doctorName;
	
	@Column
	private String patientName;
	
	@Column
	private Date startDateTime;
	
	@Column 
	private Date endDateTime;
	
	@Column
	private String comments;
	
}
