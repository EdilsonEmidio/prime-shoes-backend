package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
