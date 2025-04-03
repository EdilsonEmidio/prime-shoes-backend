package br.com.primeshoes.api.dtos;

import java.sql.Date;

import br.com.primeshoes.api.enuns.Role;

public record UserResponseDTO(
	long id,
	String name,
	String email,
	Role role,
	Date created_at,
	Date updated_at) {

}
