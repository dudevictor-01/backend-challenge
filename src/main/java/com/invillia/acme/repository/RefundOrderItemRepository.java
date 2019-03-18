package com.invillia.acme.repository;

import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.RefundOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Defines the repository of {@link OrderItem}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface RefundOrderItemRepository extends JpaRepository<RefundOrderItem, Long> {

}
