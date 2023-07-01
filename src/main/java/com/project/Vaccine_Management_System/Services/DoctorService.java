package com.project.Vaccine_Management_System.Services;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.AssociateDoctor;
import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestDoctor;
import com.project.Vaccine_Management_System.Exceptions.CenterNotFound;
import com.project.Vaccine_Management_System.Exceptions.DoctorNotFound;
import com.project.Vaccine_Management_System.Exceptions.EmailIdException;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Models.VaccinationCenter;
import com.project.Vaccine_Management_System.Repositories.DoctorRepository;
import com.project.Vaccine_Management_System.Repositories.VaccinationCenterRepository;
import com.project.Vaccine_Management_System.Transformers.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private VaccinationCenterRepository vaccinationCenterRepository;

    public void addDoctor(RequestDoctor requestDoctor) throws EmailIdException {
        if(requestDoctor.getEmailId()==null)
            throw new EmailIdException("EmailId is mandatory.");
        Doctor doctor = DoctorTransformer.convertDtoToEntity(requestDoctor);
        try{
            doctorRepository.save(doctor);
        }catch (Exception e){
            throw new EmailIdException("Doctor with emailId already present.");
        }
    }

    public void associateDoctor(AssociateDoctor associateDoctor) throws DoctorNotFound, CenterNotFound {
        int docId = associateDoctor.getDocId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);
        if(doctorOptional.isEmpty())
            throw new DoctorNotFound("Cannot find the Doctor.");

        int centerId = associateDoctor.getCenterId();
        Optional<VaccinationCenter> vaccinationCenterOptional = vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty())
            throw new CenterNotFound("Cannot find the Vaccination Center.");

        Doctor doctor = doctorOptional.get();
        VaccinationCenter vaccinationCenter = vaccinationCenterOptional.get();

        if(doctor.getVaccinationCenter()!=null)
            throw new DoctorNotFound("Doctor is already associated with a Vaccination center.");

        doctor.setVaccinationCenter(vaccinationCenter);
        vaccinationCenter.getDoctorList().add(doctor);
        vaccinationCenterRepository.save(vaccinationCenter);
    }

    public List<Appointment> getAppointments(int docId) throws DoctorNotFound {
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);
        if(doctorOptional.isEmpty())
            throw new DoctorNotFound("Cannot find the Doctor.");

        return doctorOptional.get().getAppointmentList();
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

}
