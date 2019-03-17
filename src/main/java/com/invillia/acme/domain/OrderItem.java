package com.invillia.acme.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Defines the OrderItem entity
 * @author José Victor | jvas.2000@gmail.com
 */
@Entity
@Data
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_orderItem")
	private Long id;
	private String description;
	private Double unitPrice;
	private Integer quantity;

	@ManyToOne
	private Order order;

}
