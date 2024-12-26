package com.furniturestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furniturestore.entity.Role;
import com.furniturestore.exception.RoleCreationException;
import com.furniturestore.serviceImpl.RoleServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @PostMapping({"/createNewRole"})
    public ResponseEntity<?> createNewRole(@RequestBody Role role) {
        try {
        	log.info("New role Created");
            Role createdRole = roleServiceImpl.createNewRole(role);
            return ResponseEntity.ok(createdRole);
        } catch (RoleCreationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create new role: " + e.getMessage());
        }
    }
}
