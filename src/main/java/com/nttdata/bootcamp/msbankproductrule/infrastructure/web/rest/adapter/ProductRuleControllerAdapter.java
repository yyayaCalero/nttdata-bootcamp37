package com.nttdata.bootcamp.msbankproductrule.infrastructure.web.rest.adapter;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbankproductrule.application.port.incoming.CreateProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ProductRuleControllerAdapter {

	@Autowired
	private  CreateProductRuleUseCase createProductRuleUseCase;

	
	@PostMapping("/{codeProduct}")
	public Mono<ResponseEntity<ProductRule>> create(@PathVariable("codeProduct") String codeProduct,  @RequestBody Mono<ProductRule> productRule){	
		return createProductRuleUseCase.create(productRule, codeProduct)
	    		 .map(ResponseEntity::ok)
	    		.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
}
