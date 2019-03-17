package com.invillia.acme.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * Defines the payment entity
 * @author José Victor | jvas.2000@gmail.com
 */
@Entity
@Data
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_payment")
	private Long id;

	@OneToOne
	@JsonIgnoreProperties("items")
	private Order order;

	@Enumerated(EnumType.STRING)
	private PaymentStatus status;

	private String creditCardNumber;

	private LocalDateTime paymentDate;


}
