package com.nttdata.bootcamp.msbankproductrule.application.port.outgoing;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface UpdateProductRulePort {

	Mono<ProductRule> update(Mono<ProductRule> monoProductRule);
}
