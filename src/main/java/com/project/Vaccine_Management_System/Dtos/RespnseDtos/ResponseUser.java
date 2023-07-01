package com.project.Vaccine_Management_System.Dtos.RespnseDtos;

import com.project.Vaccine_Management_System.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseUser {
    private String name;
    private int age;
    private Gender gender;
    private String emailId;
    private String mobileNo;
    private String statusCode;
    private String statusMessage;
}
