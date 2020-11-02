//package com.appointments.mapper;
//
//import org.springframework.stereotype.Component;
//
//import com.appointments.dto.AppointmentDTO;
//import com.appointments.model.Appointment;
//
//@Component
//public class AppointmentMapper {
//	
//	public Appointment appointmentDtoToEntity(AppointmentDTO appointmentDTO) {
//		Appointment appointmentEntity = new Appointment();
//		if (null != appointmentDTO.getId()) {
//			appointmentEntity.setId(appointmentDTO.getId());
//		}
//		appointmentEntity.setDoctorName(appointmentDTO.getDoctorName());
//		appointmentEntity.setPatientName(appointmentDTO.getPatientName());
//		appointmentEntity.setStartDateTime(appointmentDTO.getStartDateTime());
//		appointmentEntity.setEndDateTime(appointmentDTO.getEndDateTime());
//		appointmentEntity.setComments(appointmentDTO.getComments());
//		return appointmentEntity;
//	}
//	
//	
//
//}
