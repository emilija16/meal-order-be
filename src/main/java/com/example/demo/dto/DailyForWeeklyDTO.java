package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.example.demo.entities.Meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyForWeeklyDTO {

	private LocalDate date;
	
	private List<MealIDDTO> meals; 
}
