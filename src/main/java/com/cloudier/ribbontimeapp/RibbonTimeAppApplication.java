package com.cloudier.ribbontimeapp;

import javax.inject.Inject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RibbonClient(name = "time-service")
@RestController
public class RibbonTimeAppApplication {

	@Inject
	private RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(RibbonTimeAppApplication.class, args);
	}
	
	@GetMapping
	public String getTime() {
		return restTemplate.getForEntity("http://time-service", String.class).getBody();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
