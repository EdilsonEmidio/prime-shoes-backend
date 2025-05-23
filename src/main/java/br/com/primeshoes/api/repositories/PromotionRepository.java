package br.com.primeshoes.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long>{

	
	List<Promotion> findByProductVariation(ProductVariation productVariation);
}
