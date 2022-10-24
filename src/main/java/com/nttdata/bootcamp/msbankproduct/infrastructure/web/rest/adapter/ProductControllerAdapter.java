package com.nttdata.bootcamp.msbankproduct.infrastructure.web.rest.adapter;



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

import com.nttdata.bootcamp.msbankproduct.application.port.incoming.CreateProductUseCase;
import com.nttdata.bootcamp.msbankproduct.application.port.incoming.FindProductByIdUseCase;
import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductControllerAdapter {

	final static Logger logger= LoggerFactory.getLogger(ProductControllerAdapter.class);
	
	@Autowired
	private  CreateProductUseCase createProductUseCase;
	@Autowired
	private  FindProductByIdUseCase findProductByIdUseCase;
	
	
	@PostMapping
	public Mono<ResponseEntity<Product>> createProduct( @RequestBody Mono<Product> product){	
		return createProductUseCase.createProduct(product).map(ResponseEntity::ok)
				 .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process createProduct - It's not been created");
	    			}else {
	    				logger.info("Final process createProduct - It's been created");
	    			}
	    		 });
	}
	
	@GetMapping("/{code}")
	public Mono<ResponseEntity<Product>> findProductById(@PathVariable("code") String code){
		

		
	return findProductByIdUseCase.findProductById(code).map(ResponseEntity::ok)
				 .defaultIfEmpty(ResponseEntity.notFound().build())
	    		 .doOnNext(p->{
	    			if( p.getBody()==null) {
	    				logger.info("Final process findProductById - It's not been found");
	    			}else {
	    				logger.info("Final process findProductById - It's been found");
	    			}
	    		 });
		
	}
	

}
