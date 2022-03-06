package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.MealSizeDTO;


public interface MealSizeService{

	List<MealSizeDTO> findAll();
	
	MealSizeDTO findById(Long id);
	
	MealSizeDTO findByName(String name);
	
	MealSizeDTO createMealSize(MealSizeDTO mealSizeDTO);
	
	MealSizeDTO updateMealSize(MealSizeDTO mealSizeDTO, Long id);
	
	void deleteMealSize(Long id);

}
