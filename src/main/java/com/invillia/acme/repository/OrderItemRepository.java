package com.invillia.acme.repository;

import com.invillia.acme.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines the repository of {@link OrderItem}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrderId(Long orderId);

}
