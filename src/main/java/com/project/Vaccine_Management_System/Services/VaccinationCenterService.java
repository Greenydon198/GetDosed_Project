package com.project.Vaccine_Management_System.Services;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestCenter;
import com.project.Vaccine_Management_System.Exceptions.AddressException;
import com.project.Vaccine_Management_System.Exceptions.CenterNotFound;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Models.VaccinationCenter;
import com.project.Vaccine_Management_System.Repositories.VaccinationCenterRepository;
import com.project.Vaccine_Management_System.Transformers.CenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VaccinationCenterService {

    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public void addCenter(RequestCenter requestCenter) throws AddressException {
        if(requestCenter.getAddress()==null || requestCenter.getAddress().length()==0)
            throw new AddressException("Address is mandatory.");

        VaccinationCenter vaccinationCenter = CenterTransformer.convertDtoToEntity(requestCenter);

        vaccinationCenterRepository.save(vaccinationCenter);
    }

    public List<Doctor> doctorList(int centerId) throws CenterNotFound {
        Optional<VaccinationCenter> centerOptional = vaccinationCenterRepository.findById(centerId);
        if(centerOptional.isEmpty())
            throw new CenterNotFound("Cannot find the center.");

        return centerOptional.get().getDoctorList();
    }

    public List<VaccinationCenter> getAll() {
        return vaccinationCenterRepository.findAll();
    }
}
