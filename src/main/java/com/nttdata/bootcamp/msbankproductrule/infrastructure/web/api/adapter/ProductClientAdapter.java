package com.nttdata.bootcamp.msbankproductrule.infrastructure.web.api.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.msbankproductrule.application.outgoing.FindProductPort;
import com.nttdata.bootcamp.msbankproductrule.domain.model.Product;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component
public class ProductClientAdapter implements FindProductPort{

	final static Logger logger= LoggerFactory.getLogger(ProductClientAdapter.class);

	@Value("${client.msbankproduct.url}")
	private String url;
	private WebClient client = WebClient.create(url);
	
	@Override
	@CircuitBreaker(name="parameter-service", fallbackMethod ="findProductByIdAlternative" )
	public Mono<Product> findProductById(String codeProduct) {	
		
		 Mono<ResponseEntity<Product>> response = client.get()
				.uri(url.concat("/{code}"),codeProduct)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse-> Mono.error(new Exception("Error 400")))
				.onStatus(HttpStatus::is5xxServerError, clientResponse-> Mono.error(new Exception("Error 500")))
				.toEntity(Product.class);
		 
		 
		 return response.flatMap(r ->Mono.just(r.getBody()));
	}
	
	public Mono<Product> findProductByIdAlternative(String codeProduct,Exception e) {
		logger.info("findProductByIdAlternative executed {}", codeProduct);
		logger.error(e.getMessage());
		return Mono.empty();
	}


	
	
}
