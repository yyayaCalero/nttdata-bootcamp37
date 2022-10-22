package com.nttdata.bootcamp.msbankproductrule.infrastructure.web.api.adapter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.msbankproductrule.domain.model.Product;
import com.nttdata.bootcamp.msbankproductrule.domain.port.ProductRepositoryPort;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component
public class ProductClientAdapter implements ProductRepositoryPort{


	@Value("${client.msbankproduct.url}")
	private String url;
	private WebClient client = WebClient.create(url);
	
	@Override
	@CircuitBreaker(name="parameter-service", fallbackMethod ="getProductAlternative" )
	public Mono<Product> getProduct(String codeProduct) {
		return client.get()
				.uri(url.concat("/product/{code}"),codeProduct)
				.retrieve()
				.bodyToMono(Product.class);
	}
	
	public Mono<Product> getProductAlternative(String codeProduct,Exception e) {
		return Mono.empty();
	}


	
	
}
