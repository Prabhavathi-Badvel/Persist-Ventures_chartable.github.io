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

@RestController
@CrossOrigin(origins = "http://localhost:5500")
public class PodNovaController {

    @Autowired
    PodNovaService podNovaService;
    ResponsePodNovaLoginDto response = new ResponsePodNovaLoginDto();

    @PostMapping("/podcaster-register")
    public ResponseEntity<?> create(@RequestBody PodNovaSignUpEntity request) {
        ResponsePodNovaLoginDto rs = new ResponsePodNovaLoginDto();
        Optional<PodNovaSignUpEntity> user = podNovaService.checkExistingUser(request.getEmail(),
                request.getPassword());
        if (user.isPresent()) {
            if (user.get().getEmail().equals(request.getEmail())
                    && user.get().getPassword().equals(request.getPassword())) {
                rs.setMessage("Email already exists");
                return new ResponseEntity<>(rs, HttpStatus.OK);
            }
        }
        PodNovaDto userDetails = podNovaService.addDetails(request);
		response.setMessage("Thanks for registering with us. please sign in with registered email and password");
		response.setStatus(true);
		response.setUserData(userDetails);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/podcaster-login")
    public ResponseEntity<?> login(@RequestBody LoginRequest login) {
        try {
            ResponsePodNovaLoginDto response = podNovaService.loginDetails(login);
            if (response.getJwtToken() != null) {
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
