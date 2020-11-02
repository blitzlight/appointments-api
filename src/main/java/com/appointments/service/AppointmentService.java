package com.appointments.service;

import java.util.Date;
import java.util.List;

import com.appointments.dto.AppointmentDTO;
import com.appointments.model.Appointment;

public interface AppointmentService {

	public List<Appointment> getAllAppointments(Date startDate, Date endDate);
	public void saveAppointment(AppointmentDTO appointmentDTO);
	public void deleteAppointment(Long appointmentId);
	public boolean checkAppointmentConflict(Date startDateTime, Date endDateTime);
	public boolean checkAppointmentConflictWithId(Long id, Date startDateTime, Date endDateTime);
}
