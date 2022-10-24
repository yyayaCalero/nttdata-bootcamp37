package com.nttdata.bootcamp.msbankproductrule.application.incoming;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface CreateProductRuleUseCase {

	Mono<ProductRule> createProductRule(Mono<ProductRule> monoProductRule);
}
