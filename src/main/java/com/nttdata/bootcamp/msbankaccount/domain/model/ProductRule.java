package com.nttdata.bootcamp.msbankaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRule {

	private String validateQuantityProduct;
	private String validateOtherProducts;
	private Integer maxQuantityProduct;
	private String codeOtherProduct;
	
	
}
