package com.nttdata.bootcamp.msbankaccount.infrastructure.web.rest.adapter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbankaccount.application.incoming.CreateAccountUseCase;
import com.nttdata.bootcamp.msbankaccount.domain.model.Account;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class AccountControllerAdapter {

	final Logger logger= LoggerFactory.getLogger(AccountControllerAdapter.class);
	
	@Autowired
	private  CreateAccountUseCase createAccountUseCase;
	
	
	@PostMapping
	public Mono<ResponseEntity<Account>> createAccount( @RequestBody Mono<Account> account){	
		return createAccountUseCase.createAccount(account).map(ResponseEntity::ok)
				  .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process createProduct - It's not been created");
	    			}else {
	    				logger.info("Final process createProduct - It's been created");
	    			}
	    		 });
	}
	
}
