package com.persist.chartable.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persist.chartable.DTO.PodNovaDto;
import com.persist.chartable.DTO.ResponsePodNovaLoginDto;
import com.persist.chartable.entity.LoginRequest;
import com.persist.chartable.entity.PodNovaSignUpEntity;
import com.persist.chartable.service.PodNovaService;

@CrossOrigin(origins = "*")
@RestController
public class PodNovaController {

    @Autowired
    private PodNovaService podNovaService;

    @PostMapping("/podcaster-register")
    public ResponseEntity<ResponsePodNovaLoginDto> create(@RequestBody PodNovaSignUpEntity request) {
        Optional<PodNovaSignUpEntity> existingUser = podNovaService.checkExistingUser(request.getEmail(), request.getPassword());
        
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equals(request.getEmail()) &&
                existingUser.get().getPassword().equals(request.getPassword())) {
                
                ResponsePodNovaLoginDto response = new ResponsePodNovaLoginDto();
                response.setMessage("Email already exists");
                response.setStatus(false);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        }

        PodNovaDto userDetails = podNovaService.addDetails(request);
        ResponsePodNovaLoginDto response = new ResponsePodNovaLoginDto();
        response.setMessage("Thanks for registering with us. Please sign in with your registered email and password.");
        response.setStatus(true);
        response.setUserData(userDetails);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/podcaster-login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        try {
            ResponsePodNovaLoginDto response = podNovaService.loginDetails(login);

            if (response.getJwtToken() != null) {
                return ResponseEntity.ok(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
        }
    }
}
