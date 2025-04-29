package br.com.primeshoes.api.dtos;

import java.time.Instant;

import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.OrderStatus;
import br.com.primeshoes.api.enuns.PaymentMethod;

public record OrderResponseDTO(
	long id,
	User user,
	float totalPrice,
	OrderStatus status,
	PaymentMethod paymentMethod,
	Instant created_at,
	Instant updated_at) {

}
