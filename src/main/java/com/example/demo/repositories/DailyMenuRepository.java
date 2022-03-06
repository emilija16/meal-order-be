package com.example.demo.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.DailyMenu;

@Repository
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Long> {
	
	public Optional<DailyMenu> findByDate(LocalDate date);
	
	public List<DailyMenu> findByDateBetween(LocalDate from, LocalDate to);

}
