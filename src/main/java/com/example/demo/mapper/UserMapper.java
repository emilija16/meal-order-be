package com.example.demo.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
uses = OrderMapper.class)
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	public UserDTO entityToDTO (User user);
	
	public User dtoToEntity (UserDTO userDTO);
	
}
