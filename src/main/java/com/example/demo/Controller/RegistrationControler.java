package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.MyAppUser;
import com.example.demo.Model.MyAppUserRepositry;
import com.example.demo.security.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class RegistrationControler {
    
    @Autowired
    private MyAppUserRepositry MyAppUserRepositry;

    @PostMapping(path = "/signup", consumes = "application/json")
    public MyAppUser createUser(@RequestBody MyAppUser newUser) {
        // Here, you would typically save the new user to the database.
        // For demonstration purposes, we'll just return the received user object.

        newUser.setPassword(PasswordEncoder.encode(newUser.getPassword())); // In a real application, hash the password before saving.

        return MyAppUserRepositry.save(newUser);
    }
    
}
