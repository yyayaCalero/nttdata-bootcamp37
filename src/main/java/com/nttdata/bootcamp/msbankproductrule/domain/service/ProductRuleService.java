package com.nttdata.bootcamp.msbankproductrule.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbankproductrule.application.incoming.CreateProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.application.incoming.FindProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.application.outgoing.CreateProductRulePort;
import com.nttdata.bootcamp.msbankproductrule.application.outgoing.FindProductPort;
import com.nttdata.bootcamp.msbankproductrule.application.outgoing.FindProductRulePort;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

@Service
public class ProductRuleService implements CreateProductRuleUseCase,FindProductRuleUseCase{
	
	final static Logger logger= LoggerFactory.getLogger(ProductRuleService.class);

	@Autowired
	private CreateProductRulePort createProductRulePort;

	@Autowired
	private FindProductRulePort findProductRulePort;
	
	@Autowired
	private FindProductPort findProductPort;

	@Override
	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory(String codeProduct,String codeCustomerType, String codeCustomerCategory) {
		return findProductRulePort.findByCustomerTypeProductCustomerCategory(codeProduct, codeCustomerType, codeCustomerCategory).onErrorResume(e -> {
			  logger.error("Error findByCustomerTypeProductCustomerCategory {}",e.getMessage());
			  return Mono.empty();
		  });
	}

	@Override
	public Mono<ProductRule> createProductRule(Mono<ProductRule> monoProductRule) {
		//TODO FindCustomerByID, findProductRuleById,findProductRule
		return monoProductRule.flatMap(
					productRule -> findProductPort.findProductById(productRule.getCodeProduct())
                								  .zipWhen(product -> { return product!=null?createProductRulePort.createProductRule(Mono.just(productRule)):Mono.empty();})
                								  .map(tupla->{return tupla.getT2();})
                	).onErrorResume(e -> {
					  logger.error("Error createProductRule {}",e.getMessage());
					  return Mono.empty();
                	});

	}




	


	
}
