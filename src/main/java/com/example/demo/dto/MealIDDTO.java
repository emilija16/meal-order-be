package com.example.demo.dto;

import com.example.demo.entities.MealSize;
import com.example.demo.entities.MealType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealIDDTO{
	
	public Long id;
	
	public String name;

	public String description;

	public String contributions;

	public double price;

	private MealType mealType;

	private MealSize mealSize;
	
	private boolean isTomorrow;
	
	private String image;

}
