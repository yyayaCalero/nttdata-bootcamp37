package com.nttdata.bootcamp.msbankaccount.application.outgoing;

import reactor.core.publisher.Mono;

public interface ValidateExistsAccountWithProductPort {

  /**Valida la existencia de una cuenta con determinado producto.*/
	Mono<Boolean> validateExistsAccountWithProduct(String codeCustomer, String codeProduct);
}
