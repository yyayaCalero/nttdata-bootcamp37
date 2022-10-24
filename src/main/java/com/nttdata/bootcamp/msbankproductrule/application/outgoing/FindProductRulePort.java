package com.nttdata.bootcamp.msbankproductrule.application.outgoing;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface FindProductRulePort {

	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory(String codeProduct,String codeCustomerType, String codeCustomerCategory); 
}
