package com.nttdata.bootcamp.msbankaccount.infrastructure.web.api.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.msbankaccount.application.outgoing.FindProductRulePort;
import com.nttdata.bootcamp.msbankaccount.domain.model.ProductRule;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Component
public class ProductRuleClientAdapter implements FindProductRulePort{

	final  Logger logger= LoggerFactory.getLogger(ProductRuleClientAdapter.class);

	/**Url del microservicio declarada en el archivo yml.*/
	@Value("${client.msbankproductrule.url}")
	private String url;
	/**Cliente para extablecer conexión cnon el microservicio.*/
	private WebClient client = WebClient.create(url);

	/**Método que llama al microservicio product rule.*/
	@Override
	@CircuitBreaker(name="parameter-service", fallbackMethod ="findByCustomerTypeProductCustomerCategoryAlternative" )
	public Mono<ProductRule> findByCustomerTypeProductCustomerCategory(final String codeProduct, final String codeCustomerType, final String codeCustomerCategory) {
		Mono<ResponseEntity<ProductRule>> response = client.get()
				.uri(url.concat("/{codeProduct}/{codeCustomerType}/{codeCustomerCategory}"), codeProduct, codeCustomerType, codeCustomerCategory)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new Exception("Error 400")))
				.onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new Exception("Error 500")))
				.toEntity(ProductRule.class);
		 return response.flatMap(r -> Mono.just(r.getBody()));

	}

	/**Método que se ejecuta cuando el microservicio product rule no se encuentra activo.*/
	public Mono<ProductRule> findByCustomerTypeProductCustomerCategoryAlternative(final String codeProduct, final String codeCustomerType, final String codeCustomerCategory,Exception e) {
		logger.info("findByCustomerTypeProductCustomerCategoryAlternative executed {}, {}, {}  ", codeProduct, codeCustomerType, codeCustomerCategory);
		logger.error(e.getMessage());
		return Mono.empty();
	}

}
