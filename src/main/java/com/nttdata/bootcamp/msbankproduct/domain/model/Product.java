package com.nttdata.bootcamp.msbankproduct.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	private String code;
	private String name;
	private String maintenanceAmount;
	private String quantityMovementLimit;
	private String productType;
	
}
