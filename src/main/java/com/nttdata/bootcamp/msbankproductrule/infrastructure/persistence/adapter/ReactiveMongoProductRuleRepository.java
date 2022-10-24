package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.adapter;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;
import com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity.ProductRuleEntity;

import reactor.core.publisher.Mono;

public interface ReactiveMongoProductRuleRepository extends ReactiveMongoRepository<ProductRuleEntity, String>{


	@Query("{'codeProduct': ?0,'codeCustomerType' : ?1,'codeCustomerCategory' : ?2}")
	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory(String codeProduct,String codeCustomerType, String codeCustomerCategory);
}