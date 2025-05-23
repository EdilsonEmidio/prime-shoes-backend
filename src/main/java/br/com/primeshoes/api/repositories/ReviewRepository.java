package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.Review;
import br.com.primeshoes.api.entities.User;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	
	List<Review> findByUser(User user);
	
	List<Review> findByProductVariation(ProductVariation productVariation);
}
