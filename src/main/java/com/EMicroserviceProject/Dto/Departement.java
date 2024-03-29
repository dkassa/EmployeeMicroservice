package com.EMicroserviceProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Departement {
	
	private int depId;
	private String depCode;
	private String depAddress;
	private String depName;
	
}
