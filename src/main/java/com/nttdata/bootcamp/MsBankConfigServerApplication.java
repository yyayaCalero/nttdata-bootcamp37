package com.nttdata.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MsBankConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsBankConfigServerApplication.class, args);
	}

}
