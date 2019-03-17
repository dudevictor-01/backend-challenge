package com.invillia.acme.repository;

import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Defines the repository of {@link Payment}
 * @author José Victor | jvas.2000@gmail.com
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	/**
	 * Update de {@link PaymentStatus} of an {@link Payment}
	 * @param id payment id
	 * @param status {@link PaymentStatus} that must be updated to
	 */
	@Modifying
	@Transactional
	@Query("update Payment p set p.status = :status where p.id = :id")
	void updateStatus(Long id, PaymentStatus status);

	/**
	 * Find a payment by the {@link com.invillia.acme.domain.Order} id
	 * @param orderId order id
	 * @return return a payment of the order if it exists
	 */
	Optional<Payment> findByOrderId(Long orderId);

}
