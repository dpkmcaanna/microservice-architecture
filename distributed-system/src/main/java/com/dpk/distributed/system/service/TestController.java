package com.dpk.distributed.system.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/accounts/v1")
public class TestController {
	
	@Autowired
	MeterRegistry meterRegistry;
	
	@Timed(description = "Time spend in signup")
	@GetMapping("/account")
	public void createAccount() throws InterruptedException {
		log.info("Received account signup request");
		Counter requestCounter = meterRegistry.counter("test-counter");
		//Thread.sleep(1000L);
		Random random = new Random(20);
		requestCounter.increment(random.nextDouble());
	}
}
