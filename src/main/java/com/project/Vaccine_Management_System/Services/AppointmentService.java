package com.project.Vaccine_Management_System.Services;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestAppointment;
import com.project.Vaccine_Management_System.Exceptions.DoctorNotFound;
import com.project.Vaccine_Management_System.Exceptions.UserNotFound;
import com.project.Vaccine_Management_System.Models.Appointment;
import com.project.Vaccine_Management_System.Models.Doctor;
import com.project.Vaccine_Management_System.Models.User;
import com.project.Vaccine_Management_System.Repositories.AppointmentRepository;
import com.project.Vaccine_Management_System.Repositories.DoctorRepository;
import com.project.Vaccine_Management_System.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private JavaMailSender emailSender;

    public void bookAppointment(RequestAppointment requestAppointment) throws DoctorNotFound, UserNotFound {
        int userId = requestAppointment.getUserId();
        Optional<User> userOptional =  userRepository.findById(userId);
        if(userOptional.isEmpty())
            throw new UserNotFound("Cannot find the User.");

        int docId = requestAppointment.getDoctorId();
        Optional<Doctor> doctorOptional = doctorRepository.findById(docId);
        if(doctorOptional.isEmpty())
            throw new DoctorNotFound("Cannot find the Doctor.");

        User user = userOptional.get();
        Doctor doctor = doctorOptional.get();

        Appointment appointment = Appointment.builder().appointmentTime(requestAppointment.getAppointmentTime())
                .appointmentDate(requestAppointment.getAppointmentDate())
                .user(user)
                .doctor(doctor).build();

        appointment = appointmentRepository.save(appointment);

        user.getAppointmentList().add(appointment);
        doctor.getAppointmentList().add(appointment);

        userRepository.save(user);
        doctorRepository.save(doctor);

        //Sending booking status to the user via email
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        String message = "Hi " + user.getName()+"\n"+
                "You have successfully booked an appointment on "+appointment.getAppointmentDate() + " at "+appointment.getAppointmentTime()+"\n"+
                "You doctor is "+doctor.getName()+ "\n"+
                "Please reach at "+doctor.getVaccinationCenter().getAddress()+"\n"
                + "Mask is mandatory";

        mailMessage.setFrom("3zomato3@gmail.com");
        mailMessage.setTo(user.getEmailId());
        mailMessage.setSubject("Appointment Booked Successfully.");
        mailMessage.setText(message);

        emailSender.send(mailMessage);
    }

    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }
}
