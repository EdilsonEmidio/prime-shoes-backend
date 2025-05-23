package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.primeshoes.api.dtos.ReviewCreateDTO;
import br.com.primeshoes.api.dtos.ReviewResponseDTO;
import br.com.primeshoes.api.dtos.ReviewUpdateDTO;
import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.Review;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.mappers.ReviewMapper;
import br.com.primeshoes.api.repositories.ProductRepository;
import br.com.primeshoes.api.repositories.ReviewRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ProductRepository productRepository;
	
	
	public ReviewResponseDTO save(ReviewCreateDTO reviewCreateDTO){
		
		Review review = ReviewMapper.toEntity(reviewCreateDTO);
		User user = userRepository.findById(reviewCreateDTO.user()).orElseThrow(
				()->new RuntimeException("usuario não encontrado"));
		
		ProductVariation productVariation = productRepository.findProductVariation(reviewCreateDTO.ProductVariation()).orElseThrow(
				()-> new RuntimeException("Produto não encontrado"));
		
		review.setProductVariation(productVariation);
		review.setUser(user);
		
		reviewRepository.save(review);
		
		return ReviewMapper.toDTO(review);
	}
	
	public ReviewResponseDTO update(ReviewUpdateDTO reviewUpdateDTO){
		
		Review review = reviewRepository.findById(reviewUpdateDTO.id()).orElseThrow(
				()-> new RuntimeException());
		
		review.setComment(reviewUpdateDTO.comment());
		review.setRating(reviewUpdateDTO.rating());
		
		
		return ReviewMapper.toDTO(reviewRepository.save(review));

	}
	
	public List<ReviewResponseDTO> listProduct(long id_product){
		
		ProductVariation productVariation = productRepository.findProductVariation(id_product).orElseThrow(
				()-> new RuntimeException("produto não encontrado"));
		
		List<ReviewResponseDTO> reviewResponseDTO = reviewRepository.findByProductVariation(productVariation).stream().map(ReviewMapper::toDTO).toList();
		
				
		return reviewResponseDTO;
	}
	public List<ReviewResponseDTO> listUser(long id_user){
		
		User user = userRepository.findById(id_user).orElseThrow(
				()-> new RuntimeException("usuario não encontrado"));
		
		List<ReviewResponseDTO> reviewResponseDTO = reviewRepository.findByUser(user).stream().map(ReviewMapper::toDTO).toList();
				
		return reviewResponseDTO;
	}
	
	
	public void destroy(long id){
		Review review = reviewRepository.findById(id).orElseThrow(
				()-> new RuntimeException("review não encontrado"));
		
		reviewRepository.delete(review);
	}
}
