package com.invillia.acme.domain;

/**
 * Defines the status of an {@link Order}
 * @author José Victor | jvas.2000@gmail.com
 */
public enum OrderStatus {

	PROCESSING, PAYMENT_ACCEPT, GENERATING_INVOICE, IN_ROUTE, FINISHED, CANCELLED;

}
