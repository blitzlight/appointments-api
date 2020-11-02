package com.appointments.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.appointments.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query(value="SELECT ap FROM Appointment ap "
			+ "WHERE (TRUNC(ap.startDateTime) >= TRUNC(:startDate) AND TRUNC(ap.startDateTime) <= TRUNC(:endDate) "
			+ "OR TRUNC(ap.endDateTime) >= TRUNC(:startDate) AND TRUNC(ap.endDateTime) <= TRUNC(:endDate) "
			+ "OR TRUNC(:startDate) >= TRUNC(ap.startDateTime) AND TRUNC(:startDate) <= TRUNC(ap.endDateTime) "
			+ "OR TRUNC(:endDate) >= TRUNC(ap.startDateTime) AND TRUNC(:endDate) <= TRUNC(ap.endDateTime)) ")
	public List<Appointment> getAllFilteredAppointments(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Query(value="SELECT ap FROM Appointment ap "
			+ "WHERE ap.id IS NOT :id "
			+ "AND (:startDate BETWEEN ap.startDateTime AND ap.endDateTime "
			+ "OR :endDate BETWEEN ap.startDateTime AND ap.endDateTime "
			+ "OR ap.startDateTime BETWEEN :startDate AND :endDate "
			+ "OR ap.endDateTime BETWEEN :startDate AND :endDate)")
	public List<Appointment> checkAppointmentConflict(@Param("id") Long id, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
	
	@Query(value="SELECT ap FROM Appointment ap "
			+ "WHERE (:startDate BETWEEN ap.startDateTime AND ap.endDateTime "
			+ "OR :endDate BETWEEN ap.startDateTime AND ap.endDateTime "
			+ "OR ap.startDateTime BETWEEN :startDate AND :endDate "
			+ "OR ap.endDateTime BETWEEN :startDate AND :endDate)")
	public List<Appointment> checkAppointmentConflictNoId(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
