package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
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

import com.example.demo.dto.DailyMenuDTO;

import com.example.demo.services.DailyMenuService;

@RestController
@RequestMapping("/dailymenu")
public class DailyMenuController {

		@Autowired
		DailyMenuService dailyMenuService;
		
		@GetMapping
		public ResponseEntity<List<DailyMenuDTO>> findAll() {
			return new ResponseEntity<List<DailyMenuDTO>>(dailyMenuService.findAll(), HttpStatus.OK);
		}
		
		@GetMapping("/findbydate/{date}")
		public ResponseEntity<DailyMenuDTO> findByDate(@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate date) {
			return new ResponseEntity<DailyMenuDTO>(dailyMenuService.findByDate(date), HttpStatus.OK);
		}
		
		@GetMapping("/findbydate/{from}/{to}")
		public ResponseEntity<List<DailyMenuDTO>> findAllByDateBetween(@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate from,@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate to) {
			return new ResponseEntity<List<DailyMenuDTO>>(dailyMenuService.findAllByDateBetween(from, to), HttpStatus.OK);
		}
		
		@DeleteMapping("/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<HttpStatus> deleteDailyMenu(@PathVariable Long id){
			dailyMenuService.deleteDailyMenu(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		@PostMapping
		public ResponseEntity<?> createDailyMenu(@Valid @RequestBody DailyMenuDTO dailyMenuDTO, BindingResult result){
			
			if(result.hasErrors()) {
				List<String> errors = new ArrayList<>();
				for(ObjectError error : result.getAllErrors()) {
					errors.add(error.getDefaultMessage());
				}
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(dailyMenuService.createDailyMenu(dailyMenuDTO), HttpStatus.CREATED);

		}
		
		@PutMapping("addMealToManu/{dailyMenuId}/{mealId}")
		public ResponseEntity<DailyMenuDTO> addMealToMenu(@PathVariable Long dailyMenuId, @PathVariable Long mealId){
			return new ResponseEntity<>(dailyMenuService.addMealToMenu(dailyMenuId, mealId), HttpStatus.CREATED);
		}

		@PutMapping("deleteMealFromManu/{dailyMenuId}/{mealId}")
		public ResponseEntity<DailyMenuDTO> deleteMealFromMenu(@PathVariable Long dailyMenuId, @PathVariable Long mealId){
			return new ResponseEntity<>(dailyMenuService.deleteMealFromMenu(dailyMenuId, mealId), HttpStatus.CREATED);
		}

}
