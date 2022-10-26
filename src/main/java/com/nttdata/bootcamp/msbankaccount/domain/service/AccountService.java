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
public class AccountService implements CreateAccountUseCase {
	
  final  Logger logger = LoggerFactory.getLogger(AccountService.class);
	
  /**Interface para crear cuentas.*/
	@Autowired
	private CreateAccountPort createAccountPort;

  /**Interface  realizar validaciones.*/
	@Autowired
	private ValidateQuantityProductAllowedPort
	        validateQuantityProductAllowedPort;

  /**Interface para realizar validaciones.*/
	@Autowired
	private ValidateExistsAccountWithProductPort
	        validateExistsAccountWithProductPort;

  /**Interface del api de ProductRule.*/
	@Autowired
	private FindProductRulePort findProductRulePort;


  /**Metodo encargado de crear la cuenta del cliente.*/
	@Override
	public Mono<Account> createAccount(final Mono<Account> monoAccount) {

		//TODO findCustomerById
		return monoAccount.flatMap(account->{
				return this.zipBussinesRulesValidateCreateAccount(account)
							.map(tupla -> {
							  return (tupla.getT1() && tupla.getT2()) ? Boolean.TRUE : Boolean.FALSE; })
						    .flatMap(validate -> {
						    return validate ? createAccountPort.createAccount(Mono.just(account)):Mono.empty();});
				}).onErrorResume(e -> {
					  logger.error("Error createAccount {}",e.getMessage());
					  return Mono.empty();
				});
	}


  /**Metodo encargado de unir procesos en paralelo.
   * Validación para la creación de la cuenta del cliente.*/
	public Mono<Tuple2<Boolean, Boolean>> zipBussinesRulesValidateCreateAccount(final Account account){
		return findProductRulePort.findByCustomerTypeProductCustomerCategory(account.getCodeProduct(), account.getCodeCustomerType(), account.getCodeCustomerCategory())
						    .flatMap(productRule -> {
						    Mono<Boolean> monoValQuantityProduct = null;
								Mono<Boolean> monoValExistsProduct = null;
								if (productRule.getValidateQuantityProduct().equals(EnumValidator.YES.getValue())) {
								  monoValQuantityProduct = validateQuantityProductAllowedPort.validateQuantityProductAllowed(account.getCodeCustomer(), account.getCodeProduct(), productRule.getMaxQuantityProduct());
								} else {
								  monoValQuantityProduct = Mono.just(Boolean.TRUE);
								}
								if (productRule.getValidateOtherProducts().equals(EnumValidator.YES.getValue())) {
								  monoValExistsProduct = validateExistsAccountWithProductPort.validateExistsAccountWithProduct(account.getCodeCustomer(), productRule.getCodeOtherProduct());
								}else {
								  monoValExistsProduct = Mono.just(Boolean.TRUE);
								}
								return Mono.zip(monoValQuantityProduct, monoValExistsProduct);
						    });
	}
	


	
}
