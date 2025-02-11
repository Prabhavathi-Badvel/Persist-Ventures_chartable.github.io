package com.persist.chartable.DTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResponsePodNovaLoginDto {
    private String message;
	private boolean status;
    private String jwtToken;
	private PodNovaDto userData;

}
