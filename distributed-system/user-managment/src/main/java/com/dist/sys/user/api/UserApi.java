package com.dist.sys.user.api;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserApi {
	
	@Autowired
	MeterRegistry meterRegistry;
	
	@GetMapping
	public void getAllUsers() {
		log.info("Received call to fetch all users");
		Counter requestCounter = meterRegistry.counter("test-counter");
		//Thread.sleep(1000L);
		Random random = new Random(20);
		requestCounter.increment(random.nextDouble());
	}

}
