package com.example.demo.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.example.demo.dto.OrderDTO;
import com.example.demo.entities.Order;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
uses = ShoppingItemMapper.class)
public interface OrderMapper {

	public static OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

	public OrderDTO entityToDTO(Order order);

	public Order DtoToEntity(OrderDTO orderDto);

}
