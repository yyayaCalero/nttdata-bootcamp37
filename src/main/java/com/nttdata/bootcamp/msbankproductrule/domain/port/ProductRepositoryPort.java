package com.nttdata.bootcamp.msbankproductrule.domain.port;

import com.nttdata.bootcamp.msbankproductrule.domain.model.Product;

import reactor.core.publisher.Mono;

public interface ProductRepositoryPort {
	Mono<Product> getProduct(String codeProduct);
}
