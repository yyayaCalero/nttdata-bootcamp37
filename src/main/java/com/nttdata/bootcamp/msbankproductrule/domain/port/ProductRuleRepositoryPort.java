package com.nttdata.bootcamp.msbankproductrule.domain.port;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface ProductRuleRepositoryPort {
	Mono<ProductRule> create(Mono<ProductRule> monoProductRule);
	Mono<ProductRule> update(Mono<ProductRule> monoProductRule);
}
