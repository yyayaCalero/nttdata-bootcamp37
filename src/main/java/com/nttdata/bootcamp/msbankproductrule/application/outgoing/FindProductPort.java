package com.nttdata.bootcamp.msbankproductrule.application.outgoing;


import com.nttdata.bootcamp.msbankproductrule.domain.model.Product;

import reactor.core.publisher.Mono;

public interface FindProductPort {

	public  Mono<Product> findProductById(String codeProduct);
}
