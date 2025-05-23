package br.com.primeshoes.api.dtos;

public record ReviewUpdateDTO(
		long id,
		int rating,
		String comment
		) {

}
