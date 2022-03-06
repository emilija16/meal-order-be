package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JwtTokenState;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserAuthRequestDTO;
import com.example.demo.services.UserService;
import com.example.demo.utils.Encryption;

@RestController
@RequestMapping("/auth")

public class AuthController {
	
	@Autowired
	UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtTokenState> login(@RequestBody UserAuthRequestDTO userAuthRequestDto) {
		
		JwtTokenState jwtTokenState = userService.checkUserLogin(userAuthRequestDto);
		
		HttpStatus httpStatus = HttpStatus.ACCEPTED;
		
		if(jwtTokenState == null) {
			httpStatus = HttpStatus.FORBIDDEN;
		}
		return new ResponseEntity<>(jwtTokenState, httpStatus);
	}
	
	@RequestMapping(
		    value = "/registration", 
		    method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody RegistrationDTO registrationDto) {
		if(registrationDto.getPassword() == null || registrationDto.getPassword().equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		String encryptedPass = Encryption.getPassEncoded(registrationDto.getPassword());
		registrationDto.setPassword(encryptedPass);

		try {
			userService.createUser(registrationDto);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
		}

	}

}
