package com.project.Vaccine_Management_System.Services;

import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseUser;
import com.project.Vaccine_Management_System.Exceptions.DoseNotFound;
import com.project.Vaccine_Management_System.Exceptions.UserNotFound;
import com.project.Vaccine_Management_System.Models.Dose;
import com.project.Vaccine_Management_System.Models.User;
import com.project.Vaccine_Management_System.Repositories.DoseRepository;
import com.project.Vaccine_Management_System.Repositories.UserRepository;
import com.project.Vaccine_Management_System.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoseService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DoseRepository doseRepository;

    public String giveDose(String doseId, int userId) throws UserNotFound, DoseNotFound {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new UserNotFound("Cannot find the User");
        }

        User user = userOptional.get();
        if(user.getDose()!=null)
            throw new DoseNotFound("User is already associated with a Dose.");
        Dose dosecheck = doseRepository.findByDoseId(doseId);
        if(dosecheck!=null)
            throw new DoseNotFound("Dose is already associated with a User.");

        Dose dose = new Dose();
        dose.setDoseId(doseId);
        dose.setUser(user);
        user.setDose(dose);
        userRepository.save(user);

        return "Dose added to User Successfully.";
    }

    public ResponseUser getUserByDose(int doseId) throws DoseNotFound {
        Optional<Dose> doseOptional = doseRepository.findById(doseId);
        if(doseOptional.isEmpty())
            throw new DoseNotFound("Cannot find the dose");
        Dose dose = doseOptional.get();

        ResponseUser responseUser = UserTransformer.convertEntityToDto(dose.getUser());
        return responseUser;
    }

    public List<Dose> getAll() {
        return doseRepository.findAll();
    }

}
