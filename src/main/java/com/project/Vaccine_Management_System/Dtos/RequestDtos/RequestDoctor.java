package com.project.Vaccine_Management_System.Dtos.RequestDtos;

import com.project.Vaccine_Management_System.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RequestDoctor {
    private String name;
    private int age;
    private Gender gender;
    private String emailId;
}
