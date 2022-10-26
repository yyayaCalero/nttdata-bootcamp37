package com.nttdata.bootcamp.msbankaccount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRule {

  /**Indicador para la validación de la cantidad de productos.
   * Al crear una cuenta.*/
	private String validateQuantityProduct;
	/**Indicador para la validación de la existencia de otros productos.
	 * Al crear una cuenta.*/
	private String validateOtherProducts;
	 /**Cantidad máxima de productos permitidos
   * Al crear una cuenta.*/
	private Integer maxQuantityProduct;
	 /**Código de otros productos para validar.*/
	private String codeOtherProduct;

}
