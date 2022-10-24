package com.nttdata.bootcamp.msbankaccount.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbankaccount.application.incoming.CreateAccountUseCase;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.CreateAccountPort;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.FindProductRulePort;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.ValidateExistsAccountWithProductPort;
import com.nttdata.bootcamp.msbankaccount.application.outgoing.ValidateQuantityProductAllowedPort;
import com.nttdata.bootcamp.msbankaccount.domain.enums.EnumValidator;
import com.nttdata.bootcamp.msbankaccount.domain.model.Account;

import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
public class AccountService implements CreateAccountUseCase{
	
	final static Logger logger= LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private CreateAccountPort createAccountPort;

	@Autowired
	private ValidateQuantityProductAllowedPort validateQuantityProductAllowedPort;
	
	@Autowired
	private ValidateExistsAccountWithProductPort validateExistsAccountWithProductPort;
	
	@Autowired
	private FindProductRulePort findProductRulePort;
	
	
	@Override
	public Mono<Account> createAccount( Mono<Account> monoAccount) {
		
		//TODO findCustomerById
		return monoAccount.flatMap(account->{
				return this.zipBussinesRulesValidateCreateAccount(account)
							.map(tupla->{return (tupla.getT1()&&tupla.getT2())?Boolean.TRUE:Boolean.FALSE;})
						    .flatMap(validate->{return (validate)?createAccountPort.createAccount(Mono.just(account)):Mono.empty();});
												
				}).onErrorResume(e -> {
					  logger.error("Error createAccount {}",e.getMessage());
					  return Mono.empty();
				});
	}


	public Mono<Tuple2<Boolean,Boolean>> zipBussinesRulesValidateCreateAccount(Account account){
		return findProductRulePort.findByCustomerTypeProductCustomerCategory(account.getCodeProduct(),account.getCodeCustomerType(),account.getCodeCustomerCategory())
						    .flatMap(productRule->{
								Mono<Boolean> monoValidateQuantityProduct=null;
								Mono<Boolean> monoValidateExistsProduct=null;
								if (productRule.getValidateQuantityProduct().equals(EnumValidator.YES.getValue())) {
									monoValidateQuantityProduct=validateQuantityProductAllowedPort.validateQuantityProductAllowed(account.getCodeCustomer(),account.getCodeProduct(),productRule.getMaxQuantityProduct());		
								}else {
									monoValidateQuantityProduct=Mono.just(Boolean.TRUE);
								}
								if (productRule.getValidateOtherProducts().equals(EnumValidator.YES.getValue())) {
									monoValidateExistsProduct=validateExistsAccountWithProductPort.validateExistsAccountWithProduct(account.getCodeCustomer(), productRule.getCodeOtherProduct());
								}else {
									monoValidateExistsProduct=Mono.just(Boolean.TRUE);
								}
								return Mono.zip(monoValidateQuantityProduct, monoValidateExistsProduct);
						    	
						    });
	}
	


	
}
