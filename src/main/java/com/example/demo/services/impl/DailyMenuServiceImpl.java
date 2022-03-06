package com.example.demo.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.DailyMenuDTO;
import com.example.demo.entities.DailyMenu;
import com.example.demo.entities.Meal;
import com.example.demo.mapper.DailyManuMapper;
import com.example.demo.repositories.DailyMenuRepository;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.WeeklyMenuRepository;
import com.example.demo.services.DailyMenuService;

@Service
public class DailyMenuServiceImpl implements DailyMenuService {

	@Autowired
	MealRepository mealRepository;

	@Autowired
	DailyMenuRepository dailyMenuRepository;

	@Autowired
	WeeklyMenuRepository weeklyMenuRepository;

	@Override
	public List<DailyMenuDTO> findAll() {

		List<DailyMenu> menus = dailyMenuRepository.findAll();

		List<DailyMenuDTO> dailyMenuDTO = new ArrayList<>();

		for (DailyMenu menu : menus) {
			dailyMenuDTO.add(DailyManuMapper.INSTANCE.entityToDto(menu));
		}

		return dailyMenuDTO;

	}

	@Override
	public void deleteDailyMenu(Long id) {
		dailyMenuRepository.deleteById(id);
	}

	@Override
	public DailyMenuDTO createDailyMenu(DailyMenuDTO dailyMenuDTO) {
		DailyMenu dailyMenu = DailyManuMapper.INSTANCE.dtoToEntity(dailyMenuDTO);
		dailyMenuRepository.save(dailyMenu);
		return DailyManuMapper.INSTANCE.entityToDto(dailyMenu);
	}

	@Override
	public DailyMenuDTO findByDate(LocalDate date) {
		DailyMenu dailyMenu = dailyMenuRepository.findByDate(date).orElseThrow();
		DailyMenuDTO dailyMenuDTO = DailyManuMapper.INSTANCE.entityToDto(dailyMenu);
		return dailyMenuDTO;
	}

	@Override
	public List<DailyMenuDTO> findAllByDateBetween(LocalDate from, LocalDate to) {
		List<DailyMenu> dailyMenuList = dailyMenuRepository.findByDateBetween(from, to);
		List<DailyMenuDTO> dailyMenuDTOList = new ArrayList<>();
		for (DailyMenu dailyMenu : dailyMenuList) {
			DailyMenuDTO dailyMenuDTO = DailyManuMapper.INSTANCE.entityToDto(dailyMenu);
			dailyMenuDTOList.add(dailyMenuDTO);
		}
		return dailyMenuDTOList;
	}

	@Override
	public DailyMenuDTO addMealToMenu(Long dailyManuId, Long mealId) {
		DailyMenu dailyMenu = dailyMenuRepository.findById(dailyManuId).orElseThrow();
		Meal meal = mealRepository.findById(mealId).orElseThrow();

		meal.getMenues().add(dailyMenu);
		mealRepository.save(meal);

		dailyMenuRepository.save(dailyMenu);

		return DailyManuMapper.INSTANCE.entityToDto(dailyMenu);
	}

	@Override
	public DailyMenuDTO deleteMealFromMenu(Long dailyManuId, Long mealId) {
		DailyMenu dailyMenu = dailyMenuRepository.findById(dailyManuId).orElseThrow();
		Meal meal = mealRepository.findById(mealId).orElseThrow();

		if (dailyMenu.getMeals().contains(meal)) {
			dailyMenu.getMeals().remove(meal);
//			meal.setDailyMenu(null);
			mealRepository.save(meal);
		}

		dailyMenuRepository.save(dailyMenu);

		return DailyManuMapper.INSTANCE.entityToDto(dailyMenu);
	}

}