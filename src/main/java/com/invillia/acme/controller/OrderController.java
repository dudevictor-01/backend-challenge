package com.invillia.acme.controller;

import com.invillia.acme.business.OrderManager;
import com.invillia.acme.configuration.FixSwaggerPageable;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Define a Rest Controller for the {@link Order} entity
 * @author José Victor | jvas.2000@gmail.com
 */
@RestController
@RequestMapping("/order")
@Api(description = "Set of operations for Order entity", tags = {"Order"})
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
	public Order save(@RequestBody Order order) {
		return manager.save(order);
	}

	@GetMapping("/{id}/items")
	public List<OrderItem> retrieveOrderItems(@PathVariable Long id) {
		return orderItemRepository.findByOrderId(id);
	}

	@GetMapping("/{id}")
	public Optional<Order> findById(@PathVariable Long id) {
		return repository.findById(id);
	}

	/**
	 * Search a list of {@link Order} by params
	 * @param storeId (required) the id of the order's store
	 * @param address (optional) the address of the order
	 * @param startConfirmationDate (optional) the start date of confirmation
	 * @param endConfirmationDate (optional) the end date of confirmation
	 * @param status (optional) the {@link OrderStatus} of the order
	 * @return returns a list of orders that matches the params above
	 */
	@GetMapping("/search")
	@FixSwaggerPageable
	public Page<Order> search(@RequestParam Long storeId,
			@RequestParam(required = false) String address,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startConfirmationDate,
			@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endConfirmationDate,
			@RequestParam(required = false) OrderStatus status,
			@ApiIgnore("Ignored because swagger ui shows the wrong params, "
					+ "instead they are explained in the fix swagger pageable annotation") Pageable pageable
			) {
			return this.repository.search(storeId, address, startConfirmationDate, endConfirmationDate, status, pageable);
	}

	@PostMapping("updateStatus/{id}")
	public Optional<Order> updateStatus(@PathVariable Long id, @RequestParam OrderStatus status) {
		return this.manager.updateStatus(id, status);
	}

}
