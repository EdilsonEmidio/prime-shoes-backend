package br.com.primeshoes.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.CartItem;
import jakarta.transaction.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	
	List<CartItem> findByCart(Cart cart);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM CartItem c WHERE c.cart.id = :cart")
	void deleteByCart(@Param("cart") long cart);
}
