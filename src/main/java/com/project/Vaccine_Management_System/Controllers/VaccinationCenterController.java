package com.project.Vaccine_Management_System.Controllers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestCenter;
import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseDoctor;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Models.VaccinationCenter;
import com.project.Vaccine_Management_System.Services.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {

    @Autowired
    private VaccinationCenterService vaccinationCenterService;

    @PostMapping("addCenter")
    public String addCenter(@RequestBody RequestCenter requestCenter){
        try{
            vaccinationCenterService.addCenter(requestCenter);
            return "Vaccination center added successfully.";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/doctorList/{centerId}")
    public ResponseEntity<List<Doctor>> doctorList(@PathVariable int centerId){
        try{
            return new ResponseEntity<>(vaccinationCenterService.doctorList(centerId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<VaccinationCenter> getAll(){
        return vaccinationCenterService.getAll();
    }
}
