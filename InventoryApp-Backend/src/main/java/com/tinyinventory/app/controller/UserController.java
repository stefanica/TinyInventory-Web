package com.tinyinventory.app.controller;

import com.tinyinventory.app.exceptions.EmailAlreadyExistsException;
import com.tinyinventory.app.exceptions.InvalidPasswordFormatException;
import com.tinyinventory.app.exceptions.UsernameAlreadyExistsException;
import com.tinyinventory.app.model.User;
import com.tinyinventory.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> saveUser(@RequestBody User user) {
       try {
           User savedUser = userService.saveUser(user);

           Map<String, Object> response = new HashMap<>();
           response.put("username", savedUser.getUsername());
           response.put("fullName", savedUser.getFullName());
           response.put("email", savedUser.getEmail());
           response.put("message", "User registered successfully");

           return ResponseEntity
                   .status(HttpStatus.CREATED)
                   .body(response);

       } catch (UsernameAlreadyExistsException e) {
           return ResponseEntity
                   .status(HttpStatus.CONFLICT)
                   .body(Map.of("message", "Username already taken"));
       } catch (EmailAlreadyExistsException e) {
           return ResponseEntity
                   .status(HttpStatus.CONFLICT)
                   .body(Map.of("message", e.getMessage()));
       } catch (InvalidPasswordFormatException e) {
           return ResponseEntity
                   .status(HttpStatus.BAD_REQUEST)
                   .body(Map.of("message", e.getMessage())); //gets the message from the UserService (exception creation)
       } catch (Exception e) {
           return ResponseEntity
                   .status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body(Map.of("message", "An unexpected error occurred"));
       }

    }

}
