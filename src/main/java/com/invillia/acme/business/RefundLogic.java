package com.invillia.acme.business;

import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Contains the logic to calculate if a refund for an order is valid or not
 * @author José Victor | jvas.2000@gmail.com
 */
@Service
public class RefundLogic {

	private static final long REFUND_LIMIT_DAYS = 10L;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private Clock clock;

	/**
	 * Tells if an refund request for an order is valid
	 * @param order order to be evaluated
	 * @return return true if the refund request is valid
	 */
	boolean isValidRequestRefundForOrder(Order order) {
		return isInTenDaysAfterConfirmation(order) && isPaymentConcluded(order);
	}

	private boolean isPaymentConcluded(Order order) {
		Optional<Payment> orderPayment = paymentRepository.findByOrderId(order.getId());

		return orderPayment.isPresent() && orderPayment
				.map(Payment::getStatus)
				.map(PaymentStatus.PAYMENT_ACCEPT::equals).orElse(false);
	}

	private boolean isInTenDaysAfterConfirmation(Order order) {
		LocalDateTime now = LocalDateTime.now(clock);
		return Duration.between(order.getConfirmationDate(), now).toDays() <= REFUND_LIMIT_DAYS;
	}

}
