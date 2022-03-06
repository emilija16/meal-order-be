package com.example.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MealTypeDTO;
import com.example.demo.entities.MealType;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mapper.MealTypeMapper;
import com.example.demo.repositories.MealTypeRepository;
import com.example.demo.services.MealTypeService;

@Service
public class MealTypeServiceImpl implements MealTypeService {

	@Autowired
	private MealTypeRepository mealTypeRepository;

	@Override
	public List<MealTypeDTO> findAll() {

		List<MealType> types = mealTypeRepository.findAll();

		List<MealTypeDTO> typeMealDto = new ArrayList<>();

		for (MealType mealType : types) {
			typeMealDto.add(MealTypeMapper.INSTANCE.entityToDTO(mealType));
		}

		return typeMealDto;
	}

	@Override
	public MealTypeDTO findById(Long id) {

		MealType mealType = mealTypeRepository.findById(id).orElseThrow();

		return MealTypeMapper.INSTANCE.entityToDTO(mealType);

	}

	@Override
	public MealTypeDTO findByName(String name) {

		MealType mealType = mealTypeRepository.findByName(name).orElseThrow();

		return MealTypeMapper.INSTANCE.entityToDTO(mealType);
	}

	@Override
	public MealTypeDTO createMealType(MealTypeDTO mealTypeDTO) {
		MealType mealType = MealTypeMapper.INSTANCE.DtoToEntity(mealTypeDTO);
		mealTypeRepository.save(mealType);
		return MealTypeMapper.INSTANCE.entityToDTO(mealType);
	}

	@Override
	public MealTypeDTO updateMealType(MealTypeDTO mealTypeDTO, Long id) {
		MealType mealType = mealTypeRepository.findById(id).orElseThrow();
		mealType.setName(mealTypeDTO.getName());
		mealTypeRepository.save(mealType);
		return MealTypeMapper.INSTANCE.entityToDTO(mealType);
	}

	@Override
	public void deleteMealType(Long id) {
		boolean hasMealType = mealTypeRepository.existsById(id);
		if (!hasMealType) {
			throw new NotUniqueException("Ovo nije dozvoljeno");
		}
		mealTypeRepository.deleteById(id);
	}

}
