package com.example.demo.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.DailyMenuDTO;
import com.example.demo.entities.DailyMenu;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
uses = MealMapper.class)
public interface DailyManuMapper {

	DailyManuMapper INSTANCE = Mappers.getMapper(DailyManuMapper.class);
	
	public DailyMenuDTO entityToDto(DailyMenu dailyMenu);

	public DailyMenu dtoToEntity(DailyMenuDTO dailyMenuDto);

}
