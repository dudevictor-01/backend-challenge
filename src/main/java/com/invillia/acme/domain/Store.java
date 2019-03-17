package com.invillia.acme.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Defines a Store entity
 * @author José Victor | jvas.2000@gmail.com
 */
@Entity
@Data
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_store")
	private Long id;

	private String name;
	private String address;

}
