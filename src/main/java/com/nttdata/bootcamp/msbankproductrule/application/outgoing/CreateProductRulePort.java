package com.nttdata.bootcamp.msbankproductrule.application.outgoing;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface CreateProductRulePort {

	Mono<ProductRule> createProductRule(Mono<ProductRule> monoProductRule);
}
