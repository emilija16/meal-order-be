package com.example.demo.services.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JwtTokenState;
import com.example.demo.dto.RegistrationDTO;
import com.example.demo.dto.UserAuthRequestDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mapper.RegistrationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.RoleRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.utils.Encryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class UserServiceImpl implements UserService {

	@Value("${spring.security.secret-key}")
	private String secretKey;
//	@Value("${spring.security.token-duration}")
//	private Integer tokenDuration;
	
	private Long expiration = 8L;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public RegistrationDTO createUser(RegistrationDTO registrationDto) throws Exception {
		Optional<User> existUser = userRepository.findByEmail(registrationDto.getEmail());
		
		if (existUser.isPresent() || registrationDto.getPassword() == null || registrationDto.getPassword() == "") {
			throw new NotUniqueException("User with that email already exist or password is incorrect");
		} else {

			Optional<Role> roleOptional = roleRepository.findByNameRole("ROLE_USER");

			if (roleOptional.isPresent()) {
				User newUser = RegistrationMapper.INSTANCE.dtoToEntity(registrationDto);
				newUser.setRole(roleOptional.get());
				userRepository.saveAndFlush(newUser);
			}
			return registrationDto;
		}
	}

	@Override
	public JwtTokenState checkUserLogin(UserAuthRequestDTO userAuthRequestDTO) {

		Optional<User> userOptional = userRepository.findByEmail(userAuthRequestDTO.getEmail());
		
		if(userOptional != null) {
			User user = userOptional.get();
			if (user.isActive() == true && user != null && Encryption.validatePassword(userAuthRequestDTO.getPassword(), user.getPassword())) {
				String token = getJWTToken(user);
				return new JwtTokenState(user.getEmail(), token);
			}
		}
		return null;
	}

	private String getJWTToken(User user) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(user.getRole().getNameRole());
			
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getEmail())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.expiration * 60 * 60 * 1000))
				.signWith(SignatureAlgorithm.HS512, this.secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	@Override
	public List<UserDTO> findAll() {	
		
		List<User> users = userRepository.findAll();
		List<UserDTO> usersDto = new ArrayList<>();
		
		for (User user : users) {

			usersDto.add(UserMapper.INSTANCE.entityToDTO(user));
		}

		return usersDto;
	}

	@Override
	public UserDTO findByID(Long id) {
				
		
		User user = userRepository.findById(id).orElseThrow();

		return UserMapper.INSTANCE.entityToDTO(user);
	}

	@Override
	public void activatedUser(Long id) {
		boolean hasUser = userRepository.existsById(id);
		if (!hasUser) {
			throw new NotUniqueException("Ovo nije dozvoljeno");
		}
		userRepository.deleteById(id);
	}

}
