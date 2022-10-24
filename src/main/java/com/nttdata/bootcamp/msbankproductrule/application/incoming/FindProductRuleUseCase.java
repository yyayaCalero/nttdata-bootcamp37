package com.nttdata.bootcamp.msbankproductrule.application.incoming;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface FindProductRuleUseCase {

	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory( String codeProduct,String codeCustomerType, String codeCustomerCategory); 
}
