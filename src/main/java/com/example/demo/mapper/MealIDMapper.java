package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import com.example.demo.dto.MealIDDTO;
import com.example.demo.entities.Meal;

@Mapper
public interface MealIDMapper {


MealIDMapper INSTANCE = Mappers.getMapper(MealIDMapper.class);

public MealIDDTO entityToDto(Meal meal);

public Meal dtoToEntity(MealIDDTO mealDto);




}
