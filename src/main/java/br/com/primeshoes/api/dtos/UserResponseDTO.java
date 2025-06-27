package br.com.primeshoes.api.dtos;

import java.time.LocalDateTime;

import br.com.primeshoes.api.enuns.Role;

public record UserResponseDTO(
	long id,
	String name,
	String email,
	Role role,
	LocalDateTime created_at,
	LocalDateTime updated_at) {

}
