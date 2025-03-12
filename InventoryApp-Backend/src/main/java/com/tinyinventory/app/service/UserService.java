package com.tinyinventory.app.service;

import com.tinyinventory.app.dto.UserResponseDto;
import com.tinyinventory.app.exceptions.EmailAlreadyExistsException;
import com.tinyinventory.app.exceptions.InvalidPasswordFormatException;
import com.tinyinventory.app.exceptions.UsernameAlreadyExistsException;
import com.tinyinventory.app.model.User;
import com.tinyinventory.app.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    //Uncomment Spring Security framework from pom.xml file
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserResponseDto saveUser(User user) {

        if (userRepo.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        //This can also be done on the front-end, but for security reasons needs to be also on backend
        if (!validatePassword(user.getPassword())) {
            throw new InvalidPasswordFormatException("Password format is invalid");
        }

        //Used to encrypt the password in the database
        //user.setPassword(encoder.encode(user.getPassword()));

        return new UserResponseDto(userRepo.save(user));
    }


    //This can also be done on the front-end, but for security reasons needs to be also on backend
    public boolean validatePassword(String password) {
        return true;
    }

}
