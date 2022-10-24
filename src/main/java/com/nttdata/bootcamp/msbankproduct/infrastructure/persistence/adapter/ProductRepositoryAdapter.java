package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.CreateProductPort;
import com.nttdata.bootcamp.msbankproduct.application.port.outgoing.FindProductByIdPort;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;
import com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity.ProductEntity;

import reactor.core.publisher.Mono;

@Component
public class ProductRepositoryAdapter implements CreateProductPort,FindProductByIdPort{


	
	@Autowired
	private ReactiveMongoProductRepository reactiveMongoProductRepository;

	@Override
	public Mono<Product> createProduct(Mono<Product> product) {
		return product.map(ProductEntity::toProductEntity)
         .flatMap(reactiveMongoProductRepository::insert)
		 .map(ProductEntity::toProduct);

	}

	@Override
	public Mono<Product> findProductById(String code) {
		return reactiveMongoProductRepository.findById(code).map(ProductEntity::toProduct);
	}
	

}
