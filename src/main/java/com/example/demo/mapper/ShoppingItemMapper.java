package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.ShoppingItemDTO;

import com.example.demo.entities.ShoppingItem;

@Mapper
public interface ShoppingItemMapper {

	public static ShoppingItemMapper INSTANCE = Mappers.getMapper(ShoppingItemMapper.class);

	public abstract ShoppingItemDTO entityToDTO(ShoppingItem shoppingItem);

	public abstract ShoppingItem DtoToEntity(ShoppingItemDTO shoppingItemDto);

}
