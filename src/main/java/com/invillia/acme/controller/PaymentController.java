package com.invillia.acme.controller;

import com.invillia.acme.business.PaymentManager;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Define a Rest Controller for the {@link Payment} entity
 * @author José Victor | jvas.2000@gmail.com
 */
@RestController
@RequestMapping("/payment")
@Api(description = "Manages payment for an order", tags = {"Payment"})
public class PaymentController {

	private final PaymentRepository repository;
	private final PaymentManager manager;

	@Autowired
	public PaymentController(PaymentRepository repository, PaymentManager manager) {
		this.repository = repository;
		this.manager = manager;
	}

	@PostMapping("/")
	public Payment save(@RequestBody Payment payment) {
		return this.manager.save(payment);
	}

	@GetMapping("/findByOrder/{orderId}")
	public Optional<Payment> findByOrder(@PathVariable Long orderId) {
		return this.repository.findByOrderId(orderId);
	}

	@PostMapping("/updateStatus/{paymentId}")
	public Optional<Payment> updateStatus(@PathVariable Long paymentId, PaymentStatus status) {
		return this.manager.updateStatus(paymentId, status);
	}

}
