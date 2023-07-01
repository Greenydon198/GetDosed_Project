package com.project.Vaccine_Management_System.Dtos.RespnseDtos;

import com.project.Vaccine_Management_System.Enums.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDoctor {
    private String name;
    private int age;
    private Gender gender;
    private String emailId;
}
