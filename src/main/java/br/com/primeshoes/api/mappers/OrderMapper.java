package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.OrderCreateDTO;
import br.com.primeshoes.api.dtos.OrderResponseDTO;
import br.com.primeshoes.api.entities.Order;

public class OrderMapper {

    
    public static Order toEntity(OrderCreateDTO orderCreateDTO) {
	
	Order order = new Order();
	order.setPaymentMethod(orderCreateDTO.paymentMethod());
	return order;
    }
    
    public static OrderResponseDTO toDTO(Order order) {
	
	return new OrderResponseDTO(
		order.getId(),
		order.getUser(),
		order.getTotalPrice(),
		order.getStatus(),
		order.getPaymentMethod(),
		order.getCreated_at(),
		order.getUpdated_at()
		);
    }
}
