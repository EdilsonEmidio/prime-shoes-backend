package br.com.primeshoes.api.dtos;

import java.sql.Date;

public record PromotionUpdateDTO(
		long id,
		String name,
		float discountPercentage,
		Date startDate,
		Date endDate,
		boolean isActive
		) {

}
