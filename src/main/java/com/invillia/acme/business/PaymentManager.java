package com.invillia.acme.business;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

	/**
	 * Save a payment for an order if it was not exists yet.
	 * If it already exists, an exception {@link HttpClientErrorException} is thrown
	 * @param payment payment to be saved
	 * @return returns the payment saved
	 */
	public Payment save(Payment payment) {
		Order order = Optional.ofNullable(payment.getOrder())
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.FORBIDDEN, "An Order must be specified"));

		Optional<Payment> optPayment = this.repository.findByOrderId(order.getId());
		if (optPayment.isPresent()) {
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Payment for this order already exists");
		}

		return this.repository.save(payment);
	}

}
