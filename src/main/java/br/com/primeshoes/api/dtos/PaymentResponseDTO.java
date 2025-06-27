package br.com.primeshoes.api.dtos;

import java.time.LocalDateTime;

import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.enuns.PaymentMethod;
import br.com.primeshoes.api.enuns.PaymentStatus;

public record PaymentResponseDTO(
		long id,
		Order order,
		PaymentStatus paymentStatus,
		PaymentMethod paymentMethod,
		float amount,
		LocalDateTime createdAt
		) {

}
