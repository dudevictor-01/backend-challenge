package com.invillia.acme.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.Clock;

/**
 * Defines the clock of the system.
 * Helps when make unit tests in a class that uses LocalDateTime.now() for example
 * @author José Victor | jvas.2000@gmail.com
 */
@Service
public class SystemClock {

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}
