package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Product;
import br.com.primeshoes.api.entities.ProductVariation;
import java.util.List;


public interface ProductVariationRepository extends JpaRepository<ProductVariation, Long> {

	
	List<ProductVariation> findByProduct(Product product);
	
}
