package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
