package com.nttdata.bootcamp.msbankaccount.application.outgoing;

import com.nttdata.bootcamp.msbankaccount.domain.model.ProductRule;

import reactor.core.publisher.Mono;

public interface FindProductRulePort {


	Mono<ProductRule> findByCustomerTypeProductCustomerCategory(String codeProduct, String codeCustomerType, String codeCustomerCategory);
	
}
