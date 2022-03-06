package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MealDTO;
import com.example.demo.dto.MealResponseDTO;
import com.example.demo.services.MealServices;



@RestController
@RequestMapping("/meal")

public class MealController {

	@Autowired
	private MealServices mealService;
	
	@GetMapping
	public ResponseEntity<List<MealResponseDTO>> findAll() {
		return new ResponseEntity<List<MealResponseDTO>>(mealService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MealResponseDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(mealService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createMeal(@Valid @RequestBody MealDTO mealDTO, BindingResult result) throws Exception{
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(mealService.createMeal(mealDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MealResponseDTO>updateMeal(@RequestBody MealDTO mealDTO, @PathVariable Long id){
		return new ResponseEntity<>(mealService.updateMeal(mealDTO,id), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteMeal(@PathVariable Long id){
		mealService.deleteMeal(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
	

}
