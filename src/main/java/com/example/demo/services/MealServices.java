package com.example.demo.services;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.MealDTO;
import com.example.demo.dto.MealResponseDTO;


public interface MealServices {

List<MealResponseDTO> findAll();

MealResponseDTO findById(Long id);

MealResponseDTO findByName(String name);

MealResponseDTO createMeal(MealDTO mealDTO) throws Exception;

MealResponseDTO updateMeal(MealDTO mealDTO, Long id);

void deleteMeal(Long id);

}
