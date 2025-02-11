package com.persist.chartable.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

	private String podNovaId;
	private String name;
	private String email;
	private String password;

	
}
