package com.invillia.acme.repository;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines the repository of {@link Order}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	/**
	 * Define the search query to find a list of {@link Order} that matches all the params
	 * Nullable params are optional and will be desconsidered if null
	 * @param storeId id of the store (required)
	 * @param address address of the order
	 * @param startConfirmationDate start date of the confirmation
	 * @param endConfirmationDate end date of the confirmation
	 * @param status current status of the order
	 * @return retorns a list of order that matches all the requested params
	 */
	@Query("select o from Order o where o.store.id = :storeId "
			+ "and (:address is null or lower(o.address) like concat('%', lower(:address), '%')) "
			+ "and (:startConfirmationDate is null or o.confirmationDate >= :startConfirmationDate) "
			+ "and (:endConfirmationDate is null or o.confirmationDate <= :endConfirmationDate) "
			+ "and (:status is null or o.status = :status) ")
	List<Order> search(Long storeId,
			@Nullable String address,
			@Nullable LocalDateTime startConfirmationDate,
			@Nullable LocalDateTime endConfirmationDate,
			@Nullable OrderStatus status);

	/**
	 * Update de {@link OrderStatus} of an {@link Order}
	 * @param id order id
	 * @param status {@link OrderStatus} that must be updated to
	 */
	@Modifying
	@Transactional
	@Query("update Order o set o.status = :status where o.id = :id")
	void updateStatus(Long id, OrderStatus status);




}
