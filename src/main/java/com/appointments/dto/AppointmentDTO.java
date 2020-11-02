package com.appointments.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

	private Long id;
	private String doctorName;
	private String patientName;
	private Date startDateTime;
	private Date endDateTime;
	private String comments;
}
