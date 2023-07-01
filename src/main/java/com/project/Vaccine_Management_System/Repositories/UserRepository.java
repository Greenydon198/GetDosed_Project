package com.project.Vaccine_Management_System.Repositories;

import com.project.Vaccine_Management_System.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailId(String emailId);

}
