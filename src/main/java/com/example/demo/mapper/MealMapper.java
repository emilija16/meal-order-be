package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.MealDTO;
import com.example.demo.dto.MealIDDTO;
import com.example.demo.dto.MealResponseDTO;
import com.example.demo.entities.Meal;


@Mapper
public interface MealMapper {

MealMapper INSTANCE = Mappers.getMapper(MealMapper.class);
	
	public MealResponseDTO entityToDto(Meal meal);

	public Meal dtoToEntity(MealDTO mealDto);
	
	public Meal dtoToEntity(MealResponseDTO mealDto);
	
	public Meal dtoToEntity(MealIDDTO mealDto);
	
	

}

