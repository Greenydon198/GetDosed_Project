package com.project.Vaccine_Management_System.Services;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestUser;
import com.project.Vaccine_Management_System.Dtos.RequestDtos.UpdateEmail;
import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseUser;
import com.project.Vaccine_Management_System.Exceptions.DoctorNotFound;
import com.project.Vaccine_Management_System.Exceptions.DoseNotFound;
import com.project.Vaccine_Management_System.Exceptions.EmailIdException;
import com.project.Vaccine_Management_System.Exceptions.UserNotFound;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Models.Dose;
import com.project.Vaccine_Management_System.Models.User;
import com.project.Vaccine_Management_System.Repositories.UserRepository;
import com.project.Vaccine_Management_System.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public String addUser(RequestUser requestUser) {
        User user = UserTransformer.convertDtoToEntity(requestUser);
        userRepository.save(user);
        return "User added successfully";
    }

    public void updateEmail(UpdateEmail updateEmail) throws UserNotFound, EmailIdException {
        int userId = updateEmail.getUserId();
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
            throw new UserNotFound("Cannot find the User.");

        User user = userOptional.get();
        String currEmailId = user.getEmailId();
        String updatedEmailId = updateEmail.getUpdatedEmailId();

        if(updatedEmailId==null || currEmailId==updatedEmailId)
            throw new EmailIdException("Please enter a proper EmailId.");

        user.setEmailId(updatedEmailId);
        userRepository.save(user);
    }

    public ResponseUser getUserByEmail(String emailId) throws UserNotFound, EmailIdException {
        User user = userRepository.findByEmailId(emailId);
        if(user == null)
            throw new UserNotFound("Cannot find the User");
        if(user.getEmailId().equals(emailId))
            throw new EmailIdException("You're current emailId is same as the requested emailId.");

        return UserTransformer.convertEntityToDto(user);
    }
    public Date getVaccDate(int userId) throws UserNotFound, DoseNotFound {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
            throw new UserNotFound("Cannot find the User.");
        Dose dose = userOptional.get().getDose();
        if(dose==null)
            throw new DoseNotFound("User is not associated with a Dose.");
        return dose.getVaccinationDate();
    }

    public List<Appointment> getAppointments(int userId) throws UserNotFound {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
            throw new UserNotFound("Cannot find the User.");

        return userOptional.get().getAppointmentList();
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
