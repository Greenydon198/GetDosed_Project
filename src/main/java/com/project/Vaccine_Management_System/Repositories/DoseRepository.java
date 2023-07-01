package com.project.Vaccine_Management_System.Repositories;

import com.project.Vaccine_Management_System.Models.Dose;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoseRepository extends JpaRepository<Dose,Integer> {

    Dose findByDoseId(String doseId);
}
