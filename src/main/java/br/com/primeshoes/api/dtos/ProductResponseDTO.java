package br.com.primeshoes.api.dtos;

import java.time.Instant;

public record ProductResponseDTO(
	long id,
	String name,
	String description,
	float price,
	String category,
	String brand,
	float rating,
	Instant created_at,
	Instant updated_at) {
	
}
