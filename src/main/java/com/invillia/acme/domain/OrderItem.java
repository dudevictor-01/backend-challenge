package com.invillia.acme.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_orderItem")
	private Long id;
	private String description;
	private Double unitPrice;
	private Integer quantity;

	@ManyToOne
	@JsonIgnore
	private Order order;

}
