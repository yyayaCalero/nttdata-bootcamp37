package com.nttdata.bootcamp.msbankproductrule.infrastructure.web.rest.adapter;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.msbankproductrule.application.incoming.CreateProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.application.incoming.FindProductRuleUseCase;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productRule")
public class ProductRuleControllerAdapter {

	final static Logger logger= LoggerFactory.getLogger(ProductRuleControllerAdapter.class);
	
	@Autowired
	private  CreateProductRuleUseCase createProductRuleUseCase;
	
	@Autowired
	private  FindProductRuleUseCase findProductRuleUseCase;
	
	
	@PostMapping
	public Mono<ResponseEntity<ProductRule>> createProductRule(@RequestBody Mono<ProductRule> productRule){	
		
		logger.info("Initial process createProductRule");
		return createProductRuleUseCase.createProductRule(productRule)
				 .doOnNext(pr->{
					 logger.info("ProductRule: {} }",
					            pr.toString());
				 })
	    		 .map(ResponseEntity::ok)
	    		 .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process createProductRule - It's not been created");
	    			}else {
	    				logger.info("Final process createProductRule - It's been created");
	    			}
	    		 });
	}
	
	@GetMapping("/{codeProduct}/{codeCustomerType}/{codeCustomerCategory}")
	public Mono<ResponseEntity<ProductRule>> findByCustomerTypeProductCustomerCategory(@PathVariable("codeProduct") String codeProduct, @PathVariable("codeCustomerType") String codeCustomerType,
																					   @PathVariable("codeCustomerCategory") String codeCustomerCategory){	
		logger.info("Initial process findByCustomerTypeProductCustomerCategory");
		return findProductRuleUseCase.findByCustomerTypeProductCustomerCategory(codeProduct, codeCustomerType, codeCustomerCategory)
				 .doOnNext(pr->{
					 logger.info("ProductRule: {} }",
					            pr.toString());
				 })
	    		 .map(ResponseEntity::ok)
	    		 .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process findByCustomerTypeProductCustomerCategory - It's not been found");
	    			}else {
	    				logger.info("Final process findByCustomerTypeProductCustomerCategory - It's been found");
	    			}
	    		 });
	}

	
}
