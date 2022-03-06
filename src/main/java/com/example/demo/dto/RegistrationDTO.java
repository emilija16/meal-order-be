package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

	private String firstName;

	private String lastName;

	private String username;

	private String password;

	private String email;

	private String phoneNumber;

}
