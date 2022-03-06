package com.example.demo.dto;
import com.example.demo.entities.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingItemDTO {

	
	public Long id;
		
	public int quantity;
	
	private MealResponseDTO meal;
	
//	private OrderDTO order;
	
	

}
