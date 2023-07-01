package com.project.Vaccine_Management_System.Transformers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestDoctor;
import com.project.Vaccine_Management_System.Models.Doctor;

public class DoctorTransformer {

    public static Doctor convertDtoToEntity(RequestDoctor requestDoctor){
        Doctor doctor = Doctor.builder().name(requestDoctor.getName())
                .age(requestDoctor.getAge())
                .gender(requestDoctor.getGender())
                .emailId(requestDoctor.getEmailId()).build();

        return doctor;
    }
}
