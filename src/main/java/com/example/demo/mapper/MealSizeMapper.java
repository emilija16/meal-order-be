package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.MealSizeDTO;
import com.example.demo.entities.MealSize;

@Mapper
public interface MealSizeMapper {
	
	MealSizeMapper INSTANCE = Mappers.getMapper(MealSizeMapper.class);
	
	public MealSizeDTO entityToDto(MealSize mealSize);

	public MealSize dtoToEntity(MealSizeDTO mealSizeDto);

}


