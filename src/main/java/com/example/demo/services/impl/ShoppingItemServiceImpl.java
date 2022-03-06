package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ShoppingItemDTO;
import com.example.demo.entities.ShoppingItem;
import com.example.demo.exceptions.InvalidateShoppingItemException;
import com.example.demo.exceptions.NotUniqueException;
import com.example.demo.mapper.ShoppingItemMapper;
import com.example.demo.repositories.ShoppingItemRepository;
import com.example.demo.services.ShoppingItemService;

@Service
public class ShoppingItemServiceImpl implements ShoppingItemService {

	@Autowired
	ShoppingItemRepository shoppingItemRepository;

	@Override
	public List<ShoppingItemDTO> findAll() {

		List<ShoppingItem> items = shoppingItemRepository.findAll();

		List<ShoppingItemDTO> shoppingItemDto = new ArrayList<>();

		for (ShoppingItem shoppingItem : items) {
			shoppingItemDto.add(ShoppingItemMapper.INSTANCE.entityToDTO(shoppingItem));
		}

		return shoppingItemDto;
	}

	@Override
	public ShoppingItemDTO findById(Long id) {

		ShoppingItem shoppingItem = shoppingItemRepository.findById(id).orElseThrow();

		return ShoppingItemMapper.INSTANCE.entityToDTO(shoppingItem);

	}

	@Override
	public ShoppingItemDTO createShoppingItem(ShoppingItemDTO shoppingItemDTO) {
		ShoppingItem shoppingItem = ShoppingItemMapper.INSTANCE.DtoToEntity(shoppingItemDTO);
		shoppingItemRepository.save(shoppingItem);
		return ShoppingItemMapper.INSTANCE.entityToDTO(shoppingItem);
	}

	@Override
	public void deleteShoppingItem(Long id) {
		boolean hasShoppingItem = shoppingItemRepository.existsById(id);
		if (!hasShoppingItem) {
			throw new NotUniqueException("Ovo nije dozvoljeno");
		}
		shoppingItemRepository.deleteById(id);
	}

	@Override
	public ShoppingItemDTO updateShoppingItem(ShoppingItemDTO shoppingItemDTO, Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void validateShoppingItem(ShoppingItemDTO shoppingItem) {

		LocalDateTime currentDate = LocalDateTime.now();
//				.withHour(11);
		LocalDateTime dateTimeForDaylyOrder = LocalDateTime.now().withHour(10).withMinute(0);
		LocalDateTime dateTimeForTomorrowOrder = LocalDateTime.now().withHour(17).withMinute(0);

		if (shoppingItem.getMeal().isTomorrow()) {
			if (currentDate.isAfter(dateTimeForTomorrowOrder)) {
				throw new InvalidateShoppingItemException(
						"You cannot order " + shoppingItem.getMeal().name + " for tommorow after 17.00 hours");
			}
		} else {
			if (currentDate.isAfter(dateTimeForDaylyOrder)) {
				throw new InvalidateShoppingItemException(
						"You cannot order " + shoppingItem.getMeal().name + " after 10.00 hours");
			}
		}
	}

}
