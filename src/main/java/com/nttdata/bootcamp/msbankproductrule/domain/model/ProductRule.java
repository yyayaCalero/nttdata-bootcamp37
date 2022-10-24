package com.nttdata.bootcamp.msbankproductrule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRule {

	private String codeRule;
	private String description;
	private String codeProduct;
	private String codeCustomerType;
	private String codeCustomerCategory;
	private String validateQuantityProduct;
	private String validateOtherProducts;
	private Integer maxQuantityProduct;
	private String codeOtherProduct;
	
}
