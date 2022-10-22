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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ProductRuleControllerAdapter {

	final static Logger logger= LoggerFactory.getLogger(ProductRuleControllerAdapter.class);
	
	@Autowired
	private  CreateProductRuleUseCase createProductRuleUseCase;

	
	@PostMapping("/{codeProduct}")
	public Mono<ResponseEntity<ProductRule>> create(@PathVariable("codeProduct") String codeProduct,  @RequestBody Mono<ProductRule> productRule){	
		
		logger.info("Initial process create ProductRule");
		return createProductRuleUseCase.create(productRule, codeProduct)
				 .doOnNext(pr->{
					 logger.info("ProductRule: CodeRule {}, CodeProduct {} , CodeCustomerType {}, Value{} }",
					            pr.getCodeRule(),pr.getProduct().getCode(),pr.getCodeCustomerType(),pr.getValue());
				 })
	    		 .map(ResponseEntity::ok)
	    		 .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process ProductRule is not been created");
	    			}else {
	    				logger.info("Final process ProductRule is been created");
	    			}
	    		 });
		
	}
	
}
