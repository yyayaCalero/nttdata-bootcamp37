package com.nttdata.bootcamp.msbankaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
  
  /**Número de la cuenta del cliente.*/
	private String numberAccount;
	/**Código del cliente.*/
	private String codeCustomer;
	/**Código del producto.*/
	private String codeProduct;
	/**Código del tipo de cliente.*/
	private String codeCustomerType;
	/**Código de la categoria del cliente.*/
	private String codeCustomerCategory;
	/**Día del movimiento  (para cuentas a plazo fijo).*/
	private String dayMovement;
	/**Saldo de la cuenta del cliente.*/
	private float balance;

}
