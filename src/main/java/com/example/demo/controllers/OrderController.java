package com.example.demo.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.OrderDTO;
import com.example.demo.repositories.MealRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MealRepository mealRepository;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOrder(@RequestBody  OrderDTO orderDto){
		return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<OrderDTO>> getAll(){
		System.out.println(SecurityContextHolder.getContext().getAuthentication());
		return new ResponseEntity<List<OrderDTO>>(orderService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/userOrders")
	public ResponseEntity<List<OrderDTO>> getUserOrders(){
		return new ResponseEntity<List<OrderDTO>>(orderService.getUserOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> findById(@PathVariable Long id){
		return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
	}
	
	@GetMapping("/ordersByUser/{id}")
	public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Long id){
		return new ResponseEntity<>(orderService.getOrdersByUser(id), HttpStatus.OK);
	}
}