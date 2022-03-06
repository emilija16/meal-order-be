package com.example.demo.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.WeeklyMenuDTO;

public interface WeeklyMenuService {

	

	List<WeeklyMenuDTO> findAll();
	
	WeeklyMenuDTO deleteDailyMenuFromWeakly(Long weeklyMenuId, Long dailyMenuId);

	WeeklyMenuDTO create(WeeklyMenuDTO weeklyMenuDTO, MultipartFile[] files);

	
	
	

}
