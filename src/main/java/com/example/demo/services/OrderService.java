package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.ShoppingItemDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;

public interface OrderService {
	
	OrderDTO createOrder(OrderDTO orderDto);
	
	List<OrderDTO> findAll();

	List<OrderDTO> getUserOrders();
	
	OrderDTO findById(Long id);
	
	List<OrderDTO> getOrdersByUser(Long id);
}
