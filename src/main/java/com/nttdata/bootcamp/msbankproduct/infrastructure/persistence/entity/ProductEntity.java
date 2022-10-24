package com.nttdata.bootcamp.msbankproduct.infrastructure.persistence.entity;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nttdata.bootcamp.msbankproduct.domain.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document (collection = "Product")
public class ProductEntity {

	@Id
	private String code;
	private String name;
	private String maintenanceAmount;
	private String quantityMovementLimit;
	private String productType;
	
	public static Product toProduct(ProductEntity productEntity){
		
		Product product = new Product();
		BeanUtils.copyProperties(productEntity, product);
		return product;
		
	}

	public static ProductEntity toProductEntity(Product product){
		
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(product, productEntity);
		return productEntity;
		
	}
}
