package br.com.primeshoes.api.dtos;

import java.time.LocalDateTime;

import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.OrderStatus;
import br.com.primeshoes.api.enuns.PaymentMethod;

public record OrderResponseDTO(
	long id,
	User user,
	float totalPrice,
	OrderStatus status,
	PaymentMethod paymentMethod,
	LocalDateTime created_at,
	LocalDateTime updated_at) {

}
