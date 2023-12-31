package com.example.vaccinationbookingsystem.repository;

import com.example.vaccinationbookingsystem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAppointmentRepository extends JpaRepository<Appointment, Integer> {


}
