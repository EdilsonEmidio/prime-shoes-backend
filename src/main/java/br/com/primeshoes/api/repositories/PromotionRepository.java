package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long>{

}
