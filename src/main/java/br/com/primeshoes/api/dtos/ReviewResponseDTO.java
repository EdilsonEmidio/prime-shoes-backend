package br.com.primeshoes.api.dtos;

import java.time.Instant;

public record ReviewResponseDTO(
		long id,
		UserResponseDTO user,
		ProductVariationResponseDTO product,
		int rating,
		String comment,
		Instant createdAt
		) {

}
