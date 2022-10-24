package com.nttdata.bootcamp.msbankaccount.application.outgoing;

import reactor.core.publisher.Mono;

public interface ValidateExistsAccountWithProductPort {

	Mono<Boolean> validateExistsAccountWithProduct(String codeCustomer,String codeProduct);
}
