package br.com.primeshoes.api.dtos;

import java.time.LocalDateTime;

public record ProductResponseDTO(
	long id,
	String name,
	String description,
	float price,
	String category,
	String brand,
	float rating,
	UserResponseDTO userResponseDTO,
	LocalDateTime created_at,
	LocalDateTime updated_at) {
	
}
