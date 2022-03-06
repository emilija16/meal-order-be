package com.example.demo.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ShoppingItemDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Order;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.ShoppingItemMapper;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ShoppingItemRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.OrderService;
import com.example.demo.services.ShoppingItemService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShoppingItemService shoppingItemService;
	
	@Autowired
	private ShoppingItemRepository shoppingItemRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public OrderDTO createOrder(OrderDTO orderDto) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getPrincipal().toString();
		Optional<User> userOptional = userRepository.findByEmail(userEmail);
		
		if (userOptional == null) {
			throw new UserNotFoundException("User ne postoji");
		}
		
//		orderDto.getShoppingItems().forEach(item -> shoppingItemService.validateShoppingItem(item));
		

		
		Order order = OrderMapper.INSTANCE.DtoToEntity(orderDto);
		order.setUser(userOptional.get());
		order.setTotalPrice(orderDto.getTotalPrice());
		order.setOrderDateTime(LocalDateTime.now());
		orderRepository.save(order);
		
		orderDto.getShoppingItems().stream().map(itemDTO -> ShoppingItemMapper.INSTANCE.DtoToEntity(itemDTO)).forEach(item -> {
			item.setOrder(order);
			shoppingItemRepository.save(item);
			order.getShoppingItems().add(item);
		});
		
		return OrderMapper.INSTANCE.entityToDTO(order);
		
	}

	@Override
	public List<OrderDTO> findAll() {
		List<Order> orders = orderRepository.findAll();
		return orders.stream().map(order -> OrderMapper.INSTANCE.entityToDTO(order)).collect(Collectors.toList());
	}
	
	@Override
	public List<OrderDTO> getUserOrders() {
		List<Order> orders = new ArrayList<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = auth.getPrincipal().toString();
		Optional<User> userOptional = userRepository.findByEmail(userEmail);
		
		if (userOptional == null) {
			throw new UserNotFoundException("User ne postoji");
		}
		List<Order> ordersList = orderRepository.findAll();
		ordersList.stream().map(order -> OrderMapper.INSTANCE.entityToDTO(order)).collect(Collectors.toList());
		for(int i = 0; i < ordersList.size(); i++) {
			if(ordersList.get(i).getUser() == userOptional.get()) {
				orders.add(ordersList.get(i));
			}
		}
		return orders.stream().map(order -> OrderMapper.INSTANCE.entityToDTO(order)).collect(Collectors.toList());
	}
	
	@Override
	public OrderDTO findById(Long id) {

		Order order = orderRepository.findById(id).orElseThrow();

		return OrderMapper.INSTANCE.entityToDTO(order);
	}
	@Override
	public List<OrderDTO> getOrdersByUser(Long id) {
		User user = userRepository.findById(id).orElseThrow();
		return orderRepository.findByUser(user).stream().map(order -> OrderMapper.INSTANCE.entityToDTO(order)).collect(Collectors.toList());
	}
}