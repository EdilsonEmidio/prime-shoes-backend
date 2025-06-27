package br.com.primeshoes.api.dtos;


public record UserCreateDTO(
	String name,
	String email,
	String password) {

}
