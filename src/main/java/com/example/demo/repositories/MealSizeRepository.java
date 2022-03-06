package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.MealSize;


public interface MealSizeRepository extends JpaRepository<MealSize , Long > {
	
	public Optional<MealSize> findByName(String name);
}
