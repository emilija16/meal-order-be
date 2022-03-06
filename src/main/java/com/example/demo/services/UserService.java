package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.JwtTokenState;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserAuthRequestDTO;
import com.example.demo.dto.UserDTO;

public interface UserService {

	RegistrationDTO createUser(RegistrationDTO registrationDto) throws Exception;
	
	JwtTokenState checkUserLogin(UserAuthRequestDTO jwtTokenDto);
	
	List<UserDTO> findAll();
	
	UserDTO findByID (Long  id);
 	
	void activatedUser (Long id);

}
