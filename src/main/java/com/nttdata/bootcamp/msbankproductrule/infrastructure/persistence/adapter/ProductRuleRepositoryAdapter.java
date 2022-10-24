package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbankproductrule.application.outgoing.CreateProductRulePort;
import com.nttdata.bootcamp.msbankproductrule.application.outgoing.FindProductRulePort;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;
import com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity.ProductRuleEntity;

import reactor.core.publisher.Mono;

@Component
public class ProductRuleRepositoryAdapter implements CreateProductRulePort,FindProductRulePort{


	
	@Autowired
	private ReactiveMongoProductRuleRepository springDataProductRepository;


	
	@Override
	public Mono<ProductRule> createProductRule(Mono<ProductRule> monoProductRule) {
		
		
	return monoProductRule.map(ProductRuleEntity::toProductRuleEntity)
							  .flatMap(springDataProductRepository::insert)
							  .map(ProductRuleEntity::toProductRule);
	}

	@Override
	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory(String codeProduct,String codeCustomerType, String codeCustomerCategory) {
		return springDataProductRepository.findByCustomerTypeProductCustomerCategory(codeProduct, codeCustomerType, codeCustomerCategory);
	}


}
