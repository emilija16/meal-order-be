package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;

import com.example.demo.entities.DailyMenu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateWeeklyMenuDTO {

	private Long id;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	private List<DailyForWeeklyDTO> dailyMenus;
	
}
