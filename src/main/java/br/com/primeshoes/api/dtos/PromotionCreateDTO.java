package br.com.primeshoes.api.dtos;

import java.sql.Date;

public record PromotionCreateDTO(
		String name,
		float discountPercentage,
		Date startDate,
		Date endDate,
		long productVariation) {

}
