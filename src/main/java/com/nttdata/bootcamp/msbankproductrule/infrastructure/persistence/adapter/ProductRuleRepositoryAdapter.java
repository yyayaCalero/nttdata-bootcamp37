package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;
import com.nttdata.bootcamp.msbankproductrule.domain.port.ProductRuleRepositoryPort;
import com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity.ProductRuleEntity;

import reactor.core.publisher.Mono;

@Component
public class ProductRuleRepositoryAdapter implements ProductRuleRepositoryPort{

	final static Logger logger= LoggerFactory.getLogger(ProductRuleRepositoryAdapter.class);
	
	@Autowired
	private ReactiveMongoProductRuleRepository springDataProductRepository;
	
	@Override
	public Mono<ProductRule> create(Mono<ProductRule> monoProductRule) {
		return monoProductRule.map(ProductRuleEntity::toProductRuleEntity)
							  .flatMap(springDataProductRepository::insert)
							  .map(ProductRuleEntity::toProductRule)
							  .onErrorResume(e -> {
								  logger.error("Error insert ProductRule {}",e.getMessage());
								  return Mono.empty();
							  });
		}
	@Override
	public Mono<ProductRule> update(Mono<ProductRule> monoProductRule) {
		// TODO Auto-generated method stub
		return null;
	}

}
