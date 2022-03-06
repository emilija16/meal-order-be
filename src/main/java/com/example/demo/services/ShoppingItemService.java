package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ShoppingItemDTO;

public interface ShoppingItemService {

	List<ShoppingItemDTO> findAll();

	ShoppingItemDTO findById(Long id);

	ShoppingItemDTO createShoppingItem(ShoppingItemDTO shoppingItemDTO);

	ShoppingItemDTO updateShoppingItem(ShoppingItemDTO shoppingItemDTO, Long id);

	void deleteShoppingItem(Long id);
	
	void validateShoppingItem(ShoppingItemDTO shoppingItem);

}
