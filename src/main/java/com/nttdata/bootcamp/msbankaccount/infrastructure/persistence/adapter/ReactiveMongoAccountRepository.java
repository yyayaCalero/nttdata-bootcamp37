package com.nttdata.bootcamp.msbankaccount.infrastructure.persistence.adapter;


import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.bootcamp.msbankaccount.infrastructure.persistence.entity.AccountEntity;

import reactor.core.publisher.Flux;

public interface ReactiveMongoAccountRepository extends ReactiveMongoRepository<AccountEntity, String>{


	@Query("{'codeCustomer': ?0,'codeProduct' : ?1}")
	public Flux<AccountEntity> findByCustomerAndProduct(String codeCustomer, String codeProduct);
	
}