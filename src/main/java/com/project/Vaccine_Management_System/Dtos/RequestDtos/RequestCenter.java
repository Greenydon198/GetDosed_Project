package com.project.Vaccine_Management_System.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class RequestCenter {
    private String name;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private String address;
    private int doseCapacity;
}
