package com.invillia.acme.controller;

import com.invillia.acme.business.RefundOrderItemManager;
import com.invillia.acme.domain.RefundOrderItem;
import com.invillia.acme.domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/refunds")
public class RefundsController {

	private final RefundOrderItemManager manager;

	@Autowired
	public RefundsController(RefundOrderItemManager manager) {
		this.manager = manager;
	}

	/**
	 * Refunds one or more items of an order
	 * @param orderId order id which items refers to
	 * @param itemsId list of items id that will be refunded
	 * @return returns the list of refunded order items
	 */
	@PostMapping("/refundItems/{orderId}")
	public List<RefundOrderItem> refundOrderItems(@PathVariable Long orderId, @RequestBody List<Long> itemsId) {
		return manager.refundItems(orderId, itemsId);
	}

	/**
	 * Refunds an entire {@link com.invillia.acme.domain.Order}.
	 * All items in the order will be refunded
	 * @param orderId id order that will be refunded
	 * @return returns the list of refunded order items
	 */
	@PostMapping("/refundOrder/{orderId}")
	public List<RefundOrderItem> refundEntireOrder(@PathVariable Long orderId) {
		return manager.refundAnOrder(orderId);
	}

}
