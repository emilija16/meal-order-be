package com.example.demo.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;





@Entity
@Table(name = "daily_menu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyMenu {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long id;
	
	@Column(name = "date")
	public LocalDate date;
	
	@ManyToMany(mappedBy = "menues", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<Meal> meals;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "weeklyMenu")
	private WeeklyMenu weeklyMenu;
	
	
	
}
