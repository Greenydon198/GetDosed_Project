package com.project.Vaccine_Management_System.Repositories;

import com.project.Vaccine_Management_System.Models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
