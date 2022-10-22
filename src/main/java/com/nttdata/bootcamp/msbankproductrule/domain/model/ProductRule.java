package com.nttdata.bootcamp.msbankproductrule.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRule {

	private String codeRule;
	private Product product;
	private String codeCustomerType;
	private String value;
	
}
