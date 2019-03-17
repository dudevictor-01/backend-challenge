package com.invillia.acme.business;

import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Helps to manage dbo operations of an {@link Payment}
 * @author José Victor | jvas.2000@gmail.com
 */
@Service
public class PaymentManager {

	@Autowired
	private PaymentRepository repository;

	/**
	 * Update the status of a {@link Payment}
	 * @param paymentId id of the payment
	 * @param paymentStatus status of the payment
	 * @return returns the current state of the payment updated
	 */
	public Optional<Payment> updateStatus(Long paymentId, PaymentStatus paymentStatus) {
		this.repository.updateStatus(paymentId, paymentStatus);
		return this.repository.findById(paymentId);
	}

}
