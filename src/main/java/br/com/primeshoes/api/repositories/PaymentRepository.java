package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
