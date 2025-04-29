package br.com.primeshoes.api.dtos;

import br.com.primeshoes.api.entities.Product;

public record ProductVariationResponseDTO(
	long id,
	String color,
	float size,
	int stock,
	Product product) {

}
