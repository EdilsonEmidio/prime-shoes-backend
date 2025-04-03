package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
