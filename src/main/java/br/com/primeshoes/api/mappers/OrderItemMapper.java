package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.OrderItemCreateDTO;
import br.com.primeshoes.api.dtos.OrderItemResponseDTO;
import br.com.primeshoes.api.entities.CartItem;
import br.com.primeshoes.api.entities.OrderItem;

public class OrderItemMapper {
    
    public static OrderItem toOrderItem(CartItem cartItem) {
	OrderItem orderItem = new OrderItem();
	orderItem.setProductVariation(cartItem.getProcuctVariation());
	orderItem.setQuantity(cartItem.getQuantity());
	orderItem.setSubtotal(cartItem.getSubtotal());
	return orderItem;
    }
    
    public static OrderItem toEntity(OrderItemCreateDTO orderItemCreateDTO) {
	
	OrderItem orderItem = new OrderItem();
	orderItem.setQuantity(orderItemCreateDTO.quantity());
	return orderItem ;
    }
    
    public static OrderItemResponseDTO toDTO(OrderItem orderItem) {
	
	return new OrderItemResponseDTO(
		orderItem.getId(),
		orderItem.getOrder(),
		orderItem.getProductVariation(),
		orderItem.getQuantity(),
		orderItem.getSubtotal()
		);
    }

}
