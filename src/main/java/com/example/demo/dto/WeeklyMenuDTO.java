
package com.example.demo.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyMenuDTO {

	public Long id;
	
	public LocalDate dateFrom;
	
	public LocalDate dateTo;
	
	public String image;
	
    private Set<DailyMenuDTO> menus= new HashSet<>();
}
