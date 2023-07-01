package com.project.Vaccine_Management_System.Controllers;

import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseUser;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Dose;
import com.project.Vaccine_Management_System.Services.DoseService;
import com.project.Vaccine_Management_System.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;

    @PostMapping("giveDose")
    public String giveDose(@RequestParam String doseId,@RequestParam int userId){
        try{
            return doseService.giveDose(doseId,userId);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getUserByDose")
    public ResponseUser getUserByDose(@PathVariable int doseId){
        ResponseUser responseUser = new ResponseUser();
        try{
            responseUser = doseService.getUserByDose(doseId);
            UserTransformer.setStatusMessage(responseUser,true);
        }catch (Exception e){
            UserTransformer.setStatusMessage(responseUser,false);
        }
        return responseUser;
    }
    @GetMapping("/getAll")
    public List<Dose> getAll(){
        return doseService.getAll();
    }
}
