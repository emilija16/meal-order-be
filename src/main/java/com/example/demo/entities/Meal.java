package com.example.demo.entities;

import java.util.List;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public Long id;

	@Column(name = "name")
	public String name;

	@Column(name = "description")
	public String description;

	@Column(name = "contributions")
	private String contributions;

	@Column(name = "price")
	private double price;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mealType")
	private MealType mealType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mealSize")
	private MealSize mealSize;

	@ManyToMany
	@JoinTable(name = "meal_menues", joinColumns = @JoinColumn(name = "meal_id"), inverseJoinColumns = @JoinColumn(name="menu_id"))
	private Set<DailyMenu> menues;

	@OneToMany(mappedBy = "meal")
	private List<ShoppingItem> items;

	private boolean isTomorrow;
	
	@Column(name = "image")
	private String image;
}
