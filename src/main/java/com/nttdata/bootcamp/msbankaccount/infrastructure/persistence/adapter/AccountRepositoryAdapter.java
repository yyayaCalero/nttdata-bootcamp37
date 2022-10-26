package com.nttdata.bootcamp.msbankaccount.infrastructure.persistence.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nttdata.bootcamp.msbankaccount.application.outgoing.CreateAccountPort;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.ValidateExistsAccountWithProductPort;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.ValidateQuantityProductAllowedPort;
import com.nttdata.bootcamp.msbankaccount.domain.model.Account;
import com.nttdata.bootcamp.msbankaccount.infrastructure.persistence.entity.AccountEntity;

import reactor.core.publisher.Mono;

@Component
public class AccountRepositoryAdapter implements CreateAccountPort, ValidateQuantityProductAllowedPort, ValidateExistsAccountWithProductPort{

  /**Interface reactiva de mongodb.*/
	@Autowired
	private ReactiveMongoAccountRepository reactiveMongoAccountRepository;

	@Override
	public Mono<Boolean> validateExistsAccountWithProduct(String codeCustomer, String codeProduct) {
		return reactiveMongoAccountRepository.findByCustomerAndProduct(codeCustomer, codeProduct)
				.count()
				.map(countProduct -> {
					if (countProduct > 0) {
					  return Boolean.TRUE;
					}
					else {
						return Boolean.FALSE;
					}
				});

	}

	@Override
	public Mono<Boolean> validateQuantityProductAllowed(final String codeCustomer, final String codeProduct, final Integer maxQuantityCompare) {
		return reactiveMongoAccountRepository.findByCustomerAndProduct(codeCustomer, codeProduct)
				.count()
				.map(countProduct -> {
					Long  newQuantity = Long.sum(countProduct, 1);
  					if (newQuantity.intValue() <= maxQuantityCompare) {
  						return Boolean.TRUE;
  					}
  					else {
  						return Boolean.FALSE;
  					}
					});
	}

	@Override
	public Mono<Account> createAccount(final Mono<Account> account) {
		return account.map(AccountEntity::toAccountEntity)
		         .flatMap(reactiveMongoAccountRepository::insert)
				 .map(AccountEntity::toAccount);
	}

}
