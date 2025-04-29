package br.com.primeshoes.api.dtos;



public record ProductCreateDTO(
	String name,
	String description,
	float price,
	String category,
	String brand,
	String imageUrl
	) {

}
