package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.example.demo.dto.MealTypeDTO;
import com.example.demo.services.MealTypeService;

@RestController
@RequestMapping("/mealtype")
public class MealTypeController {
	
	@Autowired
	private MealTypeService mealTypeService;
	
	
	@GetMapping
	public ResponseEntity<List<MealTypeDTO>> findAll() {
		return new ResponseEntity<List<MealTypeDTO>>(mealTypeService.findAll(), HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<MealTypeDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(mealTypeService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")	
	public ResponseEntity<MealTypeDTO> findByName(@PathVariable String name){
		return new ResponseEntity<>(mealTypeService.findByName(name), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createMealType(@Valid @RequestBody MealTypeDTO mealTypeDTO, BindingResult result){
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(mealTypeService.createMealType(mealTypeDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MealTypeDTO>updateMealType(@RequestBody MealTypeDTO mealTypeDTO, @PathVariable Long id){
		return new ResponseEntity<>(mealTypeService.updateMealType(mealTypeDTO,id), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus>deleteMealType(@PathVariable Long id){
		mealTypeService.deleteMealType(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
	


