package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
