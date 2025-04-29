package br.com.primeshoes.api.dtos;


import br.com.primeshoes.api.enuns.PaymentMethod;

public record OrderCreateDTO(
	long cart,
	long user,
	PaymentMethod paymentMethod) {

}
