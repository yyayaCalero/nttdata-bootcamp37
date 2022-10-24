package com.nttdata.bootcamp.msbankproductrule.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
	private String description;
	private String codeProduct;
	private String codeCustomerType;
	private String codeCustomerCategory;
	private String validateQuantityProduct;
	private String validateOtherProducts;
	private Integer maxQuantityProduct;
	private String codeOtherProduct;
	
	public static ProductRule toProductRule(ProductRuleEntity productRuleEntity){
		ProductRule productRule = new ProductRule();
		BeanUtils.copyProperties(productRuleEntity , productRule);
		return productRule;
	}
	
	public static ProductRuleEntity toProductRuleEntity(ProductRule productRule){
		ProductRuleEntity productRuleEntity = new ProductRuleEntity();
		BeanUtils.copyProperties(productRule , productRuleEntity);
		return productRuleEntity;
	}
}
