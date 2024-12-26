package com.furniturestore.controller;

import javax.annotation.PostConstruct;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furniturestore.entity.User;
import com.furniturestore.exception.RegistrationException;
import com.furniturestore.serviceImpl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostConstruct
    public void initRoleAndUser() {
        userServiceImpl.initRoleAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user) {
        try {
        	log.info("Registered Successfully");
            return userServiceImpl.registerNewUser(user);
        } catch (RegistrationException e) {
            return null;
        }
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        try {
        	log.info("This is for Admin Access");
            return "This URL is only accessible to the admin";
        } catch (AccessDeniedException e) {
            return null;
        }
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasRole('User')")
    public String forUser() {
        try {
        	log.info("This is for User Access");
            return "This URL is only accessible to the user";
        } catch (AccessDeniedException e) {
            return null;
        }
    }
}


