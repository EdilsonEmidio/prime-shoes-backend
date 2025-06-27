package br.com.primeshoes.api.dtos;


public record AddressResponseDTO(
		long id,
		String street,
	    String number,
	    String neighborhood,
	    String city,
	    String state,
	    String complement,
	    long zipcode,
	    UserResponseDTO user
		) {

}
