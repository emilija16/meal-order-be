package com.example.demo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MealDTO;
import com.example.demo.dto.MealResponseDTO;
import com.example.demo.entities.DailyMenu;
import com.example.demo.entities.Meal;
import com.example.demo.entities.MealSize;
import com.example.demo.entities.MealType;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.exceptions.NullException;
import com.example.demo.mapper.MealMapper;
import com.example.demo.repositories.DailyMenuRepository;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.MealSizeRepository;
import com.example.demo.repositories.MealTypeRepository;
import com.example.demo.services.MealServices;

@Service
public class MealServiceImpl implements MealServices {

	@Autowired
	private MealRepository mealRepository;

	@Autowired
	MealTypeRepository mealTypeRepository;

	@Autowired
	MealSizeRepository mealSizeRepository;

	@Autowired
	DailyMenuRepository dailyMenuRepository;

	@Override
	public List<MealResponseDTO> findAll() {

		List<Meal> meals = mealRepository.findAll();

		List<MealResponseDTO> mealResponseDto = new ArrayList<>();

		for (Meal meal : meals) {
			mealResponseDto.add(MealMapper.INSTANCE.entityToDto(meal));
		}

		return mealResponseDto;
	}

	@Override
	public MealResponseDTO findById(Long id) {

		Meal meal = mealRepository.findById(id).orElseThrow();

		return MealMapper.INSTANCE.entityToDto(meal);
	}

	@Override
	public MealResponseDTO createMeal(MealDTO mealDTO) throws Exception {
		
		if(mealDTO.getMealSizeId() == null || mealDTO.getMealTypeId() == null) {
			throw new NullException("MealSizeId and MealTypeId must not be null");
		}
		
		MealType mealType = mealTypeRepository.findById(mealDTO.getMealTypeId()).orElseThrow();
		MealSize mealSize = mealSizeRepository.findById(mealDTO.getMealSizeId()).orElseThrow();

		Meal meal = MealMapper.INSTANCE.dtoToEntity(mealDTO);
		meal.setMealType(mealType);
		meal.setMealSize(mealSize);
		mealRepository.save(meal);
		return MealMapper.INSTANCE.entityToDto(meal);
	}

	@Override
	public MealResponseDTO updateMeal(MealDTO mealDTO, Long id) {
		Meal meal = mealRepository.findById(id).orElseThrow();
		meal.setName(mealDTO.getName());
		mealRepository.save(meal);

		return MealMapper.INSTANCE.entityToDto(meal);
	}

	@Override
	public MealResponseDTO findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMeal(Long id) {
		boolean hasMeal = mealRepository.existsById(id);
		if (!hasMeal) {
			throw new NotUniqueException("Ovo nije dozvoljeno");
		}
		mealRepository.deleteById(id);

	}

}
