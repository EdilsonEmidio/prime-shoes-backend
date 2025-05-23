package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.ReviewCreateDTO;
import br.com.primeshoes.api.dtos.ReviewResponseDTO;
import br.com.primeshoes.api.entities.Review;

public class ReviewMapper {

	
	public static ReviewResponseDTO toDTO(Review review) {
		ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO(
				review.getId(),
				UserMapper.toDTO(review.getUser()),
				ProductVariationMapper.toDTO(review.getProductVariation()),
				review.getRating(),
				review.getComment(),
				review.getCreatedAt());
				
		return reviewResponseDTO;
	}
	
	public static Review toEntity(ReviewCreateDTO reviewCreateDTO) {
		
		Review review = new Review();
		review.setRating(reviewCreateDTO.rating());
		review.setComment(reviewCreateDTO.comment());
		
		return review;
	}

}
