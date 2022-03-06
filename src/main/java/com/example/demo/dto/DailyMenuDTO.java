package com.example.demo.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.entities.DailyMenu;
import com.example.demo.entities.Meal;
import com.example.demo.entities.WeeklyMenu;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMenuDTO {
	
	public Long id;
	
	public LocalDate date;
	
	public Set<MealIDDTO> meals = new HashSet<>();
	
	public WeeklyMenuDTO weaklyMenu;

}
