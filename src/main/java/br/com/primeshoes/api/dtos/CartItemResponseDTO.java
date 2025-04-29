package br.com.primeshoes.api.dtos;

import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.ProductVariation;

public record CartItemResponseDTO(
	long id,
	ProductVariation productVariation,
	Cart cart,
	int quantity,
	float subtotal) {

}
