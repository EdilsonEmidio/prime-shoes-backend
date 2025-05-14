package br.com.primeshoes.api.dtos;

public record ReviewCreateDTO(
		long user,
		long ProductVariation,
		int rating,
		String comment
		) {

}
