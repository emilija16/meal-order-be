package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealDTO {
	
	public Long id;
	
	public String name;

	public String description;

	public String contributions;

	public double price;

	private Long mealTypeId;

	private Long mealSizeId;

	private boolean isTomorrow;
	
	private String image;
}
