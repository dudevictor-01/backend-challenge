package com.invillia.acme.business;

import com.invillia.acme.domain.Order;
import com.invillia.acme.repository.OrderRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
