package com.nttdata.bootcamp.msbankaccount.application.outgoing;

import reactor.core.publisher.Mono;

public interface ValidateQuantityProductAllowedPort {

	Mono<Boolean> validateQuantityProductAllowed(String codeCustomer,String codeProduct,Integer maxQuantityCompare);
}
