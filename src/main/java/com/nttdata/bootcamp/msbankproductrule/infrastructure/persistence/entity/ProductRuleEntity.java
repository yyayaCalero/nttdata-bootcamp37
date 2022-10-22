package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbankproductrule.domain.model.Product;
import com.nttdata.bootcamp.msbankproductrule.domain.model.ProductRule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "ProductRule")
public class ProductRuleEntity {

	@Id
	private String codeRule;
	@DBRef
	private ProductEntity product;
	private String codeCustomerType;
	private String value;

	
	public static ProductRule toProductRule(ProductRuleEntity productRuleEntity){
		
		ProductRule productRule = new ProductRule();
		productRule.setCodeRule(productRuleEntity.getCodeRule());
		productRule.setCodeCustomerType(productRuleEntity.getCodeCustomerType());
		productRule.setValue(productRuleEntity.getValue());
		
		Product product = new Product();
		product.setCode(productRuleEntity.getProduct().getCode());
		productRule.setProduct(product);
		
		return productRule;
	}
	
	public static ProductRuleEntity toProductRuleEntity(ProductRule productRule){
		
		ProductRuleEntity productRuleEntity = new ProductRuleEntity();
		productRuleEntity.setCodeRule(productRule.getCodeRule());
		productRuleEntity.setCodeCustomerType(productRule.getCodeCustomerType());
		productRuleEntity.setValue(productRule.getValue());
		
		ProductEntity productEntity = new ProductEntity();
		productEntity.setCode(productRule.getProduct().getCode());
		productRuleEntity.setProduct(productEntity);
		
		return productRuleEntity;
	}
}
