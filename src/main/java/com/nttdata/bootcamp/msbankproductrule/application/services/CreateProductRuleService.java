package com.nttdata.bootcamp.msbankproductrule.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.bootcamp.msbankproductrule.application.port.incoming.CreateProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;
import com.nttdata.bootcamp.msbankproductrule.domain.port.ProductRepositoryPort;
import com.nttdata.bootcamp.msbankproductrule.domain.port.ProductRuleRepositoryPort;

import reactor.core.publisher.Mono;

@Service
public class CreateProductRuleService implements CreateProductRuleUseCase{

	@Autowired
	private ProductRuleRepositoryPort productRuleRepositoryPort;

	@Autowired
	private ProductRepositoryPort productRepositoryPort;
	
	@Override
	public Mono<ProductRule> create(Mono<ProductRule> monoProductRule,String codeProduct) {
		Mono<ProductRule> mono= productRepositoryPort.getProduct(codeProduct)
				.zipWhen(encontrado ->{
						if(encontrado!=null) {
							return productRuleRepositoryPort.create(monoProductRule);
						}else {
							return Mono.empty();
						}		
					}).map(t->{return t.getT2();});
			
			return mono;
	}

	
}
