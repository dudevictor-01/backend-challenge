package com.invillia.acme.business;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderStatus;
import com.invillia.acme.repository.OrderRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Helps to manage CURD operations of an {@link Order}
 * @author José Victor | jvas.2000@gmail.com
 */
@Service
public class OrderManager {

	@Autowired
	private OrderRepository repository;

	/**
	 * Save a order in database.
	 * It helps to link the list of items to the specific {@link Order}
	 * @param order order to by persisted / updated on databsase
	 * @return the order saved instance
	 */
	public Order save(Order order) {

		if (CollectionUtils.isNotEmpty(order.getItems())) {
			order.getItems().forEach(i -> i.setOrder(order));
		}

		return this.repository.save(order);
	}

	/**
	 * Update a status of a {@link Order}
	 * @param orderId id of a order
	 * @param orderStatus stats that the order will be updated to
	 * @return retorns the actual state of the order
	 */
	public Optional<Order> updateStatus(Long orderId, OrderStatus orderStatus) {
		this.repository.updateStatus(orderId, orderStatus);
		return this.repository.findById(orderId);
	}

}
