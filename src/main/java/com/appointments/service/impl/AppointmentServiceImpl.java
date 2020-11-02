package com.appointments.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appointments.dto.AppointmentDTO;
import com.appointments.mapper.ObjectMapperUtils;
import com.appointments.model.Appointment;
import com.appointments.repository.AppointmentRepository;
import com.appointments.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	AppointmentRepository appointmentRepository;
	
	@Override
	public List<Appointment> getAllAppointments(Date startDate, Date endDate) {
		List<Appointment> appointmentList = new ArrayList<>();
		if (null != startDate && null != endDate) {
			appointmentList = this.appointmentRepository.getAllFilteredAppointments(startDate, endDate);
		} else {
			appointmentList = this.appointmentRepository.findAll();
		}
		return appointmentList;
	}
	
	@Override
	@Transactional
	public void saveAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointmentEntity = ObjectMapperUtils.map(appointmentDTO, Appointment.class);
		this.appointmentRepository.saveAndFlush(appointmentEntity);
	}

	@Override
	@Transactional
	public void deleteAppointment(Long appointmentId) {
		this.appointmentRepository.deleteById(appointmentId);
	}

	@Override
	public boolean checkAppointmentConflict(Date startDate, Date endDate) {
		return null != startDate && null != endDate 
				&& this.appointmentRepository.checkAppointmentConflictNoId(startDate, endDate).size() > 0;
		
	}
	
	@Override
	public boolean checkAppointmentConflictWithId(Long id, Date startDate, Date endDate) {
		return this.appointmentRepository.checkAppointmentConflict(id, startDate, endDate).size() > 0;
	}

}
