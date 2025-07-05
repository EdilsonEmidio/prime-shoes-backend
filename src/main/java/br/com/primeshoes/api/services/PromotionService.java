package br.com.primeshoes.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.PromotionCreateDTO;
import br.com.primeshoes.api.dtos.PromotionResponseDTO;
import br.com.primeshoes.api.dtos.PromotionUpdateDTO;
import br.com.primeshoes.api.entities.Product;
import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.Promotion;
import br.com.primeshoes.api.mappers.PromotionMapper;
import br.com.primeshoes.api.repositories.ProductRepository;
import br.com.primeshoes.api.repositories.ProductVariationRepository;
import br.com.primeshoes.api.repositories.PromotionRepository;

@Service
public class PromotionService {
	
	@Autowired
	private PromotionRepository promotionRepository;
	
	private ProductRepository productRepository;
	private ProductVariationRepository variationRepository;
	
	public PromotionService(ProductVariationRepository variationRepository, ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.variationRepository = variationRepository;
	}
	public PromotionResponseDTO save(PromotionCreateDTO promotionCreateDTO){
		
		Promotion promotion = PromotionMapper.toEntity(promotionCreateDTO);

		
		ProductVariation productVariation = variationRepository.findById(promotionCreateDTO.productVariation())
				.orElseThrow(()-> new RuntimeException("Produto não encontrado"));
		
		promotion.setProductVariation(productVariation);
		
		promotionRepository.save(promotion);
		
		return PromotionMapper.toDTO(promotion);
	}
	
	public PromotionResponseDTO update(PromotionUpdateDTO promotionUpdateDTO){
		
		Promotion promotion = promotionRepository.findById(promotionUpdateDTO.id()).orElseThrow(
				()-> new RuntimeException());
		
		promotion.setActive(promotionUpdateDTO.isActive());
		promotion.setName(promotionUpdateDTO.name());
		promotion.setDiscountPercentage(promotionUpdateDTO.discountPercentage());
		promotion.setStartDate(promotionUpdateDTO.startDate());
		promotion.setEndDate(promotionUpdateDTO.endDate());
		
		return PromotionMapper.toDTO(promotionRepository.save(promotion));

	}
	
	public List<PromotionResponseDTO> listProduct(long id_product){
		
		Product product = productRepository.findById(id_product).orElseThrow(
				()-> new RuntimeException("produto não encontrado"));
		
		List<ProductVariation> productVariations = variationRepository.findByProduct(product);
		
		List<PromotionResponseDTO> promotionResponseDTO = new ArrayList<>();
		
		for(ProductVariation pv : productVariations) {
			promotionResponseDTO.addAll(
					promotionRepository.findByProductVariation(pv).stream().map(PromotionMapper::toDTO).toList());
		}
								
		
		return promotionResponseDTO;
	}
	public List<PromotionResponseDTO> listAll(){
		
		List<PromotionResponseDTO> promotionResponseDTO = promotionRepository.findAll().stream().map(PromotionMapper::toDTO).toList();
				
		return promotionResponseDTO;
	}
	
	
	public void destroy(long id){
		Promotion promotion = promotionRepository.findById(id).orElseThrow(
				()-> new RuntimeException("review não encontrado"));
		
		promotionRepository.delete(promotion);
	}
}
