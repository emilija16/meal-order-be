package com.example.demo.services;



import java.time.LocalDate;
import java.util.List;

import com.example.demo.dto.DailyMenuDTO;

public interface DailyMenuService  {
	 
	DailyMenuDTO createDailyMenu (DailyMenuDTO dailyMenuDTO);
	
	List<DailyMenuDTO> findAll();
	
	DailyMenuDTO findByDate (LocalDate date);
	
	void deleteDailyMenu (Long id);
	
	public List<DailyMenuDTO> findAllByDateBetween(LocalDate from, LocalDate to);
	
	DailyMenuDTO addMealToMenu(Long dailyMenuId, Long mealId);
	
	DailyMenuDTO deleteMealFromMenu(Long dailyMenuId, Long mealId);
	
	}
