package br.com.primeshoes.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    
   
    Optional<Cart> findByUser(User user);
    

}
