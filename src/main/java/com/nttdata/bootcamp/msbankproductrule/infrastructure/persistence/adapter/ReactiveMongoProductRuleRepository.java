package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.adapter;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity.ProductRuleEntity;

public interface ReactiveMongoProductRuleRepository extends ReactiveMongoRepository<ProductRuleEntity, String>{

}