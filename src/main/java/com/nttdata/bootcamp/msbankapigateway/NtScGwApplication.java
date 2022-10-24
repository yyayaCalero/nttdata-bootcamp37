package com.nttdata.bootcamp.msbankapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NtScGwApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtScGwApplication.class, args);
	}

}
