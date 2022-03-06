package com.example.demo.dto;

import com.example.demo.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	public Long id;

	public String firstName;

	public String lastName;

	public String username;

	public String password;

	public String email;

	public String phoneNumber;

	public Role role;

	public boolean isActive;

}