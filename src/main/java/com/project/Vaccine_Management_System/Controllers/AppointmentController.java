package com.project.Vaccine_Management_System.Controllers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestAppointment;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public String bookAppointment(@RequestBody RequestAppointment requestAppointment){
        try{
            appointmentService.bookAppointment(requestAppointment);
            return "Appointment booked successfully.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAll")
    public List<Appointment> getAll(){
        return appointmentService.getAll();
    }
}
