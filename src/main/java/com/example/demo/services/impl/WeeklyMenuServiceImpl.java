package com.example.demo.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.dto.DailyMenuDTO;
import com.example.demo.dto.MealIDDTO;
import com.example.demo.dto.WeeklyMenuDTO;
import com.example.demo.entities.DailyMenu;
import com.example.demo.entities.Meal;
import com.example.demo.entities.WeeklyMenu;
import com.example.demo.mapper.WeeklyMenuMapper;
import com.example.demo.repositories.DailyMenuRepository;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.WeeklyMenuRepository;
import com.example.demo.services.WeeklyMenuService;

@Service
public class WeeklyMenuServiceImpl implements WeeklyMenuService {

	@Autowired
	WeeklyMenuRepository weeklyMenuRepository;

	@Autowired
	DailyMenuRepository dailyMenuRepository;

	@Autowired
	MealRepository mealRepository;

	@Override
	public WeeklyMenuDTO create(WeeklyMenuDTO weeklyMenuDTO, MultipartFile[] files) {

		WeeklyMenu weeklyMenu = new WeeklyMenu();
		weeklyMenu.setDateFrom(weeklyMenuDTO.getDateFrom());
		weeklyMenu.setDateTo(weeklyMenuDTO.getDateTo());

		Set<DailyMenu> menus = new HashSet<>();

		for (DailyMenuDTO dailyMenuDto : weeklyMenuDTO.getMenus()) {
			DailyMenu dailyMenu = new DailyMenu();
			dailyMenu.setDate(dailyMenuDto.getDate());
			Set<Meal> meals = new HashSet<>();

			for (MealIDDTO mealDto : dailyMenuDto.getMeals()) {
				Meal meal = mealRepository.findById(mealDto.getId()).orElseThrow();
				meals.add(meal);
			}
			
			dailyMenu.setMeals(meals);
			menus.add(dailyMenu);
		}

		weeklyMenu.setMenus(menus);

		weeklyMenu.getMenus().removeIf(x -> x.getId() == null);
		weeklyMenuRepository.save(weeklyMenu);
		LocalDate currentDate = weeklyMenuDTO.getDateFrom();

		for (DailyMenuDTO dailyMenu : weeklyMenuDTO.getMenus()) {
			DailyMenu createdDailyMenu = new DailyMenu();
			createdDailyMenu.setDate(currentDate);
			createdDailyMenu.setWeeklyMenu(weeklyMenu);
			dailyMenuRepository.save(createdDailyMenu);

			for (MealIDDTO mealDTO : dailyMenu.getMeals()) {
				Meal meal = mealRepository.findById(mealDTO.getId()).orElseThrow();
				meal.getMenues().add(createdDailyMenu);
				mealRepository.save(meal);
//				createdDailyMenu.getMeals().add(meal);
			}

			dailyMenuRepository.save(createdDailyMenu);
			weeklyMenu.getMenus().add(createdDailyMenu);

		}

		weeklyMenuRepository.save(weeklyMenu);

		
		saveImagesToWeeklyMenuFolder(weeklyMenu.getId(), files); 

		return WeeklyMenuMapper.INSTANCE.entityToDto(weeklyMenu);
	}

	@Override
	public List<WeeklyMenuDTO> findAll() {
		List<WeeklyMenu> weeklyMenuList = weeklyMenuRepository.findAll();
		List<WeeklyMenuDTO> weeklyMenuDTOList = new ArrayList();
		for (WeeklyMenu weeklyMenu : weeklyMenuList) {
			weeklyMenuDTOList.add(WeeklyMenuMapper.INSTANCE.entityToDto(weeklyMenu));
		}
		return weeklyMenuDTOList;
	}

	@Override
	public WeeklyMenuDTO deleteDailyMenuFromWeakly(Long weeklyMenuId, Long dailyMenuId) {
		WeeklyMenu weeklyMenu = weeklyMenuRepository.findById(weeklyMenuId).orElseThrow();
		DailyMenu dailyMenu = dailyMenuRepository.findById(dailyMenuId).orElseThrow();

		if (weeklyMenu.getMenus().contains(dailyMenu)) {
			weeklyMenu.getMenus().remove(dailyMenu);
			dailyMenu.setWeeklyMenu(null);
			dailyMenuRepository.save(dailyMenu);
		}

		weeklyMenuRepository.save(weeklyMenu);

		return WeeklyMenuMapper.INSTANCE.entityToDto(weeklyMenu);

	}
	
	private void saveImagesToWeeklyMenuFolder(Long id, MultipartFile[] files) {
		
		String UPLOADED_FOLDER = "src/main/resources/pictures/" + id;
		
		File directorium = new File(UPLOADED_FOLDER);
		if(!directorium.exists()) {
			directorium.mkdir();
		}
		
		
		for(MultipartFile file : files) {
			if (file.isEmpty()) {
				throw new NoSuchElementException();
			}
			try {
				byte[] bytes = file.getBytes();
				Path path = Paths.get(directorium.getPath() + "/" + file.getOriginalFilename());
				Files.write(path, bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
