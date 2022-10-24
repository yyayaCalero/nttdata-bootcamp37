package com.nttdata.bootcamp.msbankproduct.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbankproduct.application.port.incoming.CreateProductUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.incoming.FindProductByIdUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.CreateProductPort;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.FindProductByIdPort;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter.ProductRepositoryAdapter;

import reactor.core.publisher.Mono;

@Service
public class ProductService implements CreateProductUseCase,FindProductByIdUseCase{

	final static Logger logger= LoggerFactory.getLogger(ProductRepositoryAdapter.class);
	
	@Autowired
	private CreateProductPort createProductPort;

	@Autowired
	private FindProductByIdPort findProductByIdPort;

	
	
	@Override
	public Mono<Product> createProduct( Mono<Product> product) {
		return createProductPort.createProduct(product).onErrorResume(e -> {
			  logger.error("Error createProduct {}",e.getMessage());
			  return Mono.empty();
		  });
	}



	@Override
	public Mono<Product> findProductById(String code) {
		return findProductByIdPort.findProductById(code).onErrorResume(e -> {
			  logger.error("Error findProductById {}",e.getMessage());
			  return Mono.empty();
		  });
	}


	


	
}
