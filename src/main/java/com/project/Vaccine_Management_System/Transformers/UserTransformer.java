package com.project.Vaccine_Management_System.Transformers;

import com.project.Vaccine_Management_System.Dtos.RequestDtos.RequestUser;
import com.project.Vaccine_Management_System.Dtos.RespnseDtos.ResponseUser;
import com.project.Vaccine_Management_System.Models.User;

public class UserTransformer {
    public static User convertDtoToEntity(RequestUser requestUser) {
        User user = User.builder().name(requestUser.getName())
                .age(requestUser.getAge())
                .gender(requestUser.getGender())
                .emailId(requestUser.getEmailId())
                .mobileNo(requestUser.getMobileNo()).build();
        
        return user;
    }

    public static ResponseUser convertEntityToDto(User user) {
        ResponseUser responseUser = ResponseUser.builder().name(user.getName())
                .age(user.getAge())
                .gender(user.getGender())
                .emailId(user.getEmailId())
                .mobileNo(user.getMobileNo()).build();

        return responseUser;
    }

    public static void setStatusMessage(ResponseUser responseUser, boolean b) {
        if(b){
            responseUser.setStatusCode("200");
            responseUser.setStatusMessage("Found the User with this email.");
        }
        else{
            responseUser.setStatusCode("400");
            responseUser.setStatusMessage("Cannot find the User with this email.");
        }
    }

}
