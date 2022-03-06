package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MealTypeDTO;
import com.example.demo.dto.ShoppingItemDTO;
import com.example.demo.services.ShoppingItemService;

@RestController
@RequestMapping("/shoppingItem")
public class ShoppingItemController {

	@Autowired
	ShoppingItemService shoppingItemService;
	
	@GetMapping
	public ResponseEntity<List<ShoppingItemDTO>> findAll() {
		return new ResponseEntity<List<ShoppingItemDTO>>(shoppingItemService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShoppingItemDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(shoppingItemService.findById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus>deleteShoppingItem(@PathVariable Long id){
		shoppingItemService.deleteShoppingItem(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
