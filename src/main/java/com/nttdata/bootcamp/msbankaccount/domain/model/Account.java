package com.nttdata.bootcamp.msbankaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

	private String account;
	private String codeCustomer;
	private String codeProduct;
	private String codeCustomerType;
	private String codeCustomerCategory;
	private String dayMovement;
	private float balance;
	
	
	
}
