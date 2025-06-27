package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.OrderCreateDTO;
import br.com.primeshoes.api.dtos.OrderResponseDTO;
import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.CartItem;
import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.entities.OrderItem;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.OrderStatus;
import br.com.primeshoes.api.mappers.OrderItemMapper;
import br.com.primeshoes.api.mappers.OrderMapper;
import br.com.primeshoes.api.repositories.CartRepository;
import br.com.primeshoes.api.repositories.OrderRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    
    public OrderResponseDTO store(OrderCreateDTO orderCreateDTO) {
		User user = userRepository.findById(orderCreateDTO.user()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado!")
			);
		Cart cart = cartRepository.findById(orderCreateDTO.cart()).orElseThrow(
				()-> new RuntimeException("Carrinho não encontrado!")
			);
		
		Order order = OrderMapper.toEntity(orderCreateDTO);
		order.setStatus(OrderStatus.PENDING);
		order.setUser(user);
		float totalPrice = 0;
		
		for(CartItem i : cartRepository.listAllCartItem(cart.getId())) {
		    totalPrice += i.getSubtotal();
		    OrderItem orderItem = OrderItemMapper.toOrderItem(i);
		    orderItem.setOrder(order);
		    orderRepository.saveOrderItem(orderItem);
		}
		order.setTotalPrice(totalPrice);
		return OrderMapper.toDTO(orderRepository.save(order));
    }
    
    public List<OrderResponseDTO> list(){
    	return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
    }
    
    public List<OrderResponseDTO> listAll(String email){
    	User user = userRepository.findByEmail(email).
    			orElseThrow(()->new RuntimeException("usuario não encontrado: OrderService"));
    	
    	return orderRepository.findByUser(user).stream().map(OrderMapper::toDTO).toList();
    }
    
    public OrderResponseDTO show(long id) {
	
		return OrderMapper.toDTO(orderRepository.findById(id).orElseThrow(
			()-> new RuntimeException("Pedido não encontrado com este id"))
			);
	    }
}
