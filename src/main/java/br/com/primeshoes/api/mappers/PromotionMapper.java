package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.PromotionCreateDTO;
import br.com.primeshoes.api.dtos.PromotionResponseDTO;
import br.com.primeshoes.api.entities.Promotion;

public class PromotionMapper {
	
	
	public static PromotionResponseDTO toDTO(Promotion promotion) {
		PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO(
				promotion.getId(),
				promotion.getName(),
				promotion.getDiscountPercentage(),
				promotion.getStartDate(),
				promotion.getEndDate(),
				promotion.getIsActive(),
				ProductVariationMapper.toDTO(promotion.getProductVariation())
				);
		
		return promotionResponseDTO;
	}
	
	public static Promotion toEntity(PromotionCreateDTO promotionCreateDTO) {
		Promotion promotion = new Promotion();
		promotion.setActive(false);
		promotion.setDiscountPercentage(promotionCreateDTO.discountPercentage());
		promotion.setStartDate(promotionCreateDTO.startDate());
		promotion.setEndDate(promotionCreateDTO.endDate());
		promotion.setName(promotionCreateDTO.name());
		
		
		return promotion;
	}

}
