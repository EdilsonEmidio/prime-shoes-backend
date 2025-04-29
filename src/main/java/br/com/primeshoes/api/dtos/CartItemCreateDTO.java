package br.com.primeshoes.api.dtos;


public record CartItemCreateDTO(
	long productVariation,
	long cart,
	int quantity) {

}
