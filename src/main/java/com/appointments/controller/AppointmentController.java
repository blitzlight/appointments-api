package com.appointments.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appointments.dto.AppointmentDTO;
import com.appointments.model.Appointment;
import com.appointments.service.AppointmentService;

@RestController
//@RequestMapping("appointment")
//@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping("/")
	public String getHello() {
		return "hello";
	}
	
	@GetMapping("/getAllAppointments")
	public List<Appointment> getAllAppointments(@RequestParam String startDateTime, @RequestParam String endDateTime) {
		List<Appointment> appointmentList = new ArrayList<>();
		try {
			if (null == startDateTime || "null".equals(startDateTime.toString()) 
					|| null == endDateTime || "null".equals(endDateTime.toString())) {
				appointmentList = this.appointmentService.getAllAppointments(null, null);
			} else {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				appointmentList = this.appointmentService.getAllAppointments(
									dateFormat.parse(startDateTime), dateFormat.parse(endDateTime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}			
		return appointmentList;
	}
	
	@PostMapping("/saveAppointment")
	public void saveAppointment(@RequestBody AppointmentDTO appointmentDTO) {
		this.appointmentService.saveAppointment(appointmentDTO);
	}
	
	@DeleteMapping("/deleteAppointment")
	public void deleteAppointment(@RequestParam Long appointmentId) {
		if (null != appointmentId) {
			this.appointmentService.deleteAppointment(appointmentId);			
		}
	}
	
	@GetMapping("/checkAppointmentConflictWithId")
	public boolean checkAppointmentConflict(@RequestParam String id,
			@RequestParam String startDateTime, @RequestParam String endDateTime) {
		boolean hasConflict = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		if (null != startDateTime && !"null".equals(startDateTime.toString()) 
				&& null != startDateTime && !"null".equals(endDateTime.toString())) {
			try {
				Date initialDateTime = dateFormat.parse(startDateTime);
				Date finalDateTime = dateFormat.parse(endDateTime);
				hasConflict = null == id || "null".equals(id.toString())
					? this.appointmentService.checkAppointmentConflict(initialDateTime, finalDateTime)
					: this.appointmentService.checkAppointmentConflictWithId(
												Long.parseLong(id), initialDateTime, finalDateTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return hasConflict;
	}
	
}
