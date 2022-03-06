package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.MealTypeDTO;
import com.example.demo.entities.MealType;

@Mapper
public abstract class MealTypeMapper {

	
	public static MealTypeMapper INSTANCE = Mappers.getMapper(MealTypeMapper.class);

	public abstract  MealTypeDTO entityToDTO(MealType typeMeal);
	
	public abstract MealType DtoToEntity (MealTypeDTO typeMealDto);
	
}
