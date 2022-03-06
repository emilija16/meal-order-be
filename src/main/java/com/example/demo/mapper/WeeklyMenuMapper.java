package com.example.demo.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.example.demo.dto.WeeklyMenuDTO;
import com.example.demo.entities.WeeklyMenu;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
uses = DailyManuMapper.class)
public interface WeeklyMenuMapper {
	
	WeeklyMenuMapper INSTANCE = Mappers.getMapper(WeeklyMenuMapper.class);
	
	public WeeklyMenuDTO entityToDto(WeeklyMenu weeklyMenu);

	public WeeklyMenu dtoToEntity(WeeklyMenuDTO weeklyMenuDto);

}

