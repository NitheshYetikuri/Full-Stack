
package com.furniturestore.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.furniturestore.entity.JwtRequest;
import com.furniturestore.entity.JwtResponse;
import com.furniturestore.exception.InvalidCredentialsException;
import com.furniturestore.serviceImpl.JwtServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtServiceImpl jwtServiceImpl;

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createJwtToken(@RequestBody JwtRequest jwtRequest) {
        try {
        	log.info("Token Created"+jwtRequest);
            JwtResponse jwtResponse = jwtServiceImpl.createJwtToken(jwtRequest);
            return ResponseEntity.ok(jwtResponse);
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error.");
        }
    }
}
