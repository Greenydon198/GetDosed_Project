package com.project.Vaccine_Management_System.Controllers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestUser;
import com.project.Vaccine_Management_System.Dtos.RequestDtos.UpdateEmail;
import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseUser;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.User;
import com.project.Vaccine_Management_System.Services.UserService;
import com.project.Vaccine_Management_System.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody RequestUser requestUser){
        try {
            return userService.addUser(requestUser);
        }catch (Exception e){
            return "Bad request/ EmailId or MobileNo is already registered.";
        }
    }

    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmail updateEmail){
        try{
            userService.updateEmail(updateEmail);
            return "Email Successfully updated";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getUserByEmail")
    public ResponseEntity<ResponseUser> getUserByEmail(@RequestParam String emailId){
        ResponseUser responseUser = new ResponseUser();
        try{
            responseUser = userService.getUserByEmail(emailId);
            UserTransformer.setStatusMessage(responseUser,true);
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        }catch (Exception e){
            UserTransformer.setStatusMessage(responseUser,false);
            return new ResponseEntity<>(responseUser,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getVaccinationDate")
    public String getVaccDate(@RequestParam int userId){
        try{
            return userService.getVaccDate(userId).toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAppointments/{userId}")
    public ResponseEntity<List<Appointment>> getAppointments(@PathVariable int userId){
        try{
            return new ResponseEntity<>(userService.getAppointments(userId), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
}
