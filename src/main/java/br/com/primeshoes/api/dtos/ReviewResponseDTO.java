package br.com.primeshoes.api.dtos;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
		long id,
		UserResponseDTO user,
		ProductVariationResponseDTO product,
		int rating,
		String comment,
		LocalDateTime createdAt
		) {

}
