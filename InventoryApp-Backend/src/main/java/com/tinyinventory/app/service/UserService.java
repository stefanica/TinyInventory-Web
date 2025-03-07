package com.tinyinventory.app.service;

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
    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User saveUser(User user) {

        if (userRepo.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        //usually, I think this is done on the front-end. This is here in case I need it
        if (!validatePassword(user.getPassword())) {
            throw new InvalidPasswordFormatException("Password format is invalid");
        }

        //user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    //usually this is done on the front end
    public boolean validatePassword(String password) {
        return true;
    }

}
