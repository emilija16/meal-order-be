package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.MealTypeDTO;

public interface MealTypeService {

	List<MealTypeDTO> findAll();
	
	MealTypeDTO findById(Long id);
	
	MealTypeDTO findByName(String name);
	
	MealTypeDTO createMealType(MealTypeDTO mealTypeDTO);
	
	MealTypeDTO updateMealType(MealTypeDTO mealTypeDTO, Long id);
	
	void deleteMealType(Long id);

}
