package com.invillia.acme.repository;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderItem;
import com.invillia.acme.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Defines the repository of {@link Order}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
