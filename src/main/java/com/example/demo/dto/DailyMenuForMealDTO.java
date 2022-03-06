package com.example.demo.dto;

import java.time.LocalDate;
import java.util.Set;

import com.example.demo.entities.Meal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DailyMenuForMealDTO {

	public Long id;
	
	public LocalDate date;
}
