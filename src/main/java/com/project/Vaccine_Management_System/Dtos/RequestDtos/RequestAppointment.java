package com.project.Vaccine_Management_System.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class RequestAppointment {
    private int userId;
    private int doctorId;
    private Date appointmentDate;
    private LocalTime appointmentTime;
}
