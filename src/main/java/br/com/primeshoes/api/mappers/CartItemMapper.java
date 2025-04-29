package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.CartItemCreateDTO;
import br.com.primeshoes.api.dtos.CartItemResponseDTO;
import br.com.primeshoes.api.entities.CartItem;

public class CartItemMapper {
    
    
    public static CartItem toEntity(CartItemCreateDTO cartItemCreateDTO) {
	
	CartItem cartItem = new CartItem();
	cartItem.setQuantity(cartItemCreateDTO.quantity());
	
	return cartItem;
    }
    public static CartItemResponseDTO toDTO(CartItem cartItem) {
	
	return new CartItemResponseDTO(
		cartItem.getId(),
		cartItem.getProcuctVariation(),
		cartItem.getCart(),
		cartItem.getQuantity(),
		cartItem.getSubtotal());
    }
}
