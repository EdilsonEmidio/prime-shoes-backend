package br.com.primeshoes.api.dtos;


public record CartItemCreateDTO(
	long productVariation,
	long user,
	int quantity) {

}
