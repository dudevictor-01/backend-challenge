package com.invillia.acme.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines the Order entity
 * @author José Victor | jvas.2000@gmail.com
 */
@Entity
@Table(name = "order_purchase")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_order")
	private Long id;

	private String address;

	private LocalDateTime confirmationDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
	@NotNull
	private List<OrderItem> items;

	@ManyToOne
	@NotNull
	private Store store;

}
