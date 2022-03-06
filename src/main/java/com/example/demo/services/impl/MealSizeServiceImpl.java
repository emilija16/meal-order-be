package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MealSizeDTO;

import com.example.demo.entities.MealSize;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mapper.MealSizeMapper;
import com.example.demo.repositories.MealSizeRepository;
import com.example.demo.services.MealSizeService;

@Service
public class MealSizeServiceImpl implements MealSizeService {

	@Autowired
	private MealSizeRepository mealSizeRepository;

	@Override
	public List<MealSizeDTO> findAll() {

		List<MealSize> sizes = mealSizeRepository.findAll();

		List<MealSizeDTO> mealSizeDto = new ArrayList<>();

		for (MealSize mealSize : sizes) {
			mealSizeDto.add(MealSizeMapper.INSTANCE.entityToDto(mealSize));
		}

		return mealSizeDto;
	}

	@Override
	public MealSizeDTO findById(Long id) {

		MealSize mealSize = mealSizeRepository.findById(id).orElseThrow();

		return MealSizeMapper.INSTANCE.entityToDto(mealSize);

	}

	@Override
	public MealSizeDTO findByName(String name) {

		MealSize mealSize = mealSizeRepository.findByName(name).orElseThrow();

		return MealSizeMapper.INSTANCE.entityToDto(mealSize);
	}

	@Override
	public MealSizeDTO createMealSize(MealSizeDTO mealSizeDTO) {

		MealSize mealSize = MealSizeMapper.INSTANCE.dtoToEntity(mealSizeDTO);
		mealSizeRepository.save(mealSize);
		return MealSizeMapper.INSTANCE.entityToDto(mealSize);
	}

	@Override
	public MealSizeDTO updateMealSize(MealSizeDTO mealSizeDTO, Long id) {
		MealSize mealSize = mealSizeRepository.findById(id).orElseThrow();
		mealSize.setName(mealSizeDTO.getName());
		mealSizeRepository.save(mealSize);
		return MealSizeMapper.INSTANCE.entityToDto(mealSize);
	}

	@Override
	public void deleteMealSize(Long id) {
		boolean hasMealSize = mealSizeRepository.existsById(id);
		if (!hasMealSize) {
			throw new NotUniqueException("Ovo nije dozvoljeno");
		}
		mealSizeRepository.deleteById(id);
	}

}
