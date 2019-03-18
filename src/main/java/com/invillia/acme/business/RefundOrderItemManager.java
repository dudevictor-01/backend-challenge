package com.invillia.acme.business;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.RefundOrderItem;
import com.invillia.acme.repository.OrderItemRepository;
import com.invillia.acme.repository.OrderRepository;
import com.invillia.acme.repository.RefundOrderItemRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Helps to manage dbo operations of an {@link RefundOrderItem}
 * @author José Victor | jvas.2000@gmail.com
 */
@Service
public class RefundOrderItemManager {

	@Autowired
	private RefundOrderItemRepository refundOrderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private RefundLogic refundLogic;

	/**
	 * Refund items for the ordered id
	 * @param orderId id of the {@link Order}
	 * @param itemsId list of items refunded
	 * @return returns the list of {@link RefundOrderItem} of each id in itemsId param
	 */
	public List<RefundOrderItem> refundItems(Long orderId, List<Long> itemsId) {
		List<RefundOrderItem> refundedItems = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(itemsId)) {
			refundedItems = proceedWithRefundIfIsValid(orderId, itemsId);
		}

		return refundedItems;
	}

	/**
	 * Refunds all items of an order
	 * @param orderId id of the order that will be refunded
	 * @return return a list of {@link RefundOrderItem}
	 */
	public List<RefundOrderItem> refundAnOrder(Long orderId) {
		List<Long> itemsId = orderItemRepository.findByOrderId(orderId).stream()
				.map(OrderItem::getId)
				.collect(Collectors.toList());

		return refundItems(orderId, itemsId);
	}

	private List<RefundOrderItem> proceedWithRefundIfIsValid(Long orderId, List<Long> itemsId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Order not found for id " + orderId));

		if (!refundLogic.isValidRequestRefundForOrder(order)) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN,
					"An Order just should be refunded until ten days after confirmation and the payment is concluded.");
		}

		return itemsId.stream().map(id -> persistsNewRefundOrderItem(order, id)).collect(Collectors.toList());
	}

	/**
	 * Creates and persist a new {@link RefundOrderItem} based on parameteres
	 * @param order instance of the linked order
	 * @param itemId id requested refund item
	 * @return a instance of the {@link RefundOrderItem}
	 */
	private RefundOrderItem persistsNewRefundOrderItem(Order order, Long itemId) {
		OrderItem orderItem = OrderItem.builder().id(itemId).build();
		RefundOrderItem refund = RefundOrderItem.builder()
				.order(order)
				.orderItem(orderItem)
				.requestedDate(LocalDateTime.now())
				.build();

		return refundOrderItemRepository.save(refund);
	}

}
