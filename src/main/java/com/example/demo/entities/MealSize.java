package com.example.demo.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal_size")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MealSize {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long id;
	
	@Column(name = "name")
	public String name;
	
	@OneToMany(mappedBy = "mealSize", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JsonIgnore
	private Set<Meal> meal;
}
