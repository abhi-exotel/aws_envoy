package com.example.aws_envoy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AwsEnvoyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsEnvoyApplication.class, args);
		log.info("AWS-Envoy app started");
	}

}
