package com.project.Vaccine_Management_System.Controllers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.AssociateDoctor;
import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestDoctor;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public String registerDoctor(@RequestBody RequestDoctor requestDoctor){
        try {
            doctorService.addDoctor(requestDoctor);
            return "Doctor Successfully added";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PutMapping("/associateCenter")
    public String associateCenter(@RequestBody AssociateDoctor associateDoctor){
        try{
            doctorService.associateDoctor(associateDoctor);
            return "Doctor is associated with the following Vaccination Center.";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAppointments/{docId}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable int docId){
        try{
            return new ResponseEntity<>(doctorService.getAppointments(docId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAll")
    public List<Doctor> getAll(){
        return doctorService.getAll();
    }
}
