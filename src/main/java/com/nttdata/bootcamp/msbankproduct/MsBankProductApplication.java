package com.nttdata.bootcamp.msbankproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsBankProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBankProductApplication.class, args);
	}

}
