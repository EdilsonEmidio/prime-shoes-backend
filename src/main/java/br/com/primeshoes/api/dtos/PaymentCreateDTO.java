package br.com.primeshoes.api.dtos;

import br.com.primeshoes.api.enuns.PaymentMethod;

public record PaymentCreateDTO(
		long order,
		PaymentMethod paymentMethod) {

}
