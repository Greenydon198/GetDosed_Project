package com.project.Vaccine_Management_System.Transformers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestCenter;
import com.project.Vaccine_Management_System.Models.VaccinationCenter;

public class CenterTransformer {

    public static VaccinationCenter convertDtoToEntity(RequestCenter requestCenter){
        VaccinationCenter vaccinationCenter = VaccinationCenter.builder().name(requestCenter.getName())
                .openingTime(requestCenter.getOpeningTime())
                .closingTime(requestCenter.getClosingTime())
                .address(requestCenter.getAddress())
                .doseCapacity(requestCenter.getDoseCapacity()).build();

        return vaccinationCenter;
    }
}
