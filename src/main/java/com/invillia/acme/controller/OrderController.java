package com.invillia.acme.controller;

import com.invillia.acme.business.OrderManager;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Store;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Define a Rest Controller for the {@link Store} entity
 * @author José Victor | jvas.2000@gmail.com
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	private final OrderManager manager;
	private final OrderRepository repository;
	private final OrderItemRepository orderItemRepository;

	@Autowired
	public OrderController(OrderRepository repository, OrderManager manager, OrderItemRepository orderItemRepository) {
		this.manager = manager;
		this.repository = repository;
		this.orderItemRepository = orderItemRepository;
	}

	@PostMapping("/")
	public Order create(@RequestBody Order order) {
		return manager.save(order);
	}

	@GetMapping("/{id}/items")
	public List<OrderItem> retrieveOrderItems(@PathVariable Long id) {
		return orderItemRepository.findByOrderId(id);
	}

	@GetMapping("/")
	public List<Order> findAll() {
		return repository.findAll();
	}

}
