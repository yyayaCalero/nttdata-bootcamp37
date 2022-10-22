package com.nttdata.bootcamp.msbankproductrule.application.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.nttdata.bootcamp.msbankproductrule.application.port.incoming.UpdateProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;
import com.nttdata.bootcamp.msbankproductrule.domain.port.ProductRuleRepositoryPort;

import reactor.core.publisher.Mono;

public class UpdateProductRuleService implements UpdateProductRuleUseCase{

	@Autowired
	private ProductRuleRepositoryPort productRuleRepositoryPort;
	
	@Override
	public Mono<ProductRule> update(Mono<ProductRule> monoProductRule) {
		// TODO Auto-generated method stub
		return productRuleRepositoryPort.update(monoProductRule);
	}

}
