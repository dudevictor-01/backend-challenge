package com.invillia.acme.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

/**
 * Define the Refund Order Item entity
 * @author José Victor | jvas.2000@gmail.com
 */
@Data
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RefundOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_refunded_order_item")
	private Long id;

	@OneToOne
	@JsonIgnoreProperties({"order"})
	private OrderItem orderItem;

	@ManyToOne
	@JsonIgnoreProperties({"refundOrderItems", "items", "status", "confirmationDate", "store", "address"})
	private Order order;

	private LocalDateTime requestedDate;

}
