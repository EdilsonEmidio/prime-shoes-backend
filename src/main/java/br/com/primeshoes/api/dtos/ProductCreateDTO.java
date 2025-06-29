package br.com.primeshoes.api.dtos;

import org.springframework.web.multipart.MultipartFile;

public record ProductCreateDTO(
	String name,
	String description,
	float price,
	String category,
	String brand,
	long user,
	MultipartFile image
	) {

}
