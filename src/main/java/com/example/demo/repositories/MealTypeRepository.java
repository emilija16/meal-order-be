package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.MealType;


public interface MealTypeRepository extends JpaRepository<MealType, Long > {
	
	public Optional<MealType> findByName(String name);

}
