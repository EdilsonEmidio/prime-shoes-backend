package br.com.primeshoes.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	@NativeQuery("SELECT * FROM payments WHERE order_id =:id")
	public List<Payment> listAll(long id);
	
	public Payment findByOrder(Order order);
}
