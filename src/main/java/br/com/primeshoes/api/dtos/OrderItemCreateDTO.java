package br.com.primeshoes.api.dtos;


public record OrderItemCreateDTO(
	long order,
	long productVariation,
	int quantity) {

}
