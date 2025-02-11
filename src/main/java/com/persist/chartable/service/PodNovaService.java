package com.persist.chartable.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persist.chartable.DTO.PodNovaDto;
import com.persist.chartable.DTO.ResponsePodNovaLoginDto;
import com.persist.chartable.entity.LoginRequest;
import com.persist.chartable.entity.PodNovaSignUpEntity;
import com.persist.chartable.repository.PodNovaRepo;

@Service
public class PodNovaService {

	@Autowired
	PodNovaRepo podNovaRepo;

	public Optional<PodNovaSignUpEntity> checkExistingUser(String email, String password) {
		List<PodNovaSignUpEntity> users = podNovaRepo.findByEmailANDPassword(email, password);
		return users.stream().filter(user -> user.getEmail().equals(email)).findFirst();
	}

	public PodNovaDto addDetails(PodNovaSignUpEntity request) {
		PodNovaSignUpEntity login = new PodNovaSignUpEntity();
		login.setPodNovaId(request.getPodNovaId());
		login.setName(request.getName());
		login.setEmail(request.getEmail());
		login.setPassword(request.getPassword());

		podNovaRepo.save(login);
		
		PodNovaDto userDetails = new PodNovaDto();
		userDetails.setPodNovaId(request.getPodNovaId());
		userDetails.setEmail(request.getEmail());
		userDetails.setPassword(request.getPassword());
		userDetails.setName(request.getName());
		return userDetails;
		//return new PodNovaDto();
	}

	public ResponsePodNovaLoginDto loginDetails(LoginRequest login) {
	
		throw new UnsupportedOperationException("Unimplemented method 'loginDetails'");
	}

}
