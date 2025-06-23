package br.com.primeshoes.api.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.entities.OrderItem;
import br.com.primeshoes.api.entities.User;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	
	@NativeQuery("INSERT INTO order_items (order_id, product_variation_id,"
			+ ", quantity, subtotal) VALUES (orderItem.getOrder, "
			+ "orderItem.getProductVariation, orderItem.getQuantity,"
			+ "orderItem.getSubtotal],])")
	public void saveOrderItem(@Param("orderItem") OrderItem orderItem);
	
	//tem que ver se não precisa de query
	public List<Order> findByUser(User user);
	
}
