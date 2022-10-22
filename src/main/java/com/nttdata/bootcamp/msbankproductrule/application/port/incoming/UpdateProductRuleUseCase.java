package com.nttdata.bootcamp.msbankproductrule.application.port.incoming;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface UpdateProductRuleUseCase {

	Mono<ProductRule> update(Mono<ProductRule> monoProductRule);
}
