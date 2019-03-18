package com.invillia.acme.business;

import com.invillia.acme.InvilliaApplication;
import com.invillia.acme.domain.Order;
import com.invillia.acme.domain.Payment;
import com.invillia.acme.domain.PaymentStatus;
import com.invillia.acme.repository.PaymentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * unit tests for {@link RefundLogic}
 * @author José Victor | jvas.2000@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InvilliaApplication.class)
public class RefundLogicTest {


	@Autowired
	private RefundLogic logic;

	@MockBean
	private PaymentRepository paymentRepository;

	@MockBean
	private Clock clock;

	@Before
	public void init() {
		Clock fixed = Clock.fixed(LocalDate.of(2019, 3, 1).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
		when(clock.instant()).thenReturn(fixed.instant());
		when(clock.getZone()).thenReturn(fixed.getZone());
	}

	@Test
	public void isValidRequestRefundForOrderFalseEmptyPayment() {
		Order order = new Order();
		order.setId(1L);
		order.setConfirmationDate(LocalDateTime.of(2019, 1, 1, 1, 0, 0 ,0));

		assertFalse(logic.isValidRequestRefundForOrder(order));
	}

	@Test
	public void isValidRequestRefundForOrderTrue() {
		Order order = new Order();
		order.setId(1L);
		order.setConfirmationDate(LocalDateTime.of(2019, 3, 2, 0, 0, 0));
		Payment payment = new Payment();
		payment.setStatus(PaymentStatus.PAYMENT_ACCEPT);
		when(paymentRepository.findByOrderId(1L)).thenReturn(Optional.of(payment));


		assertTrue(logic.isValidRequestRefundForOrder(order));
	}

	@Test
	public void isValidRequestRefundForOrderFalse10Days() {
		Order order = new Order();
		order.setId(1L);
		order.setConfirmationDate(LocalDateTime.of(2019, 2, 18, 0, 0, 0));
		Payment payment = new Payment();
		payment.setStatus(PaymentStatus.PAYMENT_ACCEPT);
		when(paymentRepository.findByOrderId(1L)).thenReturn(Optional.of(payment));


		assertFalse(logic.isValidRequestRefundForOrder(order));
	}
}
