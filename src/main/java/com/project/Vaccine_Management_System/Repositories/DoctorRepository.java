package com.project.Vaccine_Management_System.Repositories;

import com.project.Vaccine_Management_System.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    Doctor findByEmailId(String emailId);
}
