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

import com.example.demo.dto.MealSizeDTO;
import com.example.demo.entities.MealSize;
import com.example.demo.services.MealSizeService;

@RestController
@RequestMapping("/mealsize")
public class MealSizeController {
	
	@Autowired
	
	private MealSizeService mealSizeService;
	
	@GetMapping
	public ResponseEntity<List<MealSizeDTO>> findAll() {
		return new ResponseEntity<List<MealSizeDTO>>(mealSizeService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MealSizeDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(mealSizeService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<MealSizeDTO> findByName(@PathVariable String name){
		return new ResponseEntity<>(mealSizeService.findByName(name), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createMealSize(@Valid @RequestBody MealSizeDTO mealSizeDTO, BindingResult result){
		
		if(result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			for(ObjectError error : result.getAllErrors()) {
				errors.add(error.getDefaultMessage());
			}
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(mealSizeService.createMealSize(mealSizeDTO), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<MealSizeDTO>updateMealSize(@RequestBody MealSizeDTO mealSizeDTO, @PathVariable Long id){
		return new ResponseEntity<>(mealSizeService.updateMealSize(mealSizeDTO,id), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteMealSize(@PathVariable Long id){
		mealSizeService.deleteMealSize(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
