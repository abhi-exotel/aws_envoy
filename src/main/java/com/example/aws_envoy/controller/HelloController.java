package com.example.aws_envoy.controller;

import java.math.BigInteger;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HelloController {
	@GetMapping(value = "/hello-envoy", produces = { "application/json" })
	public ResponseEntity<?> hello() {
		log.info("{}: API Current time: {}","hello-envoy", Instant.now().toString());
		final Map<String, String> response = new HashMap<>();
		response.put("status", "Envoy APP is running with envoy as sidecar.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping(value = "/hello-envoy-delay", produces = { "application/json" })
	public ResponseEntity<?> helloDelay() {
		log.info("{}: API Current time: {}","hello-envoy-delay", Instant.now().toString());
		try{
		    Thread.sleep(10);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		final Map<String, String> response = new HashMap<>();
		response.put("status", "Envoy APP is running with envoy as sidecar.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping(value = "/cpu-intensive", produces = {"application/json"})
	public ResponseEntity<?> cpuIntensizeTask() {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
	    for (int j = 0; j < 10; j++) {
	        final int ID = j;
	        executorService.submit(new Runnable() {

	            public void run() {
	                for (int i=0;i < 40; i++) {
	                    log.info(ID+" worker: "+i + ": " + fib(new BigInteger(String.valueOf(i))));
	                }
	            }
	        });
	    }
	    executorService.shutdown();
	    return ResponseEntity.status(HttpStatus.OK).body("Process has been initiated");
	}
	
	public static BigInteger fib(BigInteger n) {
	    if (n.compareTo(BigInteger.ONE) == -1 || n.compareTo(BigInteger.ONE) == 0 ) return n;
	    else 
	        return fib(n.subtract(BigInteger.ONE)).add(fib(n.subtract(BigInteger.ONE).subtract(BigInteger.ONE)));
	}
}
