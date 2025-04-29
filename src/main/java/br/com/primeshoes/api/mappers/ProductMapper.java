package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.ProductCreateDTO;
import br.com.primeshoes.api.dtos.ProductResponseDTO;
import br.com.primeshoes.api.entities.Product;

public class ProductMapper {
    public static Product toEntity(ProductCreateDTO productCreateDTO) {
	Product product = new Product();
	product.setBrand(productCreateDTO.brand());
	product.setCategory(productCreateDTO.category());
	product.setDescription(productCreateDTO.description());
	product.setName(productCreateDTO.name());
	product.setPrice(productCreateDTO.price());
	product.setImageUrl(productCreateDTO.imageUrl());


	return product;
    }
    public static ProductResponseDTO toDTO(Product product) {
	
	return new ProductResponseDTO(
		product.getId(),
		product.getName(),
		product.getDescription(),
		product.getPrice(),
		product.getCategory(),
		product.getBrand(),
		product.getRating(),
		product.getCreated_at(),
		product.getUpdate_at());
    }
}
